package com.bilitech.yilimusic.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bilitech.yilimusic.DTO.user.UserCreateDTO;
import com.bilitech.yilimusic.DTO.user.UserDTO;
import com.bilitech.yilimusic.DTO.user.UserLoginDTO;
import com.bilitech.yilimusic.DTO.user.UserQueryDTO;
import com.bilitech.yilimusic.DTO.user.UserUpdateDTO;
import com.bilitech.yilimusic.enums.ExceptionType;
import com.bilitech.yilimusic.mapper.UserMapper;
import com.bilitech.yilimusic.repository.UserRepository;
import com.bilitech.yilimusic.config.AuthenticationConfigConstants;
import com.bilitech.yilimusic.enetity.QUser;
import com.bilitech.yilimusic.enetity.User;
import com.bilitech.yilimusic.exception.BizException;
import com.bilitech.yilimusic.utils.QueryRequest;
import com.querydsl.core.BooleanBuilder;
import io.vavr.control.Option;
import java.util.Date;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author 陈现府
 */
@Service
@AllArgsConstructor
public class UserServiceImpI implements UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  private final PasswordEncoder passwordEncoder;

  @Override
  public UserDTO create(UserCreateDTO userCreateDto) {
    checkUserName(userCreateDto.getUsername());
    final User user = userMapper.createEntity(userCreateDto);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userMapper.toDto(userRepository.save(user));
  }

  private void checkUserName(String username) {
    userRepository.findByUsername(username).ifPresent(user -> {
      throw new BizException(ExceptionType.USERNAME_ALREADY_EXIST);
    });
  }

  @Override
  public UserDTO getUser(String username) {
    return userMapper.toDto(this.loadUserByUsername(username));
  }

  private User getById(String id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new BizException(ExceptionType.USER_NOT_FOUND));
  }

  @Override
  public User loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new BizException(ExceptionType.USER_NOT_FOUND));
  }

  @Override
  public String login(UserLoginDTO userLoginDTO) {
    final User user = loadUserByUsername(userLoginDTO.getUsername());
    final String providedPassword = userLoginDTO.getPassword();

    verifyUserCredentials(providedPassword, user);

    //生成JWT并且返回
    return JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(
            new Date(System.currentTimeMillis() + AuthenticationConfigConstants.EXPIRATION_TIME))
        .sign(Algorithm.HMAC512(AuthenticationConfigConstants.SECRET.getBytes()));
  }

  private void verifyUserCredentials(String providedPassword, User user) {
    if (! passwordEncoder.matches(providedPassword, user.getPassword())) {
      throw new BizException(ExceptionType.USER_PASSWORD_NOT_MATCH);
    }

    if (! user.isEnabled()) {
      throw new BizException(ExceptionType.USER_DISABLED);
    }

    if (! user.isAccountNonLocked()) {
      throw new BizException(ExceptionType.USER_ACCOUNT_LOCKED);
    }
  }

  /**
   * @param id        用户id(雪花算法生成)
   * @param updateDTO 更新用户信息
   * @return 更新后的用户信息
   */
  @Override
  public UserDTO update(String id, UserUpdateDTO updateDTO) {
    try {
      return userMapper.toDto(userRepository.save(userMapper.updateEntity(getById(id), updateDTO)));
    } catch (IllegalArgumentException e) {
      throw new BizException(ExceptionType.USER_UPDATE_FAILED);
    }
  }

  /**
   * 根据id删除用户
   *
   * @param id 用户id(雪花算法生成)
   */
  @Override
  public void delete(String id) {
    try {
      userRepository.deleteById(id);
    } catch (IllegalArgumentException e) {
      throw new BizException(ExceptionType.USER_DELETE_FAILED);
    }
  }

  @Override
  public Page<UserDTO> getUsers(QueryRequest<UserQueryDTO> queryRequest) {
    BooleanBuilder builder = new BooleanBuilder();
    Option.of(queryRequest.getQuery())
        .peek(query -> Option.of(query.getUsername())
            .peek(username -> builder.or(QUser.user.username.containsIgnoreCase(username))))
        .peek(query -> Option.of(query.getNickname())
            .peek(nickname -> builder.or(QUser.user.nickname.containsIgnoreCase(nickname))));
    return userRepository
        .findAll(builder, queryRequest.toPage())
        .map(userMapper :: toDto);
  }

  @Override
  public UserDTO getCurrentUser() {
    return userMapper.toDto(userRepository.findByUsername(
            SecurityContextHolder.getContext().getAuthentication().getName())
        .orElseThrow(() -> new BizException(ExceptionType.USER_NOT_FOUND)));
  }
}

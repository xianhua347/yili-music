package com.bilitech.yilimusic.service;

import com.bilitech.yilimusic.enums.ExceptionType;
import com.bilitech.yilimusic.exception.BizException;
import com.bilitech.yilimusic.mapper.UserMapper;
import com.bilitech.yilimusic.model.dto.user.UserCreateDTO;
import com.bilitech.yilimusic.model.dto.user.UserDTO;
import com.bilitech.yilimusic.model.dto.user.UserLoginRequest;
import com.bilitech.yilimusic.model.dto.user.UserQueryRequest;
import com.bilitech.yilimusic.model.dto.user.UserUpdateDTO;
import com.bilitech.yilimusic.model.enetity.QUser;
import com.bilitech.yilimusic.model.enetity.Role;
import com.bilitech.yilimusic.model.enetity.User;
import com.bilitech.yilimusic.repository.RoleRepository;
import com.bilitech.yilimusic.repository.UserRepository;
import com.bilitech.yilimusic.utils.JwtUtils;
import com.bilitech.yilimusic.utils.QueryRequest;
import com.bilitech.yilimusic.utils.jwt.payload.response.JwtResponse;
import com.querydsl.core.BooleanBuilder;
import io.vavr.control.Option;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
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

  private final JwtUtils jwtUtils;

  private final RefreshTokenService refreshTokenService;

  private final RoleRepository roleRepository;

  @Override
  public UserDTO create(UserCreateDTO userCreateDto) {
    checkUserName(userCreateDto.getUsername());
    Role role = roleRepository.findByName("ROLE_USER")
        .getOrElseThrow(() -> new BizException(ExceptionType.ROLE_NOT_FOUND));
    final User user = userMapper.createEntity(userCreateDto, role, passwordEncoder);
    return userMapper.toDto(userRepository.save(user));
  }

  private void checkUserName(String username) {
    userRepository.findByUsername(username).ifPresent(user -> {
      throw new BizException(ExceptionType.USERNAME_ALREADY_EXIST);
    });
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
  public JwtResponse login(UserLoginRequest userLoginDTO) {
    final User user = loadUserByUsername(userLoginDTO.getUsername());
    final String providedPassword = userLoginDTO.getPassword();

    verifyUserCredentials(providedPassword, user);

    String accessToken = jwtUtils.generateJwtToken(user);

    var refreshToken = refreshTokenService.createRefreshToken(user.id).getToken();

    return new JwtResponse(accessToken, refreshToken, LocalDateTime.now().plusHours(2));
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
  public Page<UserDTO> getUsers(QueryRequest<UserQueryRequest> queryRequest) {
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

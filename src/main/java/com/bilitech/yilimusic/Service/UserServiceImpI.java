package com.bilitech.yilimusic.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bilitech.yilimusic.Enums.ExceptionType;
import com.bilitech.yilimusic.DTO.UserCreateDTO;
import com.bilitech.yilimusic.DTO.UserDTO;
import com.bilitech.yilimusic.Mapper.UserMapper;
import com.bilitech.yilimusic.Repository.UserRepository;
import com.bilitech.yilimusic.config.AuthenticationConfigConstants;
import com.bilitech.yilimusic.enetity.User;
import com.bilitech.yilimusic.exception.BizException;
import java.util.Date;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author 陈现府
 */
@Service
public class UserServiceImpI implements UserService {

  private final UserRepository userRepository;

  private final UserMapper userMapper;

  private final PasswordEncoder passwordEncoder;

  public UserServiceImpI(UserRepository userRepository, UserMapper userMapper,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
  }


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

  @Override
  public User loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByUsername(username)
        .orElseThrow(() -> new BizException(ExceptionType.USER_NOT_FOUND));
  }

  @Override
  public String login(UserCreateDTO userCreateDto) {
    final User user = loadUserByUsername(userCreateDto.getUsername());
    final String providedPassword = userCreateDto.getPassword();

    if (! passwordEncoder.matches(providedPassword, user.getPassword())) {
      throw new BizException(ExceptionType.USER_PASSWORD_NOT_MATCH);
    }

    if (! user.isEnabled()) {
      throw new BizException(ExceptionType.USER_DISABLED);
    }

    if (! user.isAccountNonLocked()) {
      throw new BizException(ExceptionType.USER_ACCOUNT_LOCKED);
    }

    //生成JWT并且返回
    return JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(
            new Date(System.currentTimeMillis() + AuthenticationConfigConstants.EXPIRATION_TIME))
        .sign(Algorithm.HMAC512(AuthenticationConfigConstants.SECRET.getBytes()));
  }
}

package com.bilitech.yilimusic.controller;

import com.alicp.jetcache.anno.Cached;
import com.bilitech.yilimusic.exception.RefreshTokenException;
import com.bilitech.yilimusic.model.dto.user.UserLoginRequest;
import com.bilitech.yilimusic.model.enetity.RefreshToken;
import com.bilitech.yilimusic.repository.RefreshTokenRepository;
import com.bilitech.yilimusic.service.RefreshTokenService;
import com.bilitech.yilimusic.service.UserService;
import com.bilitech.yilimusic.utils.ApiResponse;
import com.bilitech.yilimusic.utils.JwtUtils;
import com.bilitech.yilimusic.utils.jwt.payload.response.JwtResponse;
import com.bilitech.yilimusic.utils.jwt.payload.response.RefreshTokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

  private final RefreshTokenRepository refreshTokenRepository;

  private final RefreshTokenService refreshTokenService;

  private final JwtUtils jwtUtils;

  private final UserService userService;


  /**
   * 登录
   *
   * @param userLoginRequest 用户登录信息
   * @return accessToken 访问令牌
   */
  @Cached(name = "auth-login-",key = "#userLoginRequest.getUsername()",expire = 7200)
  @Operation(summary = "登录")
  @PostMapping("login")
  public ApiResponse<JwtResponse> login(
      @RequestBody @Validated UserLoginRequest userLoginRequest) {
    return ApiResponse.success(userService.login(userLoginRequest));
  }

  /**
   * 根据刷新令牌获取token
   *
   * @param refreshToken 刷新令牌
   * @return RefreshTokenResponse 刷新令牌 + 访问令牌
   */

  @Cached(name = "auth-refreshToken-",key = "#refreshToken",expire = 7200)
  @Operation(summary = "根据刷新令牌获取token")
  @GetMapping("refreshToken")
  public ApiResponse
      <RefreshTokenResponse> refreshToken(@RequestParam String refreshToken) {
    return refreshTokenRepository
        .findByToken(refreshToken)
        .map(refreshTokenService :: verifyExpiration)
        .map(RefreshToken :: getUser)
        .map(user -> {
          String token = jwtUtils.generateJwtToken(user);
          return ApiResponse.success(new RefreshTokenResponse(token, refreshToken));
        })
        .getOrElseThrow(()-> new RefreshTokenException("刷新token不在数据库中，请重新登录！"));
  }
}

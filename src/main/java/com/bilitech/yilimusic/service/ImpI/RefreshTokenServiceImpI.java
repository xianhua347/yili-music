package com.bilitech.yilimusic.service.ImpI;

import com.bilitech.yilimusic.config.JwtSecurityProperties;
import com.bilitech.yilimusic.enums.ExceptionType;
import com.bilitech.yilimusic.exception.BizException;
import com.bilitech.yilimusic.exception.RefreshTokenException;
import com.bilitech.yilimusic.model.enetity.RefreshToken;
import com.bilitech.yilimusic.repository.RefreshTokenRepository;
import com.bilitech.yilimusic.repository.UserRepository;
import com.bilitech.yilimusic.service.RefreshTokenService;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpI implements RefreshTokenService {


  private final RefreshTokenRepository refreshTokenRepository;

  private final UserRepository userRepository;

  private final JwtSecurityProperties jwtSecurityProperties;


  @Override
  public RefreshToken createRefreshToken(String userId) {
    RefreshToken refreshToken = RefreshToken.builder()
        .user(userRepository.findById(userId)
            .orElseThrow(() -> new BizException(ExceptionType.USER_NOT_EXIST)))
        .expiryDate(LocalDateTime.now().plusSeconds(jwtSecurityProperties.getRefreshTokenExpirationSecond()))
        .token(UUID.randomUUID().toString())
        .build();

    return refreshTokenRepository.save(refreshToken);
  }

  @Override
  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
      refreshTokenRepository.delete(token);
      throw new RefreshTokenException(
          "刷新令牌已过期 请发起新的登录请求");
    }
    return token;
  }
}

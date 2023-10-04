package com.bilitech.yilimusic.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bilitech.yilimusic.config.JwtSecurityProperties;
import com.bilitech.yilimusic.model.enetity.User;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class JwtUtils {

  private final JwtSecurityProperties jwtSecurityProperties;

  public String generateTokenFromUsername(String username) {
    return JWT.create()
        .withSubject(username)
        .withExpiresAt(
            new Date(System.currentTimeMillis() + (jwtSecurityProperties.getAccessTokenExpirationSecond() * 1000))) // 将秒转换为毫秒
        .sign(Algorithm.HMAC512(jwtSecurityProperties.getSecret().getBytes()));
  }

  public String generateJwtToken(User user) {
    return generateTokenFromUsername(user.getUsername());
  }

  public  String  getUserNameFromJwtToken(String token) {
    return JWT.require(
            Algorithm.HMAC512(jwtSecurityProperties.getSecret().getBytes()))
        .build()
        .verify(token.replace(jwtSecurityProperties.getTokenStartWith(), ""))
        .getSubject();
  }
}

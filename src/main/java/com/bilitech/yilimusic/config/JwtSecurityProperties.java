package com.bilitech.yilimusic.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.jwt")
public class JwtSecurityProperties {

  /**
   * 令牌前缀，最后留个空格 Bearer
   */
  private String tokenStartWith;

  /**
   * jwt密钥
   */
  private String secret;

  /**
   * 令牌过期时间 此处单位/秒
   */
  private Long accessTokenExpirationSecond;

  /**
   * 刷新令牌过期时间 此处单位/秒
   */
  private Long refreshTokenExpirationSecond;
}

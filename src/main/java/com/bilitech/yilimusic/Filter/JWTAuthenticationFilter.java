package com.bilitech.yilimusic.Filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bilitech.yilimusic.Enums.ExceptionType;
import com.bilitech.yilimusic.config.AuthenticationConfigConstants;
import com.bilitech.yilimusic.enetity.User;
import com.bilitech.yilimusic.exception.BizException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * 用户注册 JWT token
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    try {
      final User user = new ObjectMapper()
          .readValue(request.getInputStream(), User.class);

      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              user.getUsername(),
              user.getPassword(),
              new ArrayList<>()

          )
      );
    } catch (IOException e) {
      throw new BizException(ExceptionType.FORBIDDEN);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain, Authentication auth) {
    String token = JWT.create()
        .withSubject(((User) auth.getPrincipal()).getUsername())
        .withExpiresAt(
            new Date(System.currentTimeMillis() + AuthenticationConfigConstants.EXPIRATION_TIME))
        .sign(Algorithm.HMAC512(AuthenticationConfigConstants.SECRET.getBytes()));

    response.addHeader(AuthenticationConfigConstants.HEADER_STRING,
        AuthenticationConfigConstants.TOKEN_PREFIX + token);
  }
}

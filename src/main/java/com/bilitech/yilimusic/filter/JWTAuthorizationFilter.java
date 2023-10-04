package com.bilitech.yilimusic.filter;

import static com.bilitech.yilimusic.utils.ResponseUtil.sendErrorResponse;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.bilitech.yilimusic.config.JwtSecurityProperties;
import com.bilitech.yilimusic.enums.ExceptionType;
import com.bilitech.yilimusic.model.enetity.User;
import com.bilitech.yilimusic.service.UserService;
import com.bilitech.yilimusic.utils.JwtUtils;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


/**
 * 用户权限 JWT token
 */

@Component
@Log4j2
@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {

  private final UserService userService;

  private final JwtSecurityProperties jwtSecurityProperties;

  private final JwtUtils jwtUtils;

  /**
   * 过滤器
   *
   * @param request  请求
   * @param response 响应
   * @param chain    过滤器链
   * @throws IOException      IO异常
   * @throws ServletException Servlet异常
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response,
      @NotNull FilterChain chain) throws IOException, ServletException {
    //拿到Authorization字段对应的信息
    final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

    //判断是否有token 如果没有token直接doFilter也就是放行 说明用户根本没有注册
    if (authorization == null || ! authorization.startsWith(
        jwtSecurityProperties.getTokenStartWith())) {
      chain.doFilter(request, response);
      return;
    }
    try {
      //拿到 header里面的token做验证
      UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(authorization);
      //把token塞到Spring Security上下文中
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      chain.doFilter(request, response);
    } catch (TokenExpiredException e) {
      log.warn("token 已过期 " + e);
      sendErrorResponse(response, HttpStatus.OK, ExceptionType.TOKEN_EXPIRED);
    }
  }

  /**
   * 验证前端传来的jwt token并且返回username+role信息构成的UsernamePasswordAuthenticationToken
   *
   * @param authorizationHeader 请求头
   * @return UsernamePasswordAuthenticationToken
   */
  private UsernamePasswordAuthenticationToken getAuthentication(String authorizationHeader) {
    //如果有token就进行解析
    if (authorizationHeader != null) {
      String username = jwtUtils.getUserNameFromJwtToken(authorizationHeader);
      //判断用户名是否为空 如果不为空就返回一个用户权限的token
      if (username != null) {
        User user = userService.loadUserByUsername(username);
        //返回一个用户权限的token
        return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
      }
    }
    return null;
  }
}

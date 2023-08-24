package com.bilitech.yilimusic.Filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bilitech.yilimusic.Service.UserService;
import com.bilitech.yilimusic.config.AuthenticationConfigConstants;
import com.bilitech.yilimusic.enetity.User;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


/**
 * 用户权限 JWT token
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
  private final UserService userService;

  public JWTAuthorizationFilter(AuthenticationManager authenticationManager,
      UserService userService) {
    super(authenticationManager);
    this.userService = userService;
  }

  /**
   * 过滤器
   * @param request 请求
   * @param response 响应
   * @param chain 过滤器链
   * @throws IOException IO异常
   * @throws ServletException Servlet异常
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws IOException, ServletException {
    //获取到token
    final String header = request.getHeader(AuthenticationConfigConstants.HEADER_STRING);

    //判断是否有token 如果没有token直接doFilter也就是放行 说明用户根本没有注册
    if (header == null || ! header.startsWith(AuthenticationConfigConstants.TOKEN_PREFIX)) {
      chain.doFilter(request, response);
      return;
    }
    //拿到 header里面的token做验证
    UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(header);
    //把token塞到Spring mvc上下文中
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    chain.doFilter(request, response);
  }

  /**
   * 获取用户权限的token
   * @param header 请求头
   * @return UsernamePasswordAuthenticationToken
   */
  private UsernamePasswordAuthenticationToken getAuthentication(String header) {
    //如果有token就进行解析
    if (header != null) {
      String username = JWT.require(
              Algorithm.HMAC512(AuthenticationConfigConstants.SECRET.getBytes()))
          .build()
          .verify(header.replace(AuthenticationConfigConstants.TOKEN_PREFIX, ""))
          .getSubject();
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

package com.bilitech.yilimusic.exception;

import cn.hutool.json.JSONUtil;
import com.bilitech.yilimusic.Enums.ExceptionType;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author admin
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {


  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException {
    response.setHeader("Access-Control-Allow-Origin", "*");
    response.setHeader("Cache-Control", "no-cache");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("application/json");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(ExceptionType.UNAUTHORIZED.getCode());
    errorResponse.setMessage(ExceptionType.UNAUTHORIZED.getMessage());
    response.getWriter().println(JSONUtil.parse(errorResponse));
    response.getWriter().flush();
  }
}

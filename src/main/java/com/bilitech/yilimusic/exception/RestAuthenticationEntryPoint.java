package com.bilitech.yilimusic.exception;

import static com.bilitech.yilimusic.utils.ResponseUtil.sendErrorResponse;

import com.bilitech.yilimusic.enums.ExceptionType;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
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
    sendErrorResponse(response, HttpStatus.UNAUTHORIZED, ExceptionType.UNAUTHORIZED);
  }
}

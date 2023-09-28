package com.bilitech.yilimusic.handler;

import com.bilitech.yilimusic.enums.ExceptionType;
import com.bilitech.yilimusic.exception.BizException;
import com.bilitech.yilimusic.exception.ErrorResponse;
import com.bilitech.yilimusic.exception.RefreshTokenException;
import java.util.concurrent.ExecutionException;
import lombok.extern.log4j.Log4j2;
import me.zhyd.oauth.exception.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

  @ExceptionHandler(value = BizException.class)
  public ResponseEntity<ErrorResponse> bizExceptionHandler(BizException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(e.getCode());
    errorResponse.setMessage(e.getMessage());
    return ResponseEntity.status(HttpStatus.OK).body(errorResponse);
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ErrorResponse> exceptionHandler() {
    final ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(ExceptionType.INNER_ERROR.getCode());
    errorResponse.setMessage(ExceptionType.INNER_ERROR.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
  }

  @ExceptionHandler(value = AccessDeniedException.class)
  public ResponseEntity<ErrorResponse> accessDeniedHandler() {
    final ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(ExceptionType.FORBIDDEN.getCode());
    errorResponse.setMessage(ExceptionType.FORBIDDEN.getMessage());
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorResponse> methodNotAllowed() {
    final ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(ExceptionType.METHOD_NOT_SUPPORTED.getCode());
    errorResponse.setMessage(ExceptionType.METHOD_NOT_SUPPORTED.getMessage());
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> methodArgumentNotValidHandler(
      MethodArgumentNotValidException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    e.getBindingResult().getAllErrors().forEach((error) -> {
      errorResponse.setCode(ExceptionType.BAD_REQUEST.getCode());
      errorResponse.setMessage(error.getDefaultMessage());
    });
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }

  /**
   * 处理第三方登录异常
   *
   * @param e 异常
   * @return 异常返回体
   */
  @ExceptionHandler(value = AuthException.class)
  public ResponseEntity<ErrorResponse> bizExceptionHandler(AuthException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(e.getErrorCode());
    errorResponse.setMessage(e.getErrorMsg());
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
  }

  /**
   * 处理刷新token过期异常
   *
   * @param e 异常
   * @return 异常返回体
   */
  @ExceptionHandler(value = RefreshTokenException.class)
  public ResponseEntity<ErrorResponse> refreshTokenExceptionHandler(RefreshTokenException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(ExceptionType.REFRESH_TOKEN_EXPIRED.getCode());
    errorResponse.setMessage(e.getMessage());
    return ResponseEntity.ok().body(errorResponse);
  }

  @ExceptionHandler(value = {ExecutionException.class, InterruptedException.class})
  public ResponseEntity<ErrorResponse> concurrentExecution(Exception e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(e.hashCode());
    errorResponse.setMessage(e.getMessage());
    return ResponseEntity.ok().body(errorResponse);
  }
}

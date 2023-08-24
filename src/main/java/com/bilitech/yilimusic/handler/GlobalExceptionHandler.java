package com.bilitech.yilimusic.handler;

import com.bilitech.yilimusic.Enums.ExceptionType;
import com.bilitech.yilimusic.exception.BizException;
import com.bilitech.yilimusic.exception.ErrorResponse;
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
}

package com.bilitech.yilimusic.exception;

import com.bilitech.yilimusic.Enums.ExceptionType;
import lombok.Getter;

/**
 * 业务异常处理
 */
@Getter
public class BizException extends RuntimeException {

  private final Integer code;

  public BizException(ExceptionType exceptionType) {
    super(exceptionType.getMessage());
    this.code = exceptionType.getCode();
  }

}

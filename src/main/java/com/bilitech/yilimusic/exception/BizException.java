package com.bilitech.yilimusic.exception;

/**
 * 业务异常处理
 */
public class BizException extends RuntimeException{

    private final Integer code;

    public BizException(ExceptionType exceptionType) {
        super(exceptionType.getMessage());
        this.code = exceptionType.getCode();
    }

    public Integer getCode() {
        return code;
    }
}

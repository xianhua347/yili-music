package com.bilitech.yilimusic.Enums;

import lombok.Getter;

/**
 * 业务异常Code
 * @author 陈现府
 */
@Getter
public enum ExceptionType {

    INNER_ERROR(500,"内部错误"),

    UNAUTHORIZED(401,"未登录"),

    BAD_REQUEST(400,"请求错误"),

    FORBIDDEN(403,"无权操作"),

    NOT_FOUND(404,"未找到"),

    USER_NAME_DUPLICATE(4000101,"用户名重复"),
    USERNAME_ALREADY_EXIST(4000101,"用户已存在"),

    USER_NOT_FOUND(40401001,"用户名不存在"),

    METHOD_NOT_SUPPORTED(405,"此请求不支持"),

    USER_PASSWORD_NOT_MATCH(4000102,"用户名或密码错误"),

    USER_DISABLED(4000103,"用户已被禁用"),
    USER_ACCOUNT_LOCKED(4000104,"用户已被锁定");

    private final Integer code;

    private final String message;

    ExceptionType(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}

package com.bilitech.yilimusic.exception;

import lombok.Data;

/**
 * @author 陈现府
 */
@Data
public class ErrorResponse {

    private Integer code;

    private String message;
}

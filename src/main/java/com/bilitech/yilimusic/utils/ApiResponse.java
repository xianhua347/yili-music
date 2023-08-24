package com.bilitech.yilimusic.utils;

import java.io.Serializable;
import lombok.Data;

@Data
public class ApiResponse<T> implements Serializable {

  private Integer code;
  private String message;
  private T data;

  private ApiResponse(Integer code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(200, "Success", data);
  }

  public static <T> ApiResponse<T> success() {
    return new ApiResponse<>(200, "Success", null);
  }
}

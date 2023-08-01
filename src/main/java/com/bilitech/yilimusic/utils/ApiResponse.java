package com.bilitech.yilimusic.utils;

import java.io.Serializable;
import lombok.Data;

/**
 * @author admin
 */
import lombok.Data;
import org.springframework.http.HttpStatus;

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

  public static <T> ApiResponse<T> error(HttpStatus status, String message) {
    return new ApiResponse<>(status.value(), message, null);
  }

  public static <T> ApiResponse<T> error(HttpStatus status, String message, T data) {
    return new ApiResponse<>(status.value(), message, data);
  }

  public boolean isSuccess() {
    return this.code != null && this.code.equals(HttpStatus.OK.value());
  }

  public boolean isError() {
    return ! isSuccess();
  }

  public boolean hasData() {
    return this.data != null;
  }
}


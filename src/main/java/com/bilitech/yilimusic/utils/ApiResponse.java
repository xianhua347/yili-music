package com.bilitech.yilimusic.utils;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@ApiResponses
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> implements Serializable {

  private  Integer code;
  private String message;
  private T data;

  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(200, "Success", data);
  }

  public static <T> ApiResponse<T> success() {
    return new ApiResponse<>(200, "Success", null);
  }
}

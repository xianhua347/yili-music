package com.bilitech.yilimusic.utils.jwt.payload.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenRequest {
  @NotBlank
  private String refreshToken;
}

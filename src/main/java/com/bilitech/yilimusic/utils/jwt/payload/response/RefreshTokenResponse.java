package com.bilitech.yilimusic.utils.jwt.payload.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenResponse implements Serializable {

  private String accessToken;

  private String refreshToken;
}

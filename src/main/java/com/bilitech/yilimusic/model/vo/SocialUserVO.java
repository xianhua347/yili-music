package com.bilitech.yilimusic.model.vo;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialUserVO implements Serializable {

  private String source;

  private String accessToken;

  private String refreshToken;
}

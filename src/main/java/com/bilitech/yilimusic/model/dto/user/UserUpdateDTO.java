package com.bilitech.yilimusic.model.dto.user;

import java.io.Serializable;
import lombok.Getter;

@Getter
public class UserUpdateDTO implements Serializable {

  private String username;

  private String nickname;

  private String gender;

  private String avatar;
}

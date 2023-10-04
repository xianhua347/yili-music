package com.bilitech.yilimusic.model.vo;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserVO extends BaseVO {

  private String username;

  private String nickname;

  private String gender;

  private Boolean locked;

  private Boolean enabled;

  private String avatar;

  private List<RoleVO> roles;

  private List<SocialUserVO> socialUsers;
}

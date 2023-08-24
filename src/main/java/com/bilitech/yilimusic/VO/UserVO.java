package com.bilitech.yilimusic.VO;

import com.bilitech.yilimusic.DTO.user.RoleDTO;
import com.bilitech.yilimusic.Enums.Gender;
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

  private Gender gender;

  private Boolean locked;

  private Boolean enabled;

  private List<RoleDTO> roles;
}

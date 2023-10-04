package com.bilitech.yilimusic.model.dto.user;

import com.bilitech.yilimusic.enums.Gender;
import com.bilitech.yilimusic.model.dto.BaseDTO;
import com.bilitech.yilimusic.model.dto.role.RoleDTO;
import com.bilitech.yilimusic.model.enetity.SocialUser;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UserDTO extends BaseDTO {

  private String username;

  private String nickname;

  private List<RoleDTO> roles;

  private List<SocialUser> socialUsers;

  private String avatar;

  private Gender gender;

  private Boolean locked;

  private Boolean enabled;

  private String lastLoginIp;

  private LocalDateTime lastLoginTime;
}

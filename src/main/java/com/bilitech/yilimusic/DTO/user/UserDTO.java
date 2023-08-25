package com.bilitech.yilimusic.DTO.user;

import com.bilitech.yilimusic.DTO.BaseDTO;
import com.bilitech.yilimusic.DTO.role.RoleDTO;
import com.bilitech.yilimusic.enums.Gender;
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

  private Gender gender;

  private Boolean locked;

  private Boolean enabled;

  private String lastLoginIp;

  private LocalDateTime lastLoginTime;
}

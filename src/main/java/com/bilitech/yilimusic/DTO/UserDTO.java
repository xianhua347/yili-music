package com.bilitech.yilimusic.DTO;

import com.bilitech.yilimusic.Enums.Gender;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
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

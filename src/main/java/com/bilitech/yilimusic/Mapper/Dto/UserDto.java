package com.bilitech.yilimusic.Mapper.Dto;

import com.bilitech.yilimusic.Enums.Gender;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class UserDto {
  private String id;

  private String username;

  private String nickname;

  private List<RoleDto> roles;

  private Gender gender;

  private Boolean locked;

  private Boolean enabled;

  private String lastLoginIp;

  private LocalDateTime lastLoginTime;
}

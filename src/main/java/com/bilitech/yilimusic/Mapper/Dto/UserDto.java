package com.bilitech.yilimusic.Mapper.Dto;

import java.util.List;
import lombok.Data;

@Data
public class UserDto {

  private String id;

  private String username;

  private String nickname;

  private List<RoleDto> roles;
}

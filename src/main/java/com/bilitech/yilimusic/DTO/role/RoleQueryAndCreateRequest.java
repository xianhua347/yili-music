package com.bilitech.yilimusic.DTO.role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleQueryAndCreateRequest {

  private String name;

  private String title;
}

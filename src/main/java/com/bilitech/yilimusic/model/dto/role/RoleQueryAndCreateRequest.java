package com.bilitech.yilimusic.model.dto.role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleQueryAndCreateRequest {

  private String name;

  private String title;
}

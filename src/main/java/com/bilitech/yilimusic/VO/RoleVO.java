package com.bilitech.yilimusic.VO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RoleVO extends BaseVO {

  private String id;

  private String name;

  private String title;
}

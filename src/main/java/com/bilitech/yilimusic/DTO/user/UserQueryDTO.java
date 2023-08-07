package com.bilitech.yilimusic.DTO.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;


/**
 * 用户查询DTO
 */
@Getter
@Schema(name = "UserQueryDTO", description = "用户查询DTO")
public class UserQueryDTO {

  @Schema(description = "用户名")
  private String username;

  @Schema(description = "昵称")
  private String nickname;
}

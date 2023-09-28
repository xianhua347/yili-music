package com.bilitech.yilimusic.model.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 用户查询DTO
 */
@Getter
@AllArgsConstructor
@Schema(name = "UserQueryRequest", description = "用户查询")
public class UserQueryRequest {

  @Schema(description = "用户名")
  private String username;

  @Schema(description = "昵称")
  private String nickname;
}

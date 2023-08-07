package com.bilitech.yilimusic.DTO.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserLoginDTO {

  @NotBlank(message = "用户名不能为空")
  @Size(min = 4, max = 64, message = "用户名长度应该在4个字符到64个字符之间")
  private String username;

  @NotBlank(message = "用户名不能为空")
  @Size(min = 6, max = 64, message = "密码长度应该在4个字符到64个字符之间")
  private String password;
}

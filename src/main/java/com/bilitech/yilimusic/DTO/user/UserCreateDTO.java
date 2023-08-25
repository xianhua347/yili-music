package com.bilitech.yilimusic.DTO.user;

import com.bilitech.yilimusic.enums.Gender;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * 用户创建DTO
 */
@Data
public class UserCreateDTO {

  /**
   * 用户名
   */
  @NotBlank(message = "用户名不能为空")
  @Size(min = 4, max = 64, message = "用户名长度应该在4个字符到64个字符之间")
  private String username;

  /**
   * 密码
   */
  @NotBlank(message = "用户名不能为空")
  @Size(min = 6, max = 64, message = "密码长度应该在4个字符到64个字符之间")
  private String password;

  /**
   * 昵称
   */
  private String nickname;

  /**
   * 性别
   */
  private Gender gender;
}

package com.bilitech.yilimusic.Dto;

import com.bilitech.yilimusic.Enums.Gender;
import lombok.Data;

import java.util.Date;
import java.util.List;

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

    private Date lastLoginTime;
}

package com.bilitech.yilimusic.VO;

import com.bilitech.yilimusic.Mapper.Dto.RoleDto;
import com.bilitech.yilimusic.Enums.Gender;
import lombok.Data;

import java.util.List;

@Data
public class UserVO extends BaseVO{

    private String username;

    private String nickname;

    private Gender gender;

    private Boolean locked;

    private Boolean enabled;

    private List<RoleDto> roles;
}

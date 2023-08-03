package com.bilitech.yilimusic.Mapper;

import com.bilitech.yilimusic.DTO.UserCreateDTO;
import com.bilitech.yilimusic.DTO.UserDTO;
import com.bilitech.yilimusic.VO.UserVO;
import com.bilitech.yilimusic.enetity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

  UserDTO toDto(User user);

  UserVO toVo(UserDTO userDto);

  UserVO toVo(User user);

  User createEntity(UserCreateDTO userCreateDto);
}

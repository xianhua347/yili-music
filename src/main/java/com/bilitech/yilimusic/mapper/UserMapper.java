package com.bilitech.yilimusic.mapper;

import com.bilitech.yilimusic.DTO.user.UserCreateDTO;
import com.bilitech.yilimusic.DTO.user.UserDTO;
import com.bilitech.yilimusic.DTO.user.UserUpdateDTO;
import com.bilitech.yilimusic.VO.UserVO;
import com.bilitech.yilimusic.enetity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

  UserDTO toDto(User user);

  UserVO toVo(UserDTO userDto);

  UserVO toVo(User user);

  User createEntity(UserCreateDTO userCreateDto);

  User updateEntity(@MappingTarget User user, UserUpdateDTO userUpdateDTO);
}

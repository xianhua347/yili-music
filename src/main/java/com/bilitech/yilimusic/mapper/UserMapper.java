package com.bilitech.yilimusic.mapper;

import com.bilitech.yilimusic.model.dto.user.UserCreateDTO;
import com.bilitech.yilimusic.model.dto.user.UserDTO;
import com.bilitech.yilimusic.model.dto.user.UserUpdateDTO;
import com.bilitech.yilimusic.model.enetity.Role;
import com.bilitech.yilimusic.model.enetity.User;
import com.bilitech.yilimusic.model.vo.UserVO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

  @AfterMapping
  default void setPassword(@MappingTarget User entity, PasswordEncoder passwordEncoder) {
    entity.setPassword(passwordEncoder.encode(entity.getPassword()));
  }

  UserDTO toDto(User user);
  @Mapping(source = "gender.display", target = "gender")
  UserVO toVo(UserDTO userDto);

  UserVO toVo(User user);

  @Mappings({
      @Mapping(target = "id", ignore = true),
      @Mapping(target = "createdTime", ignore = true),
      @Mapping(target = "updatedTime", ignore = true),
      @Mapping(target = "enabled", ignore = true),
      @Mapping(target = "lastLoginIp", ignore = true),
      @Mapping(target = "roles", expression = "java(List.of(role))"),
      @Mapping(target = "lastLoginTime", ignore = true),
      @Mapping(target = "locked", ignore = true),
      @Mapping(target = "avatar", ignore = true),
      @Mapping(target = "socialUsers", ignore = true),
      @Mapping(target = "uuid", ignore = true)
  })
  User createEntity(UserCreateDTO dto, Role role, PasswordEncoder passwordEncoder);

  @Mappings({
      @Mapping(target = "createdTime", ignore = true),
      @Mapping(target = "enabled", ignore = true),
      @Mapping(target = "id", ignore = true),
      @Mapping(target = "lastLoginIp", ignore = true),
      @Mapping(target = "lastLoginTime", ignore = true),
      @Mapping(target = "locked", ignore = true),
      @Mapping(target = "password", ignore = true),
      @Mapping(target = "roles", ignore = true),
      @Mapping(target = "updatedTime", ignore = true),
      @Mapping(target = "socialUsers", ignore = true),
      @Mapping(target = "uuid", ignore = true)
  })
  User updateEntity(@MappingTarget User user, UserUpdateDTO userUpdateDTO);
}

package com.bilitech.yilimusic.Mapper;

import com.bilitech.yilimusic.Mapper.Dto.UserCreateDto;
import com.bilitech.yilimusic.Mapper.Dto.UserDto;
import com.bilitech.yilimusic.enetity.User;
import com.bilitech.yilimusic.VO.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
@SuppressWarnings("all")
public interface UserMapper {

    UserDto toDto(User user);

    UserVO toVo(UserDto userDto);
    UserVO toVo(User user);
    User createEntity(UserCreateDto userCreateDto);
}

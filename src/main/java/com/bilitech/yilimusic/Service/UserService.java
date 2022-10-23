package com.bilitech.yilimusic.Service;


import com.bilitech.yilimusic.Mapper.Dto.UserCreateDto;
import com.bilitech.yilimusic.Mapper.Dto.UserDto;

public interface UserService {

    UserDto create(UserCreateDto userCreateDto);

    UserDto get(String id);
}

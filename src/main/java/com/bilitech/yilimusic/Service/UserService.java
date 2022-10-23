package com.bilitech.yilimusic.Service;


import com.bilitech.yilimusic.Dto.UserDto;

public interface UserService {

    UserDto findByUsername(String username);

    UserDto get(String id);
}

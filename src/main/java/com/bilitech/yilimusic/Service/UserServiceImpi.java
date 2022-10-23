package com.bilitech.yilimusic.Service;

import com.bilitech.yilimusic.Dto.UserDto;
import com.bilitech.yilimusic.Mapper.UserMapper;
import com.bilitech.yilimusic.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpi implements UserService{

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpi(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserDto findByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsername(username));
    }

    @Override
    public UserDto get(String id) {
       return userMapper.toDto(userRepository.getById(id));
    }
}

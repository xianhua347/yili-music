package com.bilitech.yilimusic.Service;

import com.bilitech.yilimusic.Mapper.Dto.UserCreateDto;
import com.bilitech.yilimusic.Mapper.Dto.UserDto;
import com.bilitech.yilimusic.Mapper.UserMapper;
import com.bilitech.yilimusic.Repository.UserRepository;
import com.bilitech.yilimusic.enetity.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpi implements UserService{

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpi(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        checkUserName(userCreateDto.getUsername());
        final User user = userMapper.createEntity(userCreateDto);
       return userMapper.toDto(userRepository.save(user));
    }

    private void checkUserName(String username){
        final Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            throw  new RuntimeException("用户已经存在！");
        }
    }

    @Override
    public UserDto get(String id) {
        final User byId = userRepository.getById(id);
        System.out.println(byId);
        return userMapper.toDto(userRepository.getById(id));
    }
}

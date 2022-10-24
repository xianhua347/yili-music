package com.bilitech.yilimusic.Service;

import com.bilitech.yilimusic.Mapper.Dto.UserCreateDto;
import com.bilitech.yilimusic.Mapper.Dto.UserDto;
import com.bilitech.yilimusic.Mapper.UserMapper;
import com.bilitech.yilimusic.Repository.UserRepository;
import com.bilitech.yilimusic.enetity.User;
import com.bilitech.yilimusic.exception.BizException;
import com.bilitech.yilimusic.exception.ExceptionType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpi implements UserService{

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpi(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDto create(UserCreateDto userCreateDto) {
        checkUserName(userCreateDto.getUsername());
        final User user = userMapper.createEntity(userCreateDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent())
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        return user.get();
    }
}

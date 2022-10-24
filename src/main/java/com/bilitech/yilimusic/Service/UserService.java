package com.bilitech.yilimusic.Service;


import com.bilitech.yilimusic.Mapper.Dto.UserCreateDto;
import com.bilitech.yilimusic.Mapper.Dto.UserDto;
import com.bilitech.yilimusic.enetity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    UserDto create(UserCreateDto userCreateDto);

    UserDto get(String id);

    @Override
    User loadUserByUsername(String username) throws UsernameNotFoundException;

}

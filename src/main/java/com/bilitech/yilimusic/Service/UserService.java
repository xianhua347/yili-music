package com.bilitech.yilimusic.Service;


import com.bilitech.yilimusic.DTO.UserCreateDTO;
import com.bilitech.yilimusic.DTO.UserDTO;
import com.bilitech.yilimusic.enetity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

  UserDTO create(UserCreateDTO userCreateDto);

  UserDTO getUser(String username);

  String login(UserCreateDTO userCreateDto);

  @Override
  User loadUserByUsername(String username) throws UsernameNotFoundException;
}

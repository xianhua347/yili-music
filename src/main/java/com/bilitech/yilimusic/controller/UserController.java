package com.bilitech.yilimusic.controller;

import com.bilitech.yilimusic.Service.UserService;
import com.bilitech.yilimusic.Mapper.UserMapper;
import com.bilitech.yilimusic.VO.UserVO;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
public class UserController {
    final private UserMapper userMapper;

    final private UserService userService;

    public UserController(UserMapper userMapper, UserService userService) {
        this.userMapper = userMapper;
        this.userService = userService;
    }


    @GetMapping("/name/{username}")
    public UserVO findByUsername(@PathVariable String username){
        return userMapper.toVo(userService.findByUsername(username));
    }

    @GetMapping("/{id}")
    UserVO get(@PathVariable String id) {
        return userMapper.toVo(userService.get(id));
    }

}

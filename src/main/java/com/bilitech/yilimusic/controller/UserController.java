package com.bilitech.yilimusic.controller;

import com.bilitech.yilimusic.Mapper.Dto.UserCreateDto;
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


    @PostMapping("/")
    public UserVO create(@RequestBody UserCreateDto userCreateDto){
        return userMapper.toVo(userService.create(userCreateDto));
    }

    @GetMapping("/{id}")
    UserVO get(@PathVariable String id) {
        return userMapper.toVo(userService.get(id));
    }

}

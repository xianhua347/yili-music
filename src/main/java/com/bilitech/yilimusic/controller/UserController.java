package com.bilitech.yilimusic.controller;

import com.bilitech.yilimusic.DTO.UserCreateDTO;
import com.bilitech.yilimusic.Mapper.UserMapper;
import com.bilitech.yilimusic.Service.UserService;
import com.bilitech.yilimusic.VO.UserVO;
import com.bilitech.yilimusic.utils.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 */
@RestController()
@RequestMapping("users")
public class UserController {

  final private UserMapper userMapper;

  final private UserService userService;

  public UserController(UserMapper userMapper, UserService userService) {
    this.userMapper = userMapper;
    this.userService = userService;
  }

  @PostMapping("register")
  public ApiResponse<UserVO> create(@RequestBody UserCreateDTO userCreateDto) {
    return ApiResponse.success(userMapper.toVo(userService.create(userCreateDto)));
  }

  @GetMapping("{name}")
  public ApiResponse<UserVO> get(@PathVariable String name) {
    System.out.println(userService.getUser(name).toString());
    return ApiResponse.success(userMapper.toVo(userService.getUser(name)));
  }

  @PostMapping("login")
  public ApiResponse<String> login(@RequestBody UserCreateDTO userCreateDto) {
    return ApiResponse.success(userService.login(userCreateDto));
  }
}

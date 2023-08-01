package com.bilitech.yilimusic.controller;

import com.bilitech.yilimusic.Mapper.Dto.UserCreateDto;
import com.bilitech.yilimusic.Mapper.UserMapper;
import com.bilitech.yilimusic.Service.UserService;
import com.bilitech.yilimusic.VO.UserVO;
import com.bilitech.yilimusic.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/users")
public class UserController {

  final private UserMapper userMapper;

  final private UserService userService;

  public UserController(UserMapper userMapper, UserService userService) {
    this.userMapper = userMapper;
    this.userService = userService;
  }


  @PostMapping("/")
  public ResponseEntity<ApiResponse<UserVO>> create(@RequestBody UserCreateDto userCreateDto) {
    return ResponseEntity.ok(
        ApiResponse.success(userMapper.toVo(userService.create(userCreateDto))));
  }

  @GetMapping("/{name}")
  UserVO get(@PathVariable String name) {
    return userMapper.toVo(userService.loadUserByUsername(name));
  }

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<String>> login(@RequestBody UserCreateDto userCreateDto) {
    String token = userService.login(userCreateDto);
    return ResponseEntity.ok(ApiResponse.success(token));
  }
}

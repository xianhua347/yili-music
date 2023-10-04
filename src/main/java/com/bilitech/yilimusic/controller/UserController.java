package com.bilitech.yilimusic.controller;


import com.bilitech.yilimusic.mapper.UserMapper;
import com.bilitech.yilimusic.model.dto.user.UserCreateDTO;
import com.bilitech.yilimusic.model.dto.user.UserQueryRequest;
import com.bilitech.yilimusic.model.dto.user.UserUpdateDTO;
import com.bilitech.yilimusic.model.vo.UserVO;
import com.bilitech.yilimusic.service.UserService;
import com.bilitech.yilimusic.utils.ApiResponse;
import com.bilitech.yilimusic.utils.QueryRequest;
import com.bilitech.yilimusic.utils.QueryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import javax.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author admin
 */
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

  final private UserMapper userMapper;

  final private UserService userService;

  /**
   * 注册
   *
   * @param userCreateDto 用户注册信息
   * @return 注册成功
   */
  @Operation(summary = "注册")
  @PostMapping("register")
  public ApiResponse<UserVO> create(
      @RequestBody @Validated UserCreateDTO userCreateDto) {
    return ApiResponse.success(userMapper.toVo(userService.create(userCreateDto)));
  }

  /**
   * 删除用户
   *
   * @param id 用户id(雪花算法生成)
   * @return 删除成功
   */
  @Operation(summary = "删除用户")
  @DeleteMapping("{id}")
  @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
  public ApiResponse<Void> delete(
      @Parameter(description = "用户id（雪花算法生成）") @PathVariable String id) {
    userService.delete(id);
    return ApiResponse.success();
  }

  /**
   * 更新用户信息
   *
   * @param id   用户id(雪花算法生成)
   * @param user 更新用户信息
   * @return 更新后的用户信息
   */
  @Operation(summary = "更新用户信息")
  @PutMapping("{id}")
  @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
  public ApiResponse<UserVO> update(
      @Parameter(description = "用户id（雪花算法生成）") @PathVariable String id,
      @RequestBody @Validated UserUpdateDTO user) {
    return ApiResponse.success(userMapper.toVo(userService.update(id, user)));
  }

  /**
   * 分页查询用户信息
   *
   * @param queryRequest 查询条件
   * @return 查询结果
   */
  @Operation(summary = "分页查询用户信息")
  @PostMapping("search")
  @RolesAllowed("ROLE_ADMIN")
  public ApiResponse<QueryResponse<UserVO>> search(
      @RequestBody @Validated QueryRequest<UserQueryRequest> queryRequest) {
    return ApiResponse.success(QueryResponse.of(
        userService
            .getUsers(queryRequest)
            .map(userMapper::toVo)
    ));
  }

  /**
   * 获取当前用户信息
   *
   * @return 获取当前用户信息
   */
  @Operation(summary = "获取当前用户信息")
  @GetMapping("/me")
  @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
  public ApiResponse<UserVO> getCurrentUser() {
    return ApiResponse.success(userMapper.toVo(userService.getCurrentUser()));
  }
}

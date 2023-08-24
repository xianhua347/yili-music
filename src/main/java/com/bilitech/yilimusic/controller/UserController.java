package com.bilitech.yilimusic.controller;

import com.bilitech.yilimusic.DTO.user.UserCreateDTO;
import com.bilitech.yilimusic.DTO.user.UserLoginDTO;
import com.bilitech.yilimusic.DTO.user.UserQueryDTO;
import com.bilitech.yilimusic.DTO.user.UserUpdateDTO;
import com.bilitech.yilimusic.Mapper.UserMapper;
import com.bilitech.yilimusic.Service.UserService;
import com.bilitech.yilimusic.VO.UserVO;
import com.bilitech.yilimusic.utils.ApiResponse;
import com.bilitech.yilimusic.utils.QueryRequest;
import com.bilitech.yilimusic.utils.QueryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import javax.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
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
@RestController()
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

  final private UserMapper userMapper;

  final private UserService userService;

  @NotNull
  private static QueryResponse<UserVO> getPageQueryResponse(Page<UserVO> user) {
    return QueryResponse.of(
        user.getTotalElements(), user.getTotalPages(),
        user.getNumber(), user.getSize(), user.getContent());
  }

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
   * 获取用户信息根据用户名
   *
   * @param name 用户名
   * @return 用户信息
   */
  @Operation(summary = "获取用户信息根据用户名")
  @GetMapping("{name}")
  @RolesAllowed("ROLE_ADMIN")
  public ApiResponse<UserVO> get(
      @Parameter(description = "用户名") @PathVariable String name) {
    return ApiResponse.success(userMapper.toVo(userService.getUser(name)));
  }

  /**
   * 登录
   *
   * @param userLoginDTO 用户登录信息
   * @return token
   */
  @Operation(summary = "登录")
  @PostMapping("login")
  public ApiResponse<String> login(
      @RequestBody @Validated UserLoginDTO userLoginDTO) {
    return ApiResponse.success(userService.login(userLoginDTO));
  }

  /**
   * 删除用户
   *
   * @param id 用户id(雪花算法生成)
   * @return 删除成功
   */
  @Operation(summary = "删除用户")
  @DeleteMapping("{id}")
  @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
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
  @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
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
      @RequestBody @Validated QueryRequest<UserQueryDTO> queryRequest) {
    var result = getPageQueryResponse(
        userService
            .getUsers(queryRequest)
            .map(userMapper :: toVo)
    );
    return ApiResponse.success(result);
  }

  /**
   * 获取当前用户信息
   *
   * @return 获取当前用户信息
   */
  @Operation(summary = "获取当前用户信息")
  @GetMapping("/me")
  @RolesAllowed({"ROLE_ADMIN","ROLE_USER"})
  public ApiResponse<UserVO> getCurrentUser() {
    return ApiResponse.success(userMapper.toVo(userService.getCurrentUser()));
  }
}

package com.bilitech.yilimusic.service;


import com.bilitech.yilimusic.model.dto.user.UserCreateDTO;
import com.bilitech.yilimusic.model.dto.user.UserDTO;
import com.bilitech.yilimusic.model.dto.user.UserLoginRequest;
import com.bilitech.yilimusic.model.dto.user.UserQueryRequest;
import com.bilitech.yilimusic.model.dto.user.UserUpdateDTO;
import com.bilitech.yilimusic.model.enetity.User;
import com.bilitech.yilimusic.utils.QueryRequest;
import com.bilitech.yilimusic.utils.jwt.payload.response.JwtResponse;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

  UserDTO create(UserCreateDTO userCreateDto);

  /**
   * @param userLoginRequest 登录参数
   * @return refreshToken + refreshToken
   */
  JwtResponse login(UserLoginRequest userLoginRequest);

  /**
   * @param id        用户id(雪花算法生成)
   * @param updateDTO 更新用户信息
   * @return 更新后的用户信息
   */
  UserDTO update(String id, UserUpdateDTO updateDTO);

  /**
   * 根据id删除用户
   *
   * @param id 用户id(雪花算法生成)
   */
  void delete(String id);

  /**
   * @param queryRequest 查询参数
   * @return 结果集合
   */
  Page<UserDTO> getUsers(QueryRequest<UserQueryRequest> queryRequest);

  /**
   * 获取当前登录用户
   *
   * @return 当前登录用户
   */
  UserDTO getCurrentUser();

  /**
   * @param username the username identifying the user whose data is required.
   * @return a fully populated user record (never <code>null</code>)
   */
  @Override
  User loadUserByUsername(String username) throws UsernameNotFoundException;
}

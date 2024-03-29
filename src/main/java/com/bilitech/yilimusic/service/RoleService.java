package com.bilitech.yilimusic.service;

import com.bilitech.yilimusic.model.dto.role.RoleQueryAndCreateRequest;
import com.bilitech.yilimusic.model.enetity.Role;
import com.bilitech.yilimusic.utils.QueryRequest;
import com.bilitech.yilimusic.utils.QueryResponse;

public interface RoleService {

  QueryResponse<Role> findAll(Integer page, Integer size);

  QueryResponse<Role> findByKeyword(QueryRequest<RoleQueryAndCreateRequest> request);

  Role save(RoleQueryAndCreateRequest role);

  void deleteById(String id);

  void updateRole(Role entity);
}

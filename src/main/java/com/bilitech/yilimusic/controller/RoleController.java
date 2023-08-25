package com.bilitech.yilimusic.controller;

import com.bilitech.yilimusic.DTO.role.RoleQueryAndCreateRequest;
import com.bilitech.yilimusic.enetity.Role;
import com.bilitech.yilimusic.mapper.RoleMapper;
import com.bilitech.yilimusic.service.RoleService;
import com.bilitech.yilimusic.utils.ApiResponse;
import com.bilitech.yilimusic.utils.QueryRequest;
import com.bilitech.yilimusic.utils.QueryResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
@Log4j2
public class RoleController {

  private final RoleService roleService;

  private final RoleMapper roleMapper;

  @GetMapping
  @Operation(summary = "获取所有角色")
  public ApiResponse<QueryResponse<Role>> getAllRoles(
      @RequestParam(defaultValue = "1", required = false) Integer page,
      @RequestParam(defaultValue = "10", required = false) Integer size) {
    return ApiResponse.success(roleService.findAll(page, size));
  }

  @PostMapping
  @Operation(summary = "创建角色")
  public ApiResponse<Role> createRole(@RequestBody RoleQueryAndCreateRequest role) {
    log.debug("REST request to save Role : {}", role);

    return ApiResponse.success(roleService.save(role));
  }

  @PutMapping("/{id}")
  @Operation(summary = "更新角色")
  public ApiResponse<String> updateRole(
      @PathVariable(value = "id") final String id,
      @RequestBody RoleQueryAndCreateRequest role) {
    log.debug("REST request to update Role : {}, {}", id, role);
    Role entity = roleMapper.toEntity(role);
    entity.setId(id);

    roleService.updateRole(entity);
    return ApiResponse.success();
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "删除角色")
  public ApiResponse<String> deleteRole(@PathVariable String id) {
    log.debug("REST request to delete Role : {}", id);
    roleService.deleteById(id);
    return ApiResponse.success();
  }

  @GetMapping("search")
  @Operation(summary = "条件查询角色")
  public ApiResponse<QueryResponse<Role>> search(
      @RequestParam(defaultValue = "1", required = false) Integer page,
      @RequestParam(defaultValue = "10", required = false) Integer size,
      @RequestParam(required = false) String name,
      @RequestParam(required = false) String title
  ) {
    return ApiResponse.success(roleService.findByKeyword(
        new QueryRequest<>(new RoleQueryAndCreateRequest(name,
            title), page, size, null)));
  }
}

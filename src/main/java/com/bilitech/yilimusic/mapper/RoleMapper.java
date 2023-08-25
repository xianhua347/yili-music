package com.bilitech.yilimusic.mapper;

import com.bilitech.yilimusic.DTO.role.RoleQueryAndCreateRequest;
import com.bilitech.yilimusic.enetity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface RoleMapper {

  RoleQueryAndCreateRequest toDto(Role role);

  @Mapping(target = "updatedTime", ignore = true)
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdTime", ignore = true)
  Role toEntity(RoleQueryAndCreateRequest role);
}

package com.bilitech.yilimusic.service;

import com.bilitech.yilimusic.DTO.role.RoleQueryAndCreateRequest;
import com.bilitech.yilimusic.enetity.QRole;
import com.bilitech.yilimusic.enetity.Role;
import com.bilitech.yilimusic.mapper.RoleMapper;
import com.bilitech.yilimusic.repository.RoleRepository;
import com.bilitech.yilimusic.utils.QueryRequest;
import com.bilitech.yilimusic.utils.QueryResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  private final RoleMapper roleMapper;

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public QueryResponse<Role> findAll(Integer page, Integer size) {
    return QueryResponse.of(roleRepository.findAll(PageRequest.of(page - 1, size)));
  }

  @Override
  public QueryResponse<Role> findByKeyword(QueryRequest<RoleQueryAndCreateRequest> request) {
    BooleanBuilder builder = new BooleanBuilder();
    Option.of(request.getQuery()) // 判断request是否为null
        .peek(query -> Option.of(query.getName()) // 判断name是否为null
            .peek(name -> builder.or(QRole.role.name.eq(name)))) // 执行副作用
        .peek(query -> Option.of(query.getTitle()) //判断title是否为null
            .peek(title -> builder.or(QRole.role.title.eq(title)))); // 执行副作用

    Page<Role> roles = roleRepository
        .findAll(builder, request.toPage());

    return Option.of(roles)
        .map(QueryResponse :: of)
        .getOrElse(QueryResponse :: empty);
  }

  @Override
  public Role save(RoleQueryAndCreateRequest role) {
    return roleRepository.save(roleMapper.toEntity(role));
  }

  @Override
  public void deleteById(String id) {
    roleRepository.deleteById(id);
  }

  @Override
  public void updateRole(Role entity) {
    jpaQueryFactory
        .update(QRole.role)
        .set(QRole.role.name, entity.getName())
        .set(QRole.role.title, entity.getTitle())
        .where(QRole.role.id.eq(entity.getId()))
        .execute();
  }
}


package com.bilitech.yilimusic.repository;

import com.bilitech.yilimusic.model.enetity.Role;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>,
    QuerydslPredicateExecutor<Role> {

  Option<Role> findByName(String name);
}

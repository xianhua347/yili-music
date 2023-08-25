package com.bilitech.yilimusic.repository;

import com.bilitech.yilimusic.enetity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String>, QuerydslPredicateExecutor<Role> {
}

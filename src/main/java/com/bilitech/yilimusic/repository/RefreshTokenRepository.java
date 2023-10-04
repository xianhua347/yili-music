package com.bilitech.yilimusic.repository;

import com.bilitech.yilimusic.model.enetity.RefreshToken;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String>,
    JpaSpecificationExecutor<RefreshToken>, QuerydslPredicateExecutor<RefreshToken> {
  Option<RefreshToken> findByToken(String token);
}

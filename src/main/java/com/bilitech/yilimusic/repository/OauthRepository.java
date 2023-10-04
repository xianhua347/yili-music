package com.bilitech.yilimusic.repository;

import com.bilitech.yilimusic.model.enetity.SocialUser;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface OauthRepository extends JpaRepository<SocialUser, String>,
    JpaSpecificationExecutor<SocialUser>, QuerydslPredicateExecutor<SocialUser> {

  /**
   * @param uuid   第三方平台UUID
   * @param source 第三方平台
   * @return 查询到的用户信息
   */
  Option<SocialUser> findByUuidAndSource(String uuid, String source);
}

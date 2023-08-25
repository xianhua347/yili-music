package com.bilitech.yilimusic.repository;

import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoleRepositoryTest {
  @Resource
  private RoleRepository roleRepository;

  @Test
  void findAllByNameLikeOrTitleLike() {

  }
}

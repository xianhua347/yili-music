package com.bilitech.yilimusic;

import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
@EnableMethodCache(basePackages = "com.bilitech.yilimusic")
public class YiliMusicApplication {

  public static void main(String[] args) {
    SpringApplication.run(YiliMusicApplication.class, args);
  }

  @Bean
  public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
    return new JPAQueryFactory(entityManager);
  }
}

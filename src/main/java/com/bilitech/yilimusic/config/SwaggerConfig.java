package com.bilitech.yilimusic.config;

import cn.hutool.core.util.RandomUtil;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

/**
 * @author 陈现府
 */
@Slf4j
@Configuration
public class SwaggerConfig {

  /**
   * 根据@Tag 上的排序，写入x-order
   *
   * @return the global open api customizer
   */
  @Bean
  public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
    return openApi -> {
      if (openApi.getTags() != null) {
        openApi.getTags().forEach(tag -> {
          Map<String, Object> map = new HashMap<>();
          map.put("x-order", RandomUtil.randomInt(0, 100));
          tag.setExtensions(map);
        });
      }
      if (openApi.getPaths() != null) {
        openApi.addExtension("x-test123", "333");
        openApi.getPaths().addExtension("x-abb", RandomUtil.randomInt(1, 100));
      }
    };
  }

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("音乐系统后台API")
            .version("1.0")
            .license(new License().name("Apache 2.0")
                .url("http://doc.xiaominfo.com"))
        ).addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION))
        .components(
            new Components().addSecuritySchemes(HttpHeaders.AUTHORIZATION, new SecurityScheme()
                .name(HttpHeaders.AUTHORIZATION).type(SecurityScheme.Type.HTTP).scheme("bearer")));
  }
}


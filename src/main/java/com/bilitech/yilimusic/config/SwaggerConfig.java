package com.bilitech.yilimusic.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi userApi(){
        String[] paths = { "/**" };
        String[] packagedToMatch = { "com.bilitech.yilimusic.controller" };
        return GroupedOpenApi.builder().group("")
                .pathsToMatch(paths)
                .packagesToScan(packagedToMatch).build();
    }
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Yilimuisc用户系统API")
                        .version("1.0")
                        .description( "springdoc-openapi")
                        .termsOfService("http://doc.xiaominfo.com")
                        .license(new License().name("MIT")
                                .url("http://doc.xiaominfo.com")));
    }


}

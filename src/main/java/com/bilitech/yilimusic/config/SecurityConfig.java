package com.bilitech.yilimusic.config;

import com.bilitech.yilimusic.Filter.JWTAuthenticationFilter;
import com.bilitech.yilimusic.Filter.JWTAuthorizationFilter;
import com.bilitech.yilimusic.Service.UserService;
import com.bilitech.yilimusic.exception.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    public SecurityConfig(UserService userService, RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.userService = userService;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Override protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, AuthenticationConfigConstants.SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .exceptionHandling() //添加exception处理 如果处理的话 所有请求和错误不会有返回结果
                .authenticationEntryPoint(restAuthenticationEntryPoint)//接管认证的异常处理
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }
}

package com.bilitech.yilimusic.config;


import com.bilitech.yilimusic.Filter.JWTAuthorizationFilter;
import com.bilitech.yilimusic.Service.UserService;
import com.bilitech.yilimusic.exception.RestAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * @author 陈现府
 */
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(
    prePostEnabled = true,
    securedEnabled = true,
    jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


  private final UserService userService;

  private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors(AbstractHttpConfigurer :: disable)
        .csrf(AbstractHttpConfigurer :: disable)
        .authorizeRequests(authorize -> authorize
            .antMatchers("/users/login", "/users/register").permitAll()
            .antMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
            .anyRequest().authenticated()
        )
        .addFilter(new JWTAuthorizationFilter(authenticationManager(), userService))
        .exceptionHandling(exceptionHandling -> exceptionHandling  // 添加exception处理，如果处理的话，所有请求和错误不会有返回结果
            .authenticationEntryPoint(restAuthenticationEntryPoint) // 接管认证的异常处理
        )
        .sessionManagement(sessionManagement -> sessionManagement
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService);
  }
}

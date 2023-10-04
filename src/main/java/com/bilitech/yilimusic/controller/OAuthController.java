package com.bilitech.yilimusic.controller;

import com.bilitech.yilimusic.service.OauthService;
import com.bilitech.yilimusic.utils.ApiResponse;
import com.xkcoding.justauth.AuthRequestFactory;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class OAuthController {

  private final AuthRequestFactory factory;

  private final OauthService oauthService;

  @Operation(summary = "获取系统支持的所有第三方登录类型")
  @GetMapping
  public ApiResponse<List<String>> list() {
    return ApiResponse.success(factory.oauthList());
  }

  @Operation(summary = "获取第三方登录地址")
  @GetMapping("/getRedirectUrl/{type}")
  public ApiResponse<String> getRedirectUrl(@PathVariable String type) {
    AuthRequest authRequest = factory.get(type);
    String authorizeUrl = authRequest.authorize(AuthStateUtils.createState());
    return ApiResponse.success(authorizeUrl);
  }

  @Operation(summary = "第三方登录回调")
  @GetMapping("/login/{type}")
  public ApiResponse<String> login(@PathVariable String type, AuthCallback callback)
      throws ExecutionException, InterruptedException {
    return ApiResponse.success(oauthService.authorize(type, callback).get());
  }
}

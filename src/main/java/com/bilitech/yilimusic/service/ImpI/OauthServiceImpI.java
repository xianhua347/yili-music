package com.bilitech.yilimusic.service.ImpI;

import com.bilitech.yilimusic.enums.ExceptionType;
import com.bilitech.yilimusic.exception.BizException;
import com.bilitech.yilimusic.model.enetity.Role;
import com.bilitech.yilimusic.model.enetity.SocialUser;
import com.bilitech.yilimusic.model.enetity.User;
import com.bilitech.yilimusic.repository.OauthRepository;
import com.bilitech.yilimusic.repository.RoleRepository;
import com.bilitech.yilimusic.repository.UserRepository;
import com.bilitech.yilimusic.service.OauthService;
import com.bilitech.yilimusic.utils.JwtUtils;
import com.xkcoding.justauth.AuthRequestFactory;
import java.util.concurrent.Future;
import lombok.RequiredArgsConstructor;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class OauthServiceImpI implements OauthService {

  private final AuthRequestFactory factory;

  private final OauthRepository oauthRepository;

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final JwtUtils jwtUtils;

  @Async
  @Transactional
  @Override
  // todo
  //  1.需要在每次做完认证后同步更新第三方用户信息到本地DB
  //  2. 在此基础上调用authRequest.refresh() 函数实现access token续签
  //  note: 尽量使用异步操作提高速度 以为这个功能需要调用很多第三方请求和读写sql
  public Future<String> authorize(String type, AuthCallback callback) {
    //1.根据type去第三方平台做认证
    AuthRequest authRequest = factory.get(type);
    AuthResponse<?> response = authRequest.login(callback);
    AuthUser authUser = (AuthUser) response.getData();

    //2. 判断是否可以根据UUID+用户来源查询到用户 如果有就获取系统用户如果没有就初始化一个
    SocialUser socialUser = oauthRepository
        .findByUuidAndSource(authUser.getUuid(),
            authUser.getSource())
        .getOrNull();

    User user = userRepository
        .findByUuid(authUser.getUuid())
        .getOrNull();

    //判断是否完全没有登录过系统
    if (ObjectUtils.isEmpty(socialUser) && ObjectUtils.isEmpty(user)) {
      //新建一个user
      User systeamUser = createNewSysteamUser(authUser);
      //颁发JWT
      return AsyncResult.forValue(jwtUtils.generateJwtToken(systeamUser));
    } else {
      //如果不是新用户而且在系统注册过就直接颁发token
      return AsyncResult.forValue(jwtUtils.generateJwtToken(user));
    }
  }

  private User createNewSysteamUser(AuthUser authUser) {
    Role role = roleRepository.findByName("ROLE_USER")
        .getOrElseThrow(() -> new BizException(ExceptionType.ROLE_NOT_FOUND));
    User user = User.build(authUser, role);
    return userRepository.save(user);
  }
}

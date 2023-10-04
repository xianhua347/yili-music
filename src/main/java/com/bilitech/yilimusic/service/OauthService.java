package com.bilitech.yilimusic.service;

import java.util.concurrent.Future;
import me.zhyd.oauth.model.AuthCallback;

public interface OauthService {

  Future<String> authorize(String type, AuthCallback callback);
}

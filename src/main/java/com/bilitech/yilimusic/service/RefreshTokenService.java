package com.bilitech.yilimusic.service;

import com.bilitech.yilimusic.model.enetity.RefreshToken;

public interface RefreshTokenService {
  RefreshToken createRefreshToken(String userId);

  RefreshToken verifyExpiration(RefreshToken token);
}

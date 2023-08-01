package com.bilitech.yilimusic.config;

/**
 * @author 陈现府
 */
public class AuthenticationConfigConstants {

    public static final String SECRET = "yili_misc";
    public static final long EXPIRATION_TIME = 86400000; // 1days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/users/";
}

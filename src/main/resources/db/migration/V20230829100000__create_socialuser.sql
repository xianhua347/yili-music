CREATE TABLE `social_user`
(

    `id`            VARCHAR(32)  NOT NULL PRIMARY KEY COMMENT '第三方用户ID',

    `user_id`       VARCHAR(32)  NOT NULL COMMENT '用户ID',

    `access_code`   VARCHAR(200) NULL DEFAULT NULL COMMENT '个别平台的授权信息' COLLATE 'utf8mb4_general_ci',

    `access_token`  VARCHAR(200) NULL DEFAULT NULL COMMENT '用户的授权令牌' COLLATE 'utf8mb4_general_ci',

    `code`          VARCHAR(200) NULL DEFAULT NULL COMMENT '用户的授权code' COLLATE 'utf8mb4_general_ci',

    `expire_in`     INT(11)      NULL DEFAULT NULL COMMENT '第三方用户的授权令牌的有效期',

    `id_token`      VARCHAR(200) NULL DEFAULT NULL COMMENT 'id token' COLLATE 'utf8mb4_general_ci',

    `openId`        VARCHAR(200) NULL DEFAULT NULL COMMENT '第三方用户的openId' COLLATE 'utf8mb4_general_ci',

    `refresh_token` VARCHAR(200) NULL DEFAULT NULL COMMENT '刷新令牌' COLLATE 'utf8mb4_general_ci',

    `scope`         VARCHAR(200) NULL DEFAULT NULL COMMENT '第三方用户授予的权限' COLLATE 'utf8mb4_general_ci',

    `source`        VARCHAR(20)  NULL DEFAULT NULL COMMENT '第三方用户来源 GITHUB,QQ,GITEE,具体参考 AuthDefaultSource' COLLATE 'utf8mb4_general_ci',

    `token_type`    VARCHAR(200) NULL DEFAULT NULL COMMENT '个别平台的授权信息' COLLATE 'utf8mb4_general_ci',

    `uId`           VARCHAR(200) NULL DEFAULT NULL COMMENT '第三方用户的ID' COLLATE 'utf8mb4_general_ci',

    `union_id`      VARCHAR(200) NULL DEFAULT NULL COMMENT '第三方用户的unionid' COLLATE 'utf8mb4_general_ci',

    `uuid`          VARCHAR(200) NULL DEFAULT NULL COMMENT '第三方系统的唯一ID' COLLATE 'utf8mb4_general_ci',

    `nickname`      VARCHAR(200) NULL DEFAULT NULL COMMENT '第三方用户的nickname' COLLATE 'utf8mb4_general_ci',

    `username`      VARCHAR(200) NULL DEFAULT NULL COMMENT '第三方用户的username' COLLATE 'utf8mb4_general_ci',

    `avatar`        VARCHAR(200) NULL DEFAULT NULL COMMENT '第三方用户的头像' COLLATE 'utf8mb4_general_ci',

    created_time    datetime(6)  not null comment '创建时间',

    updated_time    datetime(6)  not null comment '更新时间',

    CONSTRAINT s_user_id
        FOREIGN KEY (user_id) REFERENCES user (id),

    UNIQUE INDEX `UK_62b04yxuaqg0qfflxxfgjrd42` (`uuid`) USING BTREE

);

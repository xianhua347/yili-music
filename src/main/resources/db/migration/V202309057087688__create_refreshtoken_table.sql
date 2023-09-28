CREATE TABLE refreshtoken
(
    id           VARCHAR(255) NOT NULL
        PRIMARY KEY COMMENT 'ID',
    user_id      BIGINT       NOT NULL,
    token        VARCHAR(255) NOT NULL,
    expiry_date  TIMESTAMP    NOT NULL,
    created_time datetime(6)  NOT NULL COMMENT '创建时间',
    updated_time datetime(6)  NOT NULL COMMENT '更新时间',
    UNIQUE (token),
    FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT '刷新token表';

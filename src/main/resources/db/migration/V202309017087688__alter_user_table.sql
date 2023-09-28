alter table user
    add uuid varchar(128) null comment '第三方平台用户 UUID';

alter table user
    add constraint user_pk_uuid
        unique (uuid);

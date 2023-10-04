USE yili_music;

INSERT INTO `user` (id, username, nickname, password, created_time, updated_time)
VALUES ('1', 'admin', '程序猿小花', '$2a$10$GA/WGzkZkkZuNHyDaOE27eBpo0iKZ5yqnUavyczQKn108WJkZIOKu',
        NOW(), NOW());

INSERT INTO `role` (id, name, title, created_time, updated_time)
VALUES ('1', 'ROLE_USER', '普通用户', NOW(), NOW());

INSERT INTO `role` (id, name, title, created_time, updated_time)
VALUES ('2', 'ROLE_ADMIN', '超级管理员', NOW(), NOW());

INSERT INTO `user_role` (user_id, role_id)
VALUES ('1', '1');

INSERT INTO `user_role` (user_id, role_id)
VALUES ('1', '2');

# yili-music

![actions-deploy](https://github.com/xianhua347/yili-music/actions/workflows/deploy.yml/badge.svg)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
---

## 简介

yili-music 是一款全栈音乐应用，支持微信小程序和移动应用。目前处于初步开发阶段，更新会较慢，主要在业余时间进行开发。

## 快速开始

1. 使用 Docker 启动 MySQL 容器：
   ```shell
   docker run -d --name mysql-container -e MYSQL_ROOT_PASSWORD=your_password -p 3306:3306 mysql:latest
   ```
2. 使用 Docker 启动 Redis 容器：
   ```shell
   docker run -d --name redis-container -p 6379:6379 redis:latest
   ```
3. 打开您喜欢的 IDE（如 IDEA 或 VS Code），配置数据库连接：
   ```yml
   spring:
   datasource:
   driver-class-name: com.mysql.cj.jdbc.Driver
   url:  # 您的数据库 URL
   username: # 您的数据库用户名
   password: # 您的数据库密码
   ```
4. 编译项目，编译成功后打开 [Swagger 文档](localhost:8084/swagger-ui.html#/)，找到  `auth/login`  接口，填写用户名和密码（默认为 admin 900602）。

### 项目特性

- 使用 JDK11 + Spring Boot 2.6.1 进行开发
- ORM 框架使用 Spring Data JPA + QueryDSL
- 安全框架使用 Spring Security，支持密码登录和第三方授权登录，后续将拓展手机号登录
- 移动应用使用 Flutter 进行开发
- 项目的 CI/CD 使用 GitHub Actions

## 技术栈

|      框架      |    系统版本     |      说明       |
|:------------:|:-----------:|:-------------:|
| Spring Boot  |    2.6.1    |    应用开发框架     |
| Spring Data  |    2.6.1    |    ORM 框架     |
|    Jasypt    |    3.0.5    |   敏感信息加密工具    |
|    MySQL     |     8.0     |    数据库服务器     |
|    Redis     |     6.0     | Key-Value 数据库 |
|  MapStruct   | 1.4.2.Final | Java Bean 转换  |
|    Flyway    |    8.0.4    |   数据库版本管理工具   |
| Querydsl-JPA |     5.0     |  用于构建类型安全的查询  |
|     Vavr     |   0.10.4    |  补充完整的函数式编程库  |

## 前端项目地址

|                                   项目                                   |            简介            |
|:----------------------------------------------------------------------:|:------------------------:|
|   [yili-music-admin](https://github.com/xianhua347/yili-music-admin)   | 基于 Vue3 + Quasar 实现的管理后台 |
| [yili-music-mini-app](https://github.com/xianhua347/yili-music-minapp) |     使用微信原生技术栈开发的小程序      |
|     [yili-music-app](https://github.com/xianhua347/liyi_music_app)     |  使用 Flutter 技术栈开发的移动应用   |

优化了 README 文件的内容。希望这次能够满足您的需求！

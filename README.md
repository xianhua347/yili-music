# yili-music
[![Docs-zh](https://shields.io/badge/README-%E4%B8%AD%E6%96%87-blue)](README-zh.md)
![actions-deploy](https://github.com/xianhua347/yili-music/actions/workflows/deploy.yml/badge.svg)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
---

## Introduction

yili-music is a full-stack music app that supports both WeChat Mini Program and mobile app. Currently, it is in the initial development stage, and updates will be slow as it is developed in spare time.

## Quick Start

1. Start the MySQL container using Docker:
   shell
   docker run -d --name mysql-container -e MYSQL_ROOT_PASSWORD=your_password -p 3306:3306 mysql:latest
2. Start the Redis container using Docker:
   shell
   docker run -d --name redis-container -p 6379:6379 redis:latest
3. Open your favorite IDE (IDEA, VS Code) and configure the database connection:
   yml
   spring:
   datasource:
   driver-class-name: com.mysql.cj.jdbc.Driver
   url:  # Your database URL
   username: # Your database username
   password: # Your database password
4. Compile the project and after successful compilation, open the [Swagger documentation](localhost:8084/swagger-ui.html#/) and find the  auth/login  endpoint. Fill in the username and password (default is admin 900602).

### Project Features

- Developed using JDK 11 and Spring Boot 2.6.1
- ORM framework: Spring Data JPA with QueryDSL
- Security framework: Spring Security with support for password-based login and third-party authorization login. Phone number login will be added in the future.
- Mobile app developed using Flutter
- CI/CD pipeline using GitHub Actions

## Technology Stack

|  Framework   | System Version |              Description              | 
|:------------:|:--------------:|:-------------------------------------:| 
| Spring Boot  |     2.6.1      |   Application development framework   | 
| Spring Data  |     2.6.1      |             ORM framework             | 
|    Jasypt    |     3.0.5      | Sensitive information encryption tool | 
|    MySQL     |      8.0       |            Database server            | 
|    Redis     |      6.0       |          Key-Value database           | 
|  MapStruct   |  1.4.2.Final   |       Java Bean mapping library       | 
|    Flyway    |     8.0.4      |   Database version management tool    | 
| Querydsl-JPA |      5.0       |        Type-safe query builder        | 
|     Vavr     |     0.10.4     |    Functional programming library     | 

## Frontend Project Repositories

|                                Project                                 |                   Description                   | 
|:----------------------------------------------------------------------:|:-----------------------------------------------:| 
|   [yili-music-admin](https://github.com/xianhua347/yili-music-admin)   | Admin dashboard implemented with Vue3 + Quasar  | 
| [yili-music-mini-app](https://github.com/xianhua347/yili-music-minapp) | Mini Program developed with WeChat native stack | 
|     [yili-music-app](https://github.com/xianhua347/liyi_music_app)     |        Mobile app developed with Flutter        | 

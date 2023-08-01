FROM maven:3.6.3-jdk-11-slim as build

# 指定构建过程中的工作目录
WORKDIR /app

# 将src目录下所有文件，拷贝到工作目录中src目录下
COPY src /app/src

# 将pom.xml文件，拷贝到工作目录下
COPY settings.xml pom.xml /app/

# 执行代码编译命令
RUN mvn -s /app/settings.xml -f /app/pom.xml clean package -Dspring.profiles.active=prod -Dmaven.test.skip=true

# 选择运行时基础镜像
FROM adoptopenjdk:11-jdk-hotspot

RUN apt-get update && apt-get install -y --no-install-recommends \
    ca-certificates \
    && rm -rf /var/lib/apt/lists/*

# 指定运行时的工作目录
WORKDIR /app

# 将构建产物jar包拷贝到运行时目录中
COPY --from=build /app/target/yili-music-0.0.1.jar .

# 暴露端口
EXPOSE 80

# 执行启动命令
CMD ["java", "-jar", "/app/yili-music-0.0.1.jar", "--spring.profiles.active=prod"]

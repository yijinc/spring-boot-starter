# spring-boot-starter

使用最新 jdk@21 + spring boot@3.2
## Feature：
- 向前端规范返回结果 ResponseResult、StatusCode、CustomException
- record 定义简单入参
- 使用 validation 注解参数校验
- aop方式防止请求重复提交（请求）支持 param/token 2中方式
- spring security 用户名+密码/手机号+验证码 登录

## TODO


## 常用命令

```bash
# 端口占用清除
lsof -i:8080 # 查询指定端口进程
kill -9 PID # 结束进程

# 连接 redis
redis-cli -h 127.0.0.1 -p 6379
auth password
ping # 连通测试，返回 PONG 表示成功

# Maven 生命周期的每一个阶段的具体实现都是由 Maven 插件实现的，通过下面语法执行
mvn [plugin-name]:[goal-name]

mvn dependency:tree # 打印了项目依赖的树状图 
mvn spring-boot:run # 启动应用程序
mvn clean # rm /target 目录
mvn package # 打包，生成 target/myproject-0.0.1-SNAPSHOT.jar 文件
java -jar target/myproject-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod # 使用 java -jar 命令来运行这个可执行jar程序 --spring.profiles.active 选择 profile

```


## 接口文档

接口文档地址：https://apifox.com/apidoc/shared-4d7d28c2-11ea-4a37-928e-814ab208fd61

idea 安装 [Apifox Helper](https://apifox.com/help/applications-and-plugins/idea/start) 并且设置
[接口注释规范说明](https://apifox.com/help/applications-and-plugins/idea/advanced-use/annotation-rule)

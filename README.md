# spring-boot-starter

hello spring boot


### 常用命令

```bash
# Maven 生命周期的每一个阶段的具体实现都是由 Maven 插件实现的，通过下面语法执行
$ mvn [plugin-name]:[goal-name]

# 打印了项目依赖的树状图
$ mvn dependency:tree 

# 启动应用程序
$ mvn spring-boot:run

# rm /target 目录
$ mvn clean # 生命周期中的clean阶段，由 maven-clean-plugin 来实现

# 打包，生成 target/myproject-0.0.1-SNAPSHOT.jar 文件
$ mvn package

# 使用 java -jar 命令来运行这个可执行jar程序 --spring.profiles.active 选择 profile
$ java -jar target/myproject-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::  (v3.1.0-SNAPSHOT)
....... . . .
....... . . . (log output here)
....... . . .
........ Started MyApplication in 2.536 seconds (process running for 2.864)
```

```bash
# 其他常用命令
lsof -i:8080 # 查询指定端口进程
kill -9 PID # 结束进程
```
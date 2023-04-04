# spring-boot-starter

hello spring boot


### 常用命令

```bash

# 打印了项目依赖的树状图
$ mvn dependency:tree 

# 启动应用程序
$ mvn spring-boot:run

# 打包，生成 target/myproject-0.0.1-SNAPSHOT.jar 文件
$ mvn package

# 使用 java -jar 命令来运行这个可执行jar程序
$ java -jar target/myproject-0.0.1-SNAPSHOT.jar

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
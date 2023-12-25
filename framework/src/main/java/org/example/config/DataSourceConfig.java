package org.example.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 配置自定义数据源
 * Spring Boot 会在任何需要 DataSource 的地方重用它，包括数据库初始化
 * 通过 配置文件 application属性 spring.datasource 获取配置
 * **/
@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        return new DruidDataSource();
    }
}

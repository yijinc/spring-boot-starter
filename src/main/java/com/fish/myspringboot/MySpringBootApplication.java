package com.fish.myspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController 和 @RequestMapping 注解是Spring MVC注解（它们不是Spring Boot特有的）
@RestController // @RestController 注解告诉Spring将返回的结果字符串直接响应给客户端。
@SpringBootApplication // 这个注解被称为元注解，它结合了 @SpringBootConfiguration、@EnableAutoConfiguration 和 @ComponentScan
public class MySpringBootApplication {

	@Value("${myApplicationName}")
	private String myApplicationName;

	@RequestMapping("/demo") // @RequestMapping 注解提供了 “routing” （路由）信息。 它告诉Spring，任何带有 / 路径的HTTP请求都应该被映射到 home 方法
	String home() {
		return "demo";
	}

	@RequestMapping("/hello")
	String hello() {
		return "hello, " + myApplicationName;
	}

	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class, args); // args 数组也被传入，这是命令行参数
	}

}

package com.fish.myspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 这个注解被称为元注解，它结合了 @SpringBootConfiguration、@EnableAutoConfiguration 和 @ComponentScan
public class MySpringBootApplication {
	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class, args); // args 数组也被传入，这是命令行参数
	}
}

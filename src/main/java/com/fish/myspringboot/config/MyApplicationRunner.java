package com.fish.myspringboot.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 如果你需要在 SpringApplication 启动后运行一些特定的代码，
 * 你可以实现 ApplicationRunner 或 CommandLineRunner 接口
 * 这两个接口以相同的方式工作，并提供一个单一的 run 方法，该方法在 SpringApplication.run(…) 执行完毕之前被调用。
 * 这很适合用于执行那些需要在处理HTTP请求之前执行的任务。
 *
 * 如果定义了多个 CommandLineRunner 或 ApplicationRunner Bean，并且需要它们按照特定的顺序先后执行。
 * 那么可以实现 org.springframework.core.Ordered 接口或使用 org.springframework.core.annotation.Order 注解来指定顺序。
 * */
@Component
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("after ApplicationRunner");
    }
}

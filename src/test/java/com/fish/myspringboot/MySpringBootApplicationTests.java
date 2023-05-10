package com.fish.myspringboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class MySpringBootApplicationTests {

	@Autowired
	DataSource dataSource;

	@Test
	void contextLoads() {
	}

	@Test
	void testDataSource() throws SQLException {
		System.out.println("测试数据库连接");
		// 查看数据源
		System.out.println(dataSource.getClass());
		// 获取数据库连接
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
		// 关闭连接
		connection.close();
	}

}

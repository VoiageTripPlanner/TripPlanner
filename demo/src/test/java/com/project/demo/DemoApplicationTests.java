package com.project.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;

@SpringBootTest
@ContextConfiguration(classes = {DemoApplicationTests.TestConfig.class})
@TestPropertySource(locations = "classpath:application-test.properties")
class DemoApplicationTests {

	@Configuration
	static class TestConfig {

		@Bean
		public DataSource dataSource() {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("org.h2.Driver");
			dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
			dataSource.setUsername("sa");
			dataSource.setPassword("");
			return dataSource;
		}
	}

	@Test
	void contextLoads() {
    }

}

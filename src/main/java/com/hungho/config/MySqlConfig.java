package com.hungho.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.hungho.persistence")
@PropertySource("classpath:jdbc.properties")
public class MySqlConfig implements EnvironmentAware {

	private Environment env;

	@Bean
	public DataSource dataSource() {
		String jdbcUrl = env.getProperty("jdbc.url");
		String username = env.getProperty("jdbc.username");
		String password = env.getProperty("jdbc.password");

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setValidationQuery("select 1");

		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() throws Exception {
		return new DataSourceTransactionManager(dataSource());
	}

	@Bean()
	public SqlSessionFactoryBean scheduleSqlSessionFactory() {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource());
		Resource resource = new ClassPathResource("mybatis-config.xml");
		sqlSessionFactory.setConfigLocation(resource);
		return sqlSessionFactory;
	}

	@Override
	public void setEnvironment(Environment eve) {
		this.env = eve;

	}
}
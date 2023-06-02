package com.orderManagement.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "orderManagementEntityManagerFactory", transactionManagerRef = "orderManagementTransactionManager", basePackages = {
		"com.orderManagement.repository" })
public class OrderManagementDataSourceConfig {

	@Primary
	@Bean(name = "orderManagementDataSource")
	public DataSource customerDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.postgresql.Driver");
		dataSourceBuilder.url(" jdbc:postgresql://localhost:5432/order_management");
		dataSourceBuilder.username("postgres");
		dataSourceBuilder.password("admin");
		return dataSourceBuilder.build();
	}

	@Primary
	@Bean(name = "orderManagementEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("orderManagementDataSource") DataSource dataSource) {
		return builder.dataSource(dataSource).packages("com.orderManagement.entity").persistenceUnit("order_management")
				.build();
	}

	@Primary
	@Bean(name = "orderManagementTransactionManager")
	public PlatformTransactionManager customerTransactionManager(
			@Qualifier("orderManagementEntityManagerFactory") EntityManagerFactory orderManagementEntityManagerFactory) {
		return new JpaTransactionManager(orderManagementEntityManagerFactory);
	}
}

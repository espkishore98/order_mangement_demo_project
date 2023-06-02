package com.orderManagement.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement

@EnableJpaRepositories(entityManagerFactoryRef = "demoEntityManagerFactory", transactionManagerRef = "demoTransactionManager", repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class, basePackages = {
		"com.orderManagement.demo.repository" })
public class DemoDataSourceConfig {

	@Bean(name = "demoDataSource")
	public DataSource demoDataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.postgresql.Driver");
		dataSourceBuilder.url(" jdbc:postgresql://localhost:5432/demo");
		dataSourceBuilder.username("postgres");
		dataSourceBuilder.password("admin");
		return dataSourceBuilder.build();
	}

	@Bean(name = "demoEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("demoDataSource") DataSource dataSource) {
		final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(demoDataSource());
		em.setPackagesToScan(new String[] { "com.orderManagement.demo.entity" });
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		em.setJpaProperties(additionalProperties());
//		return builder.dataSource(dataSource).packages("com.orderManagement.demo.entity").persistenceUnit("demo")
//				.build();
		return em;
	}

	@Primary
	@Bean(name = "demoTransactionManager")
	public PlatformTransactionManager customerTransactionManager(
			@Qualifier("demoEntityManagerFactory") EntityManagerFactory demoEntityManagerFactory) {
		return new JpaTransactionManager(demoEntityManagerFactory);
	}

	final Properties additionalProperties() {
		final Properties hibernateProperties = new Properties();

		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		hibernateProperties.setProperty("hibernate.show_sql", "true");

		return hibernateProperties;
	}
}

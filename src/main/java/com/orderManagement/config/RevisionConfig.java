//package com.orderManagement.config;
//
//import javax.persistence.EntityManagerFactory;
//
//import org.hibernate.envers.AuditReader;
//import org.hibernate.envers.AuditReaderFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class RevisionConfig {
//
//	@Qualifier("demoEntityManagerFactory")
//	EntityManagerFactory entityManagerFactory;
//
//	RevisionConfig(EntityManagerFactory entityManagerFactory) {
//		this.entityManagerFactory = entityManagerFactory;
//	}
//
//	@Bean
//	AuditReader auditReader() {
//		return AuditReaderFactory.get(entityManagerFactory.createEntityManager());
//	}
//}

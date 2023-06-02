//package com.orderManagement.config;
//
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.ehcache.EhCacheCacheManager;
//import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//
//@Configuration
//@EnableCaching
//public class EhCacheManager {
//
//	@Bean
//	public CacheManager cacheManager() {
//		return new EhCacheCacheManager(cacheMangerFactory().getObject());
//
//	}
//
//	@Bean
//	public EhCacheManagerFactoryBean cacheMangerFactory() {
//		// TODO Auto-generated method stub
//		EhCacheManagerFactoryBean ehCacheBean = new EhCacheManagerFactoryBean();
//		ehCacheBean.setConfigLocation(new ClassPathResource("ehCache.xml"));
//		ehCacheBean.setShared(true);
//		return ehCacheBean;
//	}
//
//}

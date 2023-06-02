package com.orderManagement.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MyBatchConfigurer extends DefaultBatchConfigurer {
	@Autowired
	public MyBatchConfigurer(@Qualifier("orderManagementDataSource") DataSource firstDataSource) {
		super(firstDataSource);
	}
}

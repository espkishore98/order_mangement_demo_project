package com.orderManagement.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.orderManagement.service.ItemService;

public class Task implements Tasklet {
	Logger logger = LoggerFactory.getLogger("Task");
	@Autowired
	ItemService itemService;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		logger.info("MyTask:execute: executing tasklet - ");
		return RepeatStatus.FINISHED;
	}
}
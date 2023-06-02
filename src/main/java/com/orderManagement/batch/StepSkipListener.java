package com.orderManagement.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.SkipListener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orderManagement.model.ItemPrice;

public class StepSkipListener implements SkipListener<ItemPrice, Number> {

	Logger logger = LoggerFactory.getLogger(StepSkipListener.class);

	@Override // item reader
	public void onSkipInRead(Throwable throwable) {
		logger.info("A failure on read {} ", throwable.getMessage());
	}

	@Override // item writter
	public void onSkipInWrite(Number item, Throwable throwable) {
		logger.info("A failure on write {} , {}", throwable.getMessage(), item);
	}

	@Override // item processor
	public void onSkipInProcess(ItemPrice item, Throwable throwable) {
		try {
			logger.info("Item {}  was skipped due to the exception  {}", new ObjectMapper().writeValueAsString(item),
					throwable.getMessage());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
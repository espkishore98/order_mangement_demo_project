package com.orderManagement.batch;

import java.util.UUID;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.orderManagement.model.ItemPrice;

@Component
public class Processor implements ItemProcessor<ItemPrice, ItemPrice> {

	@Override
	public ItemPrice process(ItemPrice item) throws Exception {
		// TODO Auto-generated method stub
		String uuid = UUID.randomUUID().toString();
		item.setUuid(uuid);

		return item;
	}

}

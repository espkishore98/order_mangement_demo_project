package com.orderManagement.batch;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orderManagement.entity.Items;
import com.orderManagement.entity.Price;
import com.orderManagement.model.ItemPrice;
import com.orderManagement.repository.PriceRepository;

@Component
public class DbWriter implements ItemWriter<ItemPrice> {

	@Autowired
	PriceRepository priceRepository;

	@Override
	public void write(List<? extends ItemPrice> items) throws Exception {
		System.out.println("Data Saved for Users: " + items);
		List<Price> prices = new ArrayList<Price>();
		items.forEach(line -> {
			Items itemObj = new Items(line.getItemName(), UUID.randomUUID().toString(), line.getItemDescription(),
					line.getCategory(), line.getStatus(),
					line.getQuantityAvailable() != null ? Long.valueOf(line.getQuantityAvailable()) : null);
			Price price = new Price();
			price.setAmount(line.getAmount() != null ? Long.valueOf(line.getAmount()) : null);
			price.setItem(itemObj);
			prices.add(price);
		});
		priceRepository.saveAll(prices);
	}
}

package com.orderManagement.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orderManagement.entity.Items;
import com.orderManagement.entity.Price;
import com.orderManagement.model.ItemsResponseDTO;
import com.orderManagement.repository.PriceRepository;

@Component
public class Mapper {
	@Autowired
	PriceRepository priceRepository;
	Logger logger = LoggerFactory.getLogger("Mapper");

	public List<ItemsResponseDTO> mapToItemsResponse(List<Items> items) {

		return items.stream()
				.map(item -> new ItemsResponseDTO(item.getItemName(), item.getUuid(), item.getDescription(),
						item.getCategory(), item.getStatus(), getItemPrice(item), getDiscountedPrice(item),
						item.getCreatedDate(), item.getUpdatedDate()))
				.collect(Collectors.toList());

	}

	private Long getItemPrice(Items item) {
		logger.info("item =====>" + item);
		Long itemPrice = priceRepository.findByItem(item).getAmount();
		return itemPrice;
	}

	public Long getDiscountedPrice(Items item) {
		Price price = priceRepository.findByItem(item);
		Long itemDiscountPrice = (price.getAmount()
				* (100 - (price.getDiscount() != null ? price.getDiscount().getPercentageOfDiscount() : 0))) / 100;
		return itemDiscountPrice;
	}
}

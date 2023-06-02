package com.orderManagement.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.orderManagement.entity.Discounts;
import com.orderManagement.entity.Items;
import com.orderManagement.entity.Price;
import com.orderManagement.model.PriceDto;
import com.orderManagement.repository.DiscountsRepository;
import com.orderManagement.repository.ItemsRepository;
import com.orderManagement.repository.PriceRepository;

@Service
public class PriceService implements IPriceService {
	Logger logger = LoggerFactory.getLogger("Price service");
	@Autowired
	ItemsRepository itemsRepo;
	@Autowired
	DiscountsRepository discountsRepository;
	@Autowired
	PriceRepository priceRepository;

	@Override
	public ResponseEntity<?> addPrice(PriceDto priceDto) {
		try {
			Price itemPrice = new Price();

			if (priceDto.getItemId() == null) {
				return new ResponseEntity<String>("Item is mandatory", HttpStatus.BAD_REQUEST);
			}
			if (priceDto.getPriceId() != null) {
				Optional<Price> price = priceRepository.findById(priceDto.getPriceId());
				if (price.isPresent()) {
					BeanUtils.copyProperties(price.get(), itemPrice);
				}
			}
			Optional<Items> item = itemsRepo.findById(priceDto.getItemId());
			if (item.isEmpty()) {
				return new ResponseEntity<String>("Item details not found", HttpStatus.BAD_REQUEST);
			}
			Items itemDtls = item.get();
			itemPrice.setItem(itemDtls);
			itemPrice.setAmount(priceDto.getAmount());
			if (priceDto.getDiscountId() != null) {
				Optional<Discounts> discount = discountsRepository.findById(priceDto.getDiscountId());
				if (!discount.isEmpty()) {
					itemPrice.setDiscount(discount.get());
				}
			}

			priceRepository.saveAndFlush(itemPrice);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage());
			return new ResponseEntity<String>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("data saved successfully", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getPriceByItem(String itemId) {
		// TODO Auto-generated method stub
		try {
			Items item = itemsRepo.findByUuid(itemId);
			if (ObjectUtils.isEmpty(item)) {
				return new ResponseEntity<String>("item not found", HttpStatus.BAD_REQUEST);
			}
			logger.info("object id od priceRepo" + priceRepository);
			Price itemPrice = priceRepository.findByItem(item);
			if (itemPrice != null) {
				return new ResponseEntity<Object>(itemPrice, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("Error while fetching items price", HttpStatus.OK);

		}
		return new ResponseEntity<String>("Error while fetching items price", HttpStatus.OK);

	}

}

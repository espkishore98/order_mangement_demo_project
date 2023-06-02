package com.orderManagement.service;

import org.springframework.http.ResponseEntity;

import com.orderManagement.model.PriceDto;

public interface IPriceService {

	ResponseEntity<?> addPrice(PriceDto price);

	ResponseEntity<?> getPriceByItem(String itemId);

//	ResponseEntity<?> getPriceByItem(Long itemId);

}

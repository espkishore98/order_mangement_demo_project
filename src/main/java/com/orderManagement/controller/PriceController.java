package com.orderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderManagement.model.PriceDto;
import com.orderManagement.service.IPriceService;

@RequestMapping("/price")
@RestController
//@A(value = "price demo", description = "demo for price")
public class PriceController {

	@Autowired
	IPriceService priceService;

	@PostMapping("/price")
	public ResponseEntity<?> addPriceDetails(@RequestBody PriceDto priceDto) {
		return priceService.addPrice(priceDto);
	}

	@GetMapping("/priceByItem")
	public ResponseEntity<?> getPriceByItem(@RequestParam("itemId") String itemId) {
		return priceService.getPriceByItem(itemId);
	}

}

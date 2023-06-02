package com.orderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderManagement.model.DiscountRequest;
import com.orderManagement.service.IDiscountService;

@RestController
public class DiscountsController {

	@Autowired
	IDiscountService discountService;

	@PostMapping("/discount")
	public ResponseEntity<?> addDiscount(@RequestBody DiscountRequest discountRequest) {
		return discountService.addDiscount(discountRequest);
	}

	@DeleteMapping("/discount")
	public ResponseEntity<?> removeDiscount(@RequestParam(name = "discountId") Long id) {
		return discountService.removeDiscount(id);
	}

}

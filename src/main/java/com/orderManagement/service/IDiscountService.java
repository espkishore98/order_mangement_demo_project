package com.orderManagement.service;

import org.springframework.http.ResponseEntity;

import com.orderManagement.model.DiscountRequest;

public interface IDiscountService {

	ResponseEntity<?> addDiscount(DiscountRequest request);

	ResponseEntity<?> removeDiscount(Long id);

}

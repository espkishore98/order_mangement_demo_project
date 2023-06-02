package com.orderManagement.service;

import org.springframework.http.ResponseEntity;

import com.orderManagement.model.OrderRequest;

public interface IOrderService {

//	ResponseEntity<?> placeOrder();

	ResponseEntity<?> placeOrder(OrderRequest request, String bearerToken);

	ResponseEntity<?> getAllOrders(String bearerToken);

	ResponseEntity<?> cancelOrder(String bearerToken);

}

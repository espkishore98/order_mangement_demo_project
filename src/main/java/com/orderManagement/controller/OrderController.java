package com.orderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.orderManagement.model.OrderRequest;
import com.orderManagement.service.EmailService;
import com.orderManagement.service.IOrderService;

@RestController
//@RequestMapping("/order")
public class OrderController {
	@Autowired
	IOrderService orderService;
	@Autowired
	EmailService emailService;

	@PostMapping("/order")
	public ResponseEntity<?> placeOrder(@RequestBody OrderRequest request,
			@RequestHeader("Authorization") String bearerToken) {
		return orderService.placeOrder(request, bearerToken);
	}

	@GetMapping("/order")
	public ResponseEntity<?> getAllOrders(@RequestHeader("Authorization") String bearerToken) {
		return orderService.getAllOrders(bearerToken);
	}

	@DeleteMapping("/order")
	public ResponseEntity<?> cancelOrder(@RequestHeader("Authorization") String bearerToken) {
		return orderService.cancelOrder(bearerToken);
	}

	@PostMapping("/emailTest")
	public String emailTest(@RequestHeader("Authorization") String bearerToken) {
		String resp = emailService.testMail("ORDER_CREATION", bearerToken);
		return resp;

	}
}

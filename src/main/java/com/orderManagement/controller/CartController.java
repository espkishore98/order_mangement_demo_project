package com.orderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderManagement.model.CartRequest;
import com.orderManagement.service.ICartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	ICartService cartService;

	@PostMapping("/save")
	public ResponseEntity<?> addItemToCart(@RequestBody CartRequest cartRequest,
			@RequestHeader("Authorization") String bearerToken) {
		return cartService.addItemsToCart(cartRequest, bearerToken);
	}

	@PostMapping("/delete")
	public ResponseEntity<?> removeItemFromCart(@RequestBody CartRequest cartRequest,
			@RequestHeader("Authorization") String bearerToken) {
		return cartService.removeFromCart(cartRequest, bearerToken);
	}

	@GetMapping("/findAll")
	public ResponseEntity<?> findAllCartItems(@RequestHeader("Authorization") String bearerToken) {
		return cartService.getItemsInCart(bearerToken);
	}
}

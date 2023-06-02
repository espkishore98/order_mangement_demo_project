package com.orderManagement.service;

import org.springframework.http.ResponseEntity;

import com.orderManagement.model.CartRequest;

public interface ICartService {

	ResponseEntity<?> addItemsToCart(CartRequest cartRequest, String bearerToken);

	ResponseEntity<?> removeFromCart(CartRequest cartRequest, String bearerToken);

	ResponseEntity<?> getItemsInCart(String bearerToken);

}

package com.orderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orderManagement.entity.Items;
import com.orderManagement.service.ItemService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	ItemService itemService;

	@PostMapping("/item")
	public ResponseEntity<?> addItem(@RequestBody Items item, @RequestHeader String bearerToken) {

		return itemService.addItem(item, bearerToken);
	}
}

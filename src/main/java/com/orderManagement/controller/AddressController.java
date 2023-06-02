package com.orderManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderManagement.model.AddressRequest;
import com.orderManagement.service.IAddressService;

@RestController
public class AddressController {
	@Autowired
	IAddressService addressService;

	@PostMapping("/address")
	public ResponseEntity<?> addAddress(@RequestBody AddressRequest addressRequest) {
		return addressService.addAddress(addressRequest);
	}

	@DeleteMapping("/address")
	public ResponseEntity<?> deleteAddress(@RequestParam("addressId") Long addressId) {
		return addressService.removeAddress(addressId);
	}
}

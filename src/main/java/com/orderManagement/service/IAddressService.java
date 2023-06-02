package com.orderManagement.service;

import org.springframework.http.ResponseEntity;

import com.orderManagement.model.AddressRequest;

public interface IAddressService {

	ResponseEntity<?> addAddress(AddressRequest addressRequest);

	ResponseEntity<?> removeAddress(Long addressId);

}

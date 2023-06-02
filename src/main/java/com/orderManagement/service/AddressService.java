package com.orderManagement.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.orderManagement.entity.Address;
import com.orderManagement.entity.Users;
import com.orderManagement.model.AddressRequest;
import com.orderManagement.repository.AddressRepository;
import com.orderManagement.repository.UserRepository;

import constants.Constants;

@Service
public class AddressService implements IAddressService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

	@Autowired
	UserRepository userRepository;
	@Autowired
	AddressRepository addressRepository;

	@Override
	public ResponseEntity<?> addAddress(AddressRequest addressRequest) {
		LOGGER.info("Add new address started {}", addressRequest.getUserId());
		try {
			List<Address> addresses = addressRequest.getAddresses();
			Users user = userRepository.findByExternalId(addressRequest.getUserId());
			if (user == null) {
				LOGGER.error("User not found========>", addressRequest.getUserId());
				return new ResponseEntity<String>("user not found", HttpStatus.BAD_REQUEST);
			}
			addresses.stream().forEach(address -> {
				address.setUser(user);
				addressRepository.save(address);
			});

			return new ResponseEntity<String>("Address saved successfully", HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Something went wrong========>", e.getMessage());
			return new ResponseEntity<String>(Constants.ExceptionConstants.SOMETHING_WENT_WRONG,
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@Override
	public ResponseEntity<?> removeAddress(Long addressRemoveRequest) {

		try {
			LOGGER.info("remove address started");
			if (addressRemoveRequest != null) {
				Optional<Address> address = addressRepository.findById(addressRemoveRequest);
				if (address.isPresent() && ObjectUtils.isEmpty(address)) {
					addressRepository.deleteById(addressRemoveRequest);
					return new ResponseEntity<String>("address deleted Successfully", HttpStatus.OK);
				}
			}

		} catch (Exception e) {
			LOGGER.error("Error while deleting address", e.getMessage());
			return new ResponseEntity<String>(Constants.ExceptionConstants.SOMETHING_WENT_WRONG,
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<String>(Constants.ExceptionConstants.SOMETHING_WENT_WRONG,
				HttpStatus.INTERNAL_SERVER_ERROR);

	}
}

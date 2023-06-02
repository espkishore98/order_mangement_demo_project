package com.orderManagement.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.orderManagement.entity.Discounts;
import com.orderManagement.model.DiscountRequest;
import com.orderManagement.repository.DiscountsRepository;

import constants.Constants;

@Service
public class DiscountService implements IDiscountService {
	private static final Logger LOGGER = LoggerFactory.getLogger(DiscountService.class);
	@Autowired
	DiscountsRepository discountRepository;

	@Override
	public ResponseEntity<?> addDiscount(DiscountRequest request) {
		try {
			if (request.getDiscountType() != null && request.getPercentageOfDiscount() != null) {
				LOGGER.info("add new discount request added");
				Discounts discount = new Discounts();
				discount.setDiscountType(request.getDiscountType());
				discount.setPercentageOfDiscount(request.getPercentageOfDiscount());
				discountRepository.save(discount);
			}
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("error while adding discount=============>", e.getMessage());
			return new ResponseEntity<String>(Constants.ExceptionConstants.SOMETHING_WENT_WRONG, HttpStatus.OK);

		}

		return new ResponseEntity<String>(Constants.SuccessConstants.DATA_SAVED_SUCCESSFULLY, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> removeDiscount(Long id) {
		LOGGER.info("remove discount started");
		try {
			Optional<Discounts> discount = discountRepository.findById(id);
			if (discount.isPresent()) {
				discountRepository.deleteById(id);
				return new ResponseEntity<String>("deleted successfully", HttpStatus.OK);
			}

		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error("Error while removing discount===========>", e.getMessage());
			return new ResponseEntity<String>(Constants.ExceptionConstants.SOMETHING_WENT_WRONG, HttpStatus.OK);

		}
		return new ResponseEntity<String>(Constants.ExceptionConstants.SOMETHING_WENT_WRONG, HttpStatus.OK);
	}
}

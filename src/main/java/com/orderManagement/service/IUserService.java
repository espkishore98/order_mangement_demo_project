package com.orderManagement.service;

import org.springframework.http.ResponseEntity;

import com.orderManagement.model.LoginRequest;
import com.orderManagement.model.UserRequestDTO;

public interface IUserService {

	ResponseEntity<String> register(UserRequestDTO user);

	ResponseEntity<Object> loginUser(LoginRequest request);

}

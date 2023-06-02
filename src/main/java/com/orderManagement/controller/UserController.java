package com.orderManagement.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.orderManagement.entity.UserRoles;
import com.orderManagement.model.LoginRequest;
import com.orderManagement.model.UserRequestDTO;
import com.orderManagement.model.UserResponseMap;
import com.orderManagement.repository.UserRepository;
import com.orderManagement.service.UserService;

//@Api(value = "userdemo", description = "demo for user creation")
@RestController
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	UserRepository userRepository;

	@PostMapping("/register")
//	@ApiOperation("register user")
//    @ApiImplicitParams({
//    })
	public ResponseEntity<String> registerUser(@RequestBody UserRequestDTO user) {
		return userService.register(user);

	}

	@PostMapping("/login")
//	@ApiOperation("login user")
	public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
		return userService.loginUser(loginRequest);
	}

	@GetMapping("/getUserByRole")
	public ResponseEntity<?> fetchUser(@RequestParam("userId") Long userId, @RequestParam("roleId") Long roleId) {
		UserRoles userRole = userRepository.findByUserAndRole(userId, roleId);
		UserResponseMap userResponseMap = new UserResponseMap();
		BeanUtils.copyProperties(userRole.getUserId(), userResponseMap);
		BeanUtils.copyProperties(userRole.getRoleId(), userResponseMap);
		return new ResponseEntity<Object>(userResponseMap, HttpStatus.OK);

	}
}

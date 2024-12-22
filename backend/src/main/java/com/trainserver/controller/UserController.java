package com.trainserver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trainserver.auth.TokenValidator;
import com.trainserver.helper.UserExistsException;
import com.trainserver.model.Train;
import com.trainserver.model.User;
import com.trainserver.service.TrainService;
import com.trainserver.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private TrainService trainService;

	@Autowired
	private TokenValidator tokenValidator;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		try {
			User registeredUser = userService.registerUser(user);
			return ResponseEntity.ok("Hey " + registeredUser.getUserName() + ", you are registered successfully 😎");
		} catch (UserExistsException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// View Profile
	@GetMapping("/profile/{userId}")
	public ResponseEntity<?> viewProfile(@PathVariable String userId, HttpServletRequest request) {
		try {
			checkUserAuthentication(request);

			User user = userService.getUserProfile(userId);

			// Create a success response
			Map<String, Object> response = new HashMap<>();
			response.put("userData", user);

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// Update Profile
	@PutMapping("/profile/{userId}")
	public ResponseEntity<?> updateProfile(@PathVariable String userId, @RequestBody User updatedUser,
			HttpServletRequest request) {
		try {
			checkUserAuthentication(request);

			User user = userService.updateUserProfile(userId, updatedUser);

			// Create a success response
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Profile updated successfully");
			response.put("userData", user);

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/trains/search")
	public ResponseEntity<?> searchTrains(@RequestParam String originStation, @RequestParam String destinationStation,
			@RequestParam String date, @RequestParam String tierClass, @RequestParam Double price,
			HttpServletRequest request) {
		try {
			List<Train> trainsData = trainService.searchTrains(originStation, destinationStation, date, tierClass,
					price);

			// Create a success response
			Map<String, Object> response = new HashMap<>();
			response.put("train", trainsData); // Include the added train data

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// Method to check user authentication
	private void checkUserAuthentication(HttpServletRequest request) throws Exception {
		String token = request.getHeader("Authorization");
		if (token == null || !tokenValidator.validateToken(token, "customer")) {
			throw new RuntimeException("Unauthorized");
		}
	}
}
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainserver.auth.TokenValidator;
import com.trainserver.dao.TrainBookingDetailsDAO;
import com.trainserver.helper.AdminExistsException;
import com.trainserver.model.Admin;
import com.trainserver.service.AdminService;
import com.trainserver.service.BookTrainService;
import com.trainserver.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/admins")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private UserService userService;

	@Autowired
	private BookTrainService bookTrainService;

	@Autowired
	private TokenValidator tokenValidator;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody Admin admin) {
		try {
			Admin registeredAdmin = adminService.registerAdmin(admin);
			return ResponseEntity.ok("Hey " + registeredAdmin.getUserName() + ", you are registered successfully 😎");
		} catch (AdminExistsException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/users/active/count")
	public ResponseEntity<?> getNumberOfActiveUsers(HttpServletRequest request) {
		try {
			checkAdminAuthentication(request);
			long activeUsersCount = userService.getActiveUsersCount();

			// Create a success response
			Map<String, Object> response = new HashMap<>();
			response.put("userCount", activeUsersCount); // Include the added train data

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/trains/users/booked")
	public ResponseEntity<?> getUsersCountForTrain(HttpServletRequest request) {
		try {
			checkAdminAuthentication(request);
			List<TrainBookingDetailsDAO> trainBookingDetails = bookTrainService.getTrainBookingDetails();

			// Create a success response
			Map<String, Object> response = new HashMap<>();
			response.put("trainDetails", trainBookingDetails); // Include the added train data

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// Method to check admin authentication
	private void checkAdminAuthentication(HttpServletRequest request) throws Exception {
		String token = request.getHeader("Authorization");
		if (token == null || !tokenValidator.validateToken(token, "admin")) {
			throw new RuntimeException("Unauthorized");
		}
	}
}
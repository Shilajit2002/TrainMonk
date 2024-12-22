package com.trainserver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.trainserver.model.BookTrain;
import com.trainserver.service.BookTrainService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/users/trains")
public class BookTrainController {
	@Autowired
	private BookTrainService bookTrainService;

	@Autowired
	private TokenValidator tokenValidator;

	@PostMapping("/book-train")
	public ResponseEntity<?> bookTrain(@RequestBody BookTrain bookTrain, HttpServletRequest request) {
		try {
			checkUserAuthentication(request);
			BookTrain bookTrainData = bookTrainService.bookNewTrain(bookTrain);

			// Create a success response
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Ticket booked successfully");
			response.put("bookTrain", bookTrainData); // Include the added train data

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping("/cancel-train")
	public ResponseEntity<?> cancelTrain(@RequestBody BookTrain bookTrain, HttpServletRequest request) {
		try {
			checkUserAuthentication(request);
			BookTrain bookTrainData = bookTrainService.cancelBookedTrain(bookTrain);

			// Create a success response
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Ticket cancelled successfully");
			response.put("bookTrain", bookTrainData); // Include the added train data

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/view-booked-train/{uId}")
	public ResponseEntity<?> fetchBookedTrainByUserId(@PathVariable String uId, HttpServletRequest request) {
		try {
			checkUserAuthentication(request);
			Optional<List<BookTrain>> bookTrainData = bookTrainService.fetchAllBookedTrainBuUserId(uId);

			// Create a success response
			Map<String, Object> response = new HashMap<>();
			response.put("bookTrain", bookTrainData); // Include the added train data

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

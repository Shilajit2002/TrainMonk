package com.trainserver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainserver.auth.TokenValidator;
import com.trainserver.model.Train;
import com.trainserver.service.TrainService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1/admins/trains")
public class TrainController {

	@Autowired
	private TrainService trainService;

	@Autowired
	private TokenValidator tokenValidator;

	@PostMapping("/add")
	public ResponseEntity<?> addTrain(@RequestBody Train train, HttpServletRequest request) {
		try {
			checkAdminAuthentication(request);
			Train trainData = trainService.addTrain(train);

			// Create a success response
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Train added successfully");
			response.put("train", trainData); // Include the added train data

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateTrain(@PathVariable Long id, @RequestBody Train trainDetails,
			HttpServletRequest request) {
		try {
			checkAdminAuthentication(request);
			Train trainData = trainService.updateTrain(id, trainDetails);

			// Create a success response
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Train updated successfully");
			response.put("train", trainData); // Include the updated train data

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteTrain(@PathVariable Long id, HttpServletRequest request) {
		try {
			checkAdminAuthentication(request);
			trainService.deleteTrain(id);

			// Create a success response
			Map<String, Object> response = new HashMap<>();
			response.put("message", "Train deleted successfully");

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> fetchTrainById(@PathVariable Long id, HttpServletRequest request) {
		try {
			checkAdminAuthentication(request);
			Train trainData = trainService.fetchTrainById(id);

			// Create a success response
			Map<String, Object> response = new HashMap<>();
			response.put("train", trainData); // Include the added train data

			return ResponseEntity.ok(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/all-train")
	public ResponseEntity<?> fetchAllTrain(HttpServletRequest request) {
		try {
			checkAdminAuthentication(request);
			List<Train> trainsData = trainService.fetchAllTrain();

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
	
	@GetMapping("/count")
	public ResponseEntity<?> getNumberOfRegisteredTrains(HttpServletRequest request) {
		try {
			checkAdminAuthentication(request);
			long trainCount = trainService.getTrainCount();

			// Create a success response
			Map<String, Object> response = new HashMap<>();
			response.put("trainCount", trainCount); // Include the added train data

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

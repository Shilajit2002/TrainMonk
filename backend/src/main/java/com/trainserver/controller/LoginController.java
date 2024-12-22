package com.trainserver.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trainserver.auth.AuthService;
import com.trainserver.helper.BadCredentialsException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
public class LoginController {
	@Autowired
	private AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> request) throws Exception {
		String email = request.get("email");
		String password = request.get("password");
		String role = request.get("role");

		try {
			Map<String, Object> response = authService.login(email, password, role);
			return ResponseEntity.ok(response);
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}
package com.trainserver.auth;

import org.springframework.stereotype.Service;

import com.trainserver.config.JwtUtil;

@Service
public class TokenValidator {

	private static final String SECRET_KEY = "trainticket@#$2024";

	// Method to validate the token
	public boolean validateToken(String token, String role) throws Exception {
		String[] parts = token.split("~");
		if (parts.length != 2)
			return false;

		String data = parts[0];
		String providedSignature = parts[1];

		// Recreate the signature
		String expectedSignature = new JwtUtil().sign(data, SECRET_KEY);

		// Validate the signature and email
		String[] tokenParts = data.split(":");
		if (tokenParts.length != 3)
			return false;

		return expectedSignature.equals(providedSignature) && tokenParts[1].equals(role);
	}
}

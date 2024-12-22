package com.trainserver.config;

import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
	private final String SECRET_KEY = "trainticket@#$2024";

	// Method to generate a custom token
	public String generateToken(String email, String role) throws Exception {
		String data = email + ":" + role + ":" + System.currentTimeMillis();
		String signature = sign(data, SECRET_KEY);
		return data + "~" + signature;
	}

	// Method to sign data using HMAC-SHA256
	public String sign(String data, String secret) throws Exception {
		Mac hmac = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
		hmac.init(secretKey);
		byte[] signatureBytes = hmac.doFinal(data.getBytes());
		return Base64.getUrlEncoder().withoutPadding().encodeToString(signatureBytes);
	}
}

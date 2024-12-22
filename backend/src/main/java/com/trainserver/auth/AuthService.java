package com.trainserver.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trainserver.config.JwtUtil;
import com.trainserver.helper.BadCredentialsException;
import com.trainserver.model.Admin;
import com.trainserver.model.User;
import com.trainserver.repository.AdminRepository;
import com.trainserver.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Map<String, Object> login(String email, String password, String role) throws Exception {
		Map<String, Object> response = new HashMap<>();

		if (role.equalsIgnoreCase("admin")) {
			Admin admin = adminRepository.findByEmail(email)
					.orElseThrow(() -> new BadCredentialsException("Invalid email or password !!"));
			// Match hashed password
			if (!passwordEncoder.matches(password, admin.getPassword())) {
				throw new BadCredentialsException("Invalid email or password !!");
			}

			String token = jwtUtil.generateToken(email, role);
			response.put("token", token);
			response.put("adminId", admin.getAdminId());
			response.put("role", role);
		} else if (role.equalsIgnoreCase("customer")) {
			User user = userRepository.findByEmail(email)
					.orElseThrow(() -> new BadCredentialsException("Invalid email or password !!"));
			if (!passwordEncoder.matches(password, user.getPassword())) {
				throw new BadCredentialsException("Invalid email or password !!");
			}
			String token = jwtUtil.generateToken(email, role);
			response.put("token", token);
			response.put("userId", user.getUserId());
			response.put("role", role);
		} else {
			throw new IllegalArgumentException("Invalid role !!");
		}

		return response;
	}
}

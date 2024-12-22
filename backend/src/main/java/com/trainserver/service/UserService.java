package com.trainserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trainserver.helper.UserExistsException;
import com.trainserver.model.User;
import com.trainserver.repository.UserRepository;
import java.util.Random;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public User registerUser(User user) throws UserExistsException {
		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new UserExistsException("~ User already exists !! please login. ~");
		}

		// Set UserId
		user.setUserId(generateUniqueId());
		// Hash Password before saving the data
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(user);
	}

	private String generateUniqueId() {
		String idString;
		do {
			idString = "U-" + String.format("%08d", new Random().nextInt(100000000));
		} while (userRepository.existsById(idString));

		return idString;
	}

	// View Profile by User ID
	public User getUserProfile(String userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
	}

	// Update Profile
	public User updateUserProfile(String userId, User updatedUser) {
		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

		// Update fields
		existingUser.setUserName(updatedUser.getUserName());
		existingUser.setAddress(updatedUser.getAddress());
		existingUser.setContactNumber(updatedUser.getContactNumber());

		// Save updated user
		return userRepository.save(existingUser);
	}

	public long getActiveUsersCount() {
		return userRepository.count();
	}

}
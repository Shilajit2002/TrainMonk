package com.trainserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trainserver.helper.AdminExistsException;
import com.trainserver.model.Admin;
import com.trainserver.repository.AdminRepository;
import java.util.Random;

@Service
public class AdminService {
	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public Admin registerAdmin(Admin admin) throws AdminExistsException {
		if (adminRepository.findByEmail(admin.getEmail()).isPresent()) {
			throw new AdminExistsException("~ Admin already exists !! please login. ~");
		}

		// Set AdminId
		admin.setAdminId(generateUniqueId());
		// Hash Password before saving the data
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));

		return adminRepository.save(admin);
	}

	private String generateUniqueId() {
		String idString;
		do {
			idString = "A-" + String.format("%08d", new Random().nextInt(100000000));
		} while (adminRepository.existsById(idString));

		return idString;
	}
}
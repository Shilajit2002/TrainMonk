package com.trainserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainserver.model.Admin;

//	Admin Repository Interface
public interface AdminRepository extends JpaRepository<Admin, String> {
	// Find By Email Method
	Optional<Admin> findByEmail(String email);
}
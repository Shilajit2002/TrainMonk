package com.trainserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainserver.model.User;

//	User Repository Interface
public interface UserRepository extends JpaRepository<User, String> {
	//	Find By Email Method
	Optional<User> findByEmail(String email);
}

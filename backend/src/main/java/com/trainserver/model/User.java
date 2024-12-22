package com.trainserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customer")
public class User {
	@Id
	@Column(name = "userid")
	private String userId;

	@Column(name = "username")
	private String userName;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "address")
	private String address;

	@Column(name = "contact_number", unique = true, nullable = false)
	private String contactNumber;

	@Column(name = "role")
	private String role = "customer";

	public User() {
		super();
	}

	public User(String userId, String userName, String email, String password, String address, String contactNumber,
			String role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.contactNumber = contactNumber;
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}

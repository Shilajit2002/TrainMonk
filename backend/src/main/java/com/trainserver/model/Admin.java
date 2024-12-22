package com.trainserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Admin")
public class Admin {
	@Id
	@Column(name = "adminid")
	private String adminId;

	@Column(name = "username")
	private String userName;

	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role = "admin";

	public Admin() {
		super();
	}

	public Admin(String adminId, String userName, String email, String password, String role) {
		super();
		this.adminId = adminId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
package com.trainserver.helper;

public class UserExistsException extends Exception {
	public UserExistsException(String msg) {
		super(msg);
	}
}
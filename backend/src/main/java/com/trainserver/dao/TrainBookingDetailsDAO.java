package com.trainserver.dao;

public class TrainBookingDetailsDAO {
	private String trainNumber;
	private String trainName;
	private int numberOfBookedUsers;

	public TrainBookingDetailsDAO() {
		super();
	}

	public TrainBookingDetailsDAO(String trainNumber, String trainName, int numberOfBookedUsers) {
		super();
		this.trainNumber = trainNumber;
		this.trainName = trainName;
		this.numberOfBookedUsers = numberOfBookedUsers;
	}

	public String getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public int getNumberOfBookedUsers() {
		return numberOfBookedUsers;
	}

	public void setNumberOfBookedUsers(int numberOfBookedUsers) {
		this.numberOfBookedUsers = numberOfBookedUsers;
	}
}

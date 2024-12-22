package com.trainserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BookTrain")
public class BookTrain {
	@Id
	@Column(name = "pnr")
	private String PNR;

	@Column(name = "userid")
	private String userId;

	@Column(name = "username")
	private String userName;

	@Column(name = "contact_number",nullable = false)
	private String contactNumber;

	@Column(name = "train_number")
	private String trainNumber;

	@Column(name = "train_name")
	private String trainName;

	@Column(name = "origin_station")
	private String originStation;

	@Column(name = "destination_station")
	private String destinationStation;

	@Column(name = "departure_time")
	private String departureTime;

	@Column(name = "arrival_time")
	private String arrivalTime;

	@Column(name = "date")
	private String date;

	@Column(name = "number_of_seats")
	private int numberOfSeats;

	@Column(name = "tier_class")
	private String tierClass;

	@Column(name = "price")
	private double tierClassPrice;

	@Column(name = "status")
	private String status;

	public BookTrain() {
		super();
	}

	public BookTrain(String pNR, String userId, String userName, String contactNumber, String trainNumber,
			String trainName, String originStation, String destinationStation, String departureTime, String arrivalTime,
			String date, int numberOfSeats, String tierClass, double tierClassPrice, String status) {
		super();
		PNR = pNR;
		this.userId = userId;
		this.userName = userName;
		this.contactNumber = contactNumber;
		this.trainNumber = trainNumber;
		this.trainName = trainName;
		this.originStation = originStation;
		this.destinationStation = destinationStation;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.date = date;
		this.numberOfSeats = numberOfSeats;
		this.tierClass = tierClass;
		this.tierClassPrice = tierClassPrice;
		this.status = status;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPNR() {
		return PNR;
	}

	public void setPNR(String pNR) {
		PNR = pNR;
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

	public String getOriginStation() {
		return originStation;
	}

	public void setOriginStation(String originStation) {
		this.originStation = originStation;
	}

	public String getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public String getTierClass() {
		return tierClass;
	}

	public void setTierClass(String tierClass) {
		this.tierClass = tierClass;
	}

	public double getTierClassPrice() {
		return tierClassPrice;
	}

	public void setTierClassPrice(double tierClassPrice) {
		this.tierClassPrice = tierClassPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

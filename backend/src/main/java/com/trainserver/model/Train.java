package com.trainserver.model;

import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "Train")
public class Train {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

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

	@ElementCollection
	@CollectionTable(name = "tier_class_price", joinColumns = @JoinColumn(name = "train_id"))
	@MapKeyColumn(name = "tier_class")
	@Column(name = "price")
	private Map<String, Double> tierClassPrice;

	public Train() {
		super();
	}

	public Train(Long id, String trainNumber, String trainName, String originStation, String destinationStation,
			String departureTime, String arrivalTime, String tierPrices, String date, int numberOfSeats,
			Map<String, Double> tierClassPrice) {
		super();
		this.id = id;
		this.trainNumber = trainNumber;
		this.trainName = trainName;
		this.originStation = originStation;
		this.destinationStation = destinationStation;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.date = date;
		this.numberOfSeats = numberOfSeats;
		this.tierClassPrice = tierClassPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Map<String, Double> getTierClassPrice() {
		return tierClassPrice;
	}

	public void setTierClassPrice(Map<String, Double> tierClassPrice) {
		this.tierClassPrice = tierClassPrice;
	}
}

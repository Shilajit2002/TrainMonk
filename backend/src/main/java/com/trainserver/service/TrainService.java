package com.trainserver.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainserver.helper.TrainNotFoundException;
import com.trainserver.model.Train;
import com.trainserver.repository.TrainRepository;

@Service
public class TrainService {
	@Autowired
	private TrainRepository trainRepository;

	public Train addTrain(Train train) {
		return trainRepository.save(train);
	}

	public Train updateTrain(Long id, Train trainDetails) {
		Train train = trainRepository.findById(id).orElseThrow(() -> new RuntimeException("Train not found"));
		train.setTrainNumber(trainDetails.getTrainNumber());
		train.setTrainName(trainDetails.getTrainName());
		train.setOriginStation(trainDetails.getOriginStation());
		train.setDestinationStation(trainDetails.getDestinationStation());
		train.setDepartureTime(trainDetails.getDepartureTime());
		train.setArrivalTime(trainDetails.getArrivalTime());
		train.setDate(trainDetails.getDate());
		train.setNumberOfSeats(trainDetails.getNumberOfSeats());
		train.setTierClassPrice(trainDetails.getTierClassPrice());
		return trainRepository.save(train);
	}

	public void deleteTrain(Long id) {
		Train train = trainRepository.findById(id).orElseThrow(() -> new RuntimeException("Train not found"));
		trainRepository.delete(train);
	}

	public Train fetchTrainById(Long id) {
		return trainRepository.findById(id).orElseThrow(() -> new RuntimeException("Train not found"));
	}

	public List<Train> searchTrains(String originStation, String destinationStation, String date, String tierClass) {
		List<Train> trains = trainRepository.findByOriginStationAndDestinationStationAndDate(originStation,
				destinationStation, date);

		return trains.stream().filter(train -> {
			Map<String, Double> tierClassPrice = train.getTierClassPrice();
			return tierClassPrice.containsKey(tierClass);
		}).collect(Collectors.toList());
	}

	public List<Train> fetchAllTrain() {
		return trainRepository.findAll();
	}

	public Train getTrainByNumber(String trainNumber) throws TrainNotFoundException {
		return trainRepository.findByTrainNumber(trainNumber)
				.orElseThrow(() -> new TrainNotFoundException("Train not found with number: " + trainNumber));
	}

	public long getTrainCount() {
		return trainRepository.count(); // JPA provides a built-in `count` method
	}
}

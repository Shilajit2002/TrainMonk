package com.trainserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trainserver.model.Train;

public interface TrainRepository extends JpaRepository<Train, Long> {

	List<Train> findByOriginStationAndDestinationStationAndDate(String originStation, String destinationStation,
			String date);
	
	Optional<Train> findByTrainNumber(String trainNumber);
}

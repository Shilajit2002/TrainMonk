package com.trainserver.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainserver.dao.TrainBookingDetailsDAO;
import com.trainserver.helper.TrainNotFoundException;
import com.trainserver.model.BookTrain;
import com.trainserver.model.Train;
import com.trainserver.repository.BookTrainRepository;

@Service
public class BookTrainService {
	@Autowired
	private BookTrainRepository bookTrainRepository;

	@Autowired
	private TrainService trainService; // Inject TrainService to manage train-related operations

	public BookTrain bookNewTrain(BookTrain bookTrain) throws TrainNotFoundException {
		// Step 1: Fetch the train details by trainNumber
		Train train = trainService.getTrainByNumber(bookTrain.getTrainNumber());

		if (train == null) {
			throw new TrainNotFoundException("Train not found for number: " + bookTrain.getTrainNumber());
		}

		// Step 2: Check if enough seats are available
		int seatsAvailable = train.getNumberOfSeats();
		if (seatsAvailable <= 0 || seatsAvailable < bookTrain.getNumberOfSeats()) {
			throw new IllegalArgumentException("Not enough seats available for booking.");
		}

		// Step 3: Decrease the number of available seats
		train.setNumberOfSeats(seatsAvailable - bookTrain.getNumberOfSeats());
		trainService.updateTrain(train.getId(), train);

		// Step 4: Set PNR
		bookTrain.setPNR(generateUniquePNR());

		// Step 5: Set Status
		bookTrain.setStatus("BOOKED");

		// Step 6: Save the booking
		return bookTrainRepository.save(bookTrain);
	}

	public BookTrain cancelBookedTrain(BookTrain bookTrain) throws TrainNotFoundException {
		// Step 1: Fetch the train details by trainNumber
		Train train = trainService.getTrainByNumber(bookTrain.getTrainNumber());

		if (train == null) {
			throw new TrainNotFoundException("Train not found for number: " + bookTrain.getTrainNumber());
		}

		// Step 2: Check seats
		int seatsAvailable = train.getNumberOfSeats();

		// Step 3: Increase the number of available seats
		train.setNumberOfSeats(seatsAvailable + bookTrain.getNumberOfSeats());
		trainService.updateTrain(train.getId(), train);

		// Step 5: Set Status
		BookTrain bookTrainData = bookTrainRepository.findByPNR(bookTrain.getPNR())
				.orElseThrow(() -> new RuntimeException("Train not found"));
		bookTrainData.setStatus("CANCELLED");

		// Step 4: Save the booking
		return bookTrainRepository.save(bookTrainData);
	}

	public Optional<List<BookTrain>> fetchAllBookedTrainBuUserId(String userId) {
		return bookTrainRepository.findByUserId(userId);
	}

	private String generateUniquePNR() {
		String idString;
		do {
			idString = "PNR-" + String.format("%08d", new Random().nextInt(100000000));
		} while (bookTrainRepository.existsById(idString));

		return idString;
	}

	public List<TrainBookingDetailsDAO> getTrainBookingDetails() {
	    List<Object[]> results = bookTrainRepository.getTrainBookingDetails();

	    // Map raw results into DTO objects
	    return results.stream().map(result -> new TrainBookingDetailsDAO(
	            (String) result[0], // Train Number
	            (String) result[1], // Train Name
	            ((Number) result[2]).intValue() // Number of Booked Users
	    )).toList();
	}
}

package com.trainserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.trainserver.model.BookTrain;

public interface BookTrainRepository extends JpaRepository<BookTrain, String> {
	Optional<BookTrain> findByPNR(String PNR);

	Optional<List<BookTrain>> findByUserId(String userId);

	@Query("""
			    SELECT t.trainNumber, t.trainName, COUNT(DISTINCT b.userId)
			    FROM Train t
			    LEFT JOIN BookTrain b ON t.trainNumber = b.trainNumber
			    GROUP BY t.trainNumber, t.trainName
			""")
	List<Object[]> getTrainBookingDetails();
}

package com.publicissapient.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.publicissapient.entity.Booking;


public interface BookingRepository extends JpaRepository<Booking,Integer> {

	@Query("UPDATE Booking SET paymentStatus = :paymentStatus , bookingStatus =:bookingStatus WHERE bookingId = :bookingId")
	@Transactional
	@Modifying
	Integer makePayment(String paymentStatus,String bookingStatus,Integer bookingId);
	
	@Query("UPDATE Booking SET staffId = :staffId WHERE bookingId = :bookingId")
	@Transactional
	@Modifying
	Integer assignStaff(Integer staffId,Integer bookingId);
	
}

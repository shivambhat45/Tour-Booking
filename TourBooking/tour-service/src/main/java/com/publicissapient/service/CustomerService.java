package com.publicissapient.service;

import java.util.List;

import com.publicissapient.dto.BookingDto;
import com.publicissapient.entity.Booking;
import com.publicissapient.entity.Package;
import com.publicissapient.exceptions.BookingNotFoundException;

public interface CustomerService {

	public List<Package> listAllPackages();
	
	public Booking makeBooking(BookingDto bookingDto);
	
	public Integer makePayment(Integer bookingId) throws BookingNotFoundException;
	
	public Integer sendNotification(String to, String subject, String message);
}

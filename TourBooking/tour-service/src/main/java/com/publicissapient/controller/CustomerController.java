package com.publicissapient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publicissapient.dto.BookingDto;
import com.publicissapient.entity.Booking;
import com.publicissapient.entity.Package;
import com.publicissapient.exceptions.BookingNotFoundException;
import com.publicissapient.service.CustomerService;
import com.publicissapient.util.Message;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping(path = "/listPackages", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "request.customer.listPackages")
	public ResponseEntity<List<Package>> listPackages() {
		return ResponseEntity.ok(customerService.listAllPackages());
	}

	@PostMapping(path = "/bookTour", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "request.customer.bookTour")
	public ResponseEntity<Booking> bookTour(@RequestBody BookingDto bookingdto) {
		return ResponseEntity.ok(customerService.makeBooking(bookingdto));
	}

	@PutMapping(path = "/makePayment/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "request.customer.makePayment")
	public ResponseEntity<Message> makePayment(@PathVariable("bookingId") Integer bookingId)
			throws BookingNotFoundException {
		customerService.makePayment(bookingId);
		return ResponseEntity.ok(new Message("Payment Done Successfully"));
	}

	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<Booking> handlePackageNotFound(BookingNotFoundException e) {
		return ResponseEntity.ok(new Booking());
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Message> handleError(RuntimeException e) {
		return ResponseEntity.ok(new Message(e.getMessage()));
	}

}

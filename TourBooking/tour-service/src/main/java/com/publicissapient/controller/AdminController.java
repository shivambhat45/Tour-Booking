package com.publicissapient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publicissapient.dto.PackageDto;
import com.publicissapient.service.AdminService;
import com.publicissapient.util.Message;

import io.micrometer.core.annotation.Timed;

import com.publicissapient.entity.Booking;
import com.publicissapient.entity.Package;
import com.publicissapient.exceptions.BookingNotFoundException;
import com.publicissapient.exceptions.PackageAlreadyExistsException;
import com.publicissapient.exceptions.PackageNotFoundException;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@PostMapping(path = "/createPackage", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "request.admin.createPackage")
	public ResponseEntity<Message> createPackage(@RequestBody PackageDto packageDto)
			throws PackageAlreadyExistsException {
		adminService.createPackage(packageDto);
		return ResponseEntity.ok(new Message("Package Added Successfully"));

	}

	@PutMapping(path = "/updatePackage", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "request.admin.updatePackage")
	public ResponseEntity<Message> updatePackage(@RequestBody PackageDto packageDto) throws PackageNotFoundException {
		adminService.updatePackage(packageDto);
		return ResponseEntity.ok(new Message("Package Updated Successfully"));
		// Name Should Not Be Updated
	}

	@DeleteMapping(path = "/deletePackage/{packageName}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "request.admin.deletePackage")
	public ResponseEntity<Message> deletePackage(@PathVariable("packageName") String packageName)
			throws PackageNotFoundException {
		adminService.deletePackage(packageName);
		return ResponseEntity.ok(new Message("Package Deleted Successfully"));

	}

	@GetMapping(path = "/listPackages", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "request.admin.listPackages")
	public ResponseEntity<List<Package>> listPackages() {
		return ResponseEntity.ok(adminService.listAllPackages());
	}

	@GetMapping(path = "/listBookings", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "request.admin.listBookings")
	public ResponseEntity<List<Booking>> listBooking() {
		return ResponseEntity.ok(adminService.listAllBooking());
	}

	@DeleteMapping(path = "/deleteBooking/{bookingId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "request.admin.deleteBooking")
	public ResponseEntity<Message> deletePackage(@PathVariable("bookingId") Integer bookingId)
			throws BookingNotFoundException {
		adminService.deleteBooking(bookingId);
		return ResponseEntity.ok(new Message("Booking Deleted Successfully"));

	}

	@PutMapping(path = "/assignStaff/{staffId}/{bookingId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "request.admin.assignStaff")
	public ResponseEntity<Message> assignStaff(@PathVariable("staffId") Integer staffId,
			@PathVariable("bookingId") Integer bookingId) throws BookingNotFoundException {
		adminService.addStaffToBooking(staffId, bookingId);
		return ResponseEntity.ok(new Message("Staff Has Been Assigned"));
	}

	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<Message> handlePackageNotFound(BookingNotFoundException e) {
		return ResponseEntity.ok(new Message("Booking Not Found"));
	}

	@ExceptionHandler(PackageNotFoundException.class)
	public ResponseEntity<Message> handlePackageNotFound(PackageNotFoundException e) {
		return ResponseEntity.ok(new Message("Package Not Found"));
	}

	@ExceptionHandler(PackageAlreadyExistsException.class)
	public ResponseEntity<Message> handlePackageAlreadyExistsException(PackageAlreadyExistsException e) {
		return ResponseEntity.ok(new Message("Package Already Exists"));
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Message> handleRuntimeException(RuntimeException e) {
		return ResponseEntity.ok(new Message(e.getMessage()));
	}

}

package com.publicissapient.service;

import java.util.List;

import com.publicissapient.entity.Booking;
import com.publicissapient.entity.Package;
import com.publicissapient.exceptions.BookingNotFoundException;
import com.publicissapient.exceptions.PackageAlreadyExistsException;
import com.publicissapient.exceptions.PackageNotFoundException;
import com.publicissapient.dto.PackageDto;


public interface AdminService {

	public Package createPackage(PackageDto packageDto) throws PackageAlreadyExistsException;
	
	public void updatePackage(PackageDto packageDto) throws PackageNotFoundException;
	
	public void deletePackage(String packageName) throws PackageNotFoundException;
	
	public List<Package> listAllPackages();
	
	public List<Booking> listAllBooking();
	
	public void deleteBooking(Integer bookingId) throws BookingNotFoundException;
	
	public void addStaffToBooking(Integer staffId,Integer bookingId) throws BookingNotFoundException;
	
}

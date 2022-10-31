package com.publicissapient.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.publicissapient.dto.PackageDto;
import com.publicissapient.dto.RoleDto;
import com.publicissapient.dto.UserDto;
import com.publicissapient.exceptions.BookingNotFoundException;
import com.publicissapient.exceptions.PackageAlreadyExistsException;
import com.publicissapient.exceptions.PackageNotFoundException;
import com.publicissapient.repository.BookingRepository;
import com.publicissapient.repository.PackageRepo;
import com.publicissapient.entity.Booking;
import com.publicissapient.entity.Package;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private PackageRepo packageRepository;

	@Autowired
	BookingRepository bookingRepository;

	@Override
	public Package createPackage(PackageDto packageDto) throws PackageAlreadyExistsException {
		if (packageRepository.existsByPackageName(packageDto.getPackageName()).booleanValue()) {
			throw new PackageAlreadyExistsException();
		} else {
			Package packageCreated = new Package(packageDto.getPackageName(), packageDto.getPackageDetails(),
					packageDto.getPackageFees(), packageDto.getPackageFacilities());

			return packageRepository.save(packageCreated);
		}
	}

	@Override
	public void updatePackage(PackageDto packageDto) throws PackageNotFoundException {
		if (packageRepository.existsByPackageName(packageDto.getPackageName()).booleanValue()) {

			packageRepository.updatePackage(packageDto.getPackageName(), packageDto.getPackageDetails(),
					packageDto.getPackageFees(), packageDto.getPackageFacilities());
		} else {
			throw new PackageNotFoundException();
		}
	}

	@Override
	public void deletePackage(String packageName) throws PackageNotFoundException {
		if (packageRepository.existsByPackageName(packageName).booleanValue()) {
			packageRepository.deletePackageByName(packageName);
		} else {
			throw new PackageNotFoundException();
		}
	}
	
	@Override
	public List<Package> listAllPackages() {
		return packageRepository.findAll();
	}
	
	@Override
	public List<Booking> listAllBooking() {
		return bookingRepository.findAll();
	}

	@Override
	public void deleteBooking(Integer bookingId) throws BookingNotFoundException {
		if (bookingRepository.existsById(bookingId)) {
			bookingRepository.deleteById(bookingId);

		} else {
			throw new BookingNotFoundException();
		}

	}
	
	@Override
	public void addStaffToBooking(Integer staffId, Integer bookingId) throws BookingNotFoundException {
		if (bookingRepository.existsById(bookingId))

		{
			HttpHeaders headers = new HttpHeaders();
			headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

			HttpEntity<Void> req = new HttpEntity<>(headers);

			ResponseEntity<UserDto> responseStaff = restTemplate.exchange("http://registration-service/find/" + staffId,
					HttpMethod.GET, req, UserDto.class);
			
			if (responseStaff.getBody().getUsername() != null) {
				List<RoleDto> roles = (List<RoleDto>) responseStaff.getBody().getRoles();
				boolean isStaff = false;
				for (int i = 0; i < roles.size(); i++) {
					if (roles.get(i).getRoleName().equals("STAFF")) {
						isStaff = true;
						break;
					}
				}
				if (isStaff) {
					bookingRepository.assignStaff(staffId, bookingId);
				} else {
					throw new RuntimeException("Not a Staff Member");
				}
			} else {
				throw new RuntimeException("Staff Not Found");
			}
		}

		else {
			throw new BookingNotFoundException();
		}

	}

}

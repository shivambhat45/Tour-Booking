package com.publicissapient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.publicissapient.dto.BookingDto;


@RestController
public class CustomerController {
	
	@Autowired
	RestTemplate restTemplate;

	static final String ACCEPT="Accept";
	static final String CONTENTTYPE="Content-Type";
	
	@GetMapping(path = "/bookTour")
	public ModelAndView registrationForm(@RequestParam("packageId") int packageId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("packageId",packageId);
		mv.setViewName("booking");
		return mv;
	}
	
	@PostMapping(path = "/bookTour")
	public ModelAndView saveUser(BookingDto bookingDto) {
		ModelAndView mv = new ModelAndView();

		HttpHeaders headers = new HttpHeaders();

		headers.set(ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.set(CONTENTTYPE, MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<BookingDto> req = new HttpEntity<>(bookingDto, headers);
		

		ResponseEntity<BookingDto> response = restTemplate.exchange("http://localhost:8087/customer/bookTour", HttpMethod.POST,
				req, BookingDto.class);

		if (response.getBody().getPackageStandard()==null) {
			mv.setViewName("error-customer-booking"); // Create Page for it
		} else {
			mv.addObject("bookingInfo",response.getBody());
			mv.setViewName("booking-success");
		}
		return mv;

	}
	
	@PostMapping(path = "/makePayment")
	public ModelAndView makePayment(@RequestParam("bookingId") int bookingId) {
		ModelAndView mv = new ModelAndView();
		
		HttpHeaders headers = new HttpHeaders();

		headers.set(ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.set(CONTENTTYPE, MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<String> req = new HttpEntity<>(headers);

		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8087/customer/makePayment/"+bookingId, HttpMethod.PUT,
				req, String.class);

		if(response.getBody().contains("Payment Done Successfully"))
		{
			mv.addObject("bookingId",bookingId);
			mv.setViewName("payment-success");
		}
		else {
			mv.setViewName("error-payment");
		}
		
		return mv;
	}
}

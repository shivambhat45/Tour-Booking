package com.publicissapient;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicissapient.dto.BookingDto;
import com.publicissapient.dto.EmailMessageDto;
import com.publicissapient.dto.UserDto;
import com.publicissapient.entity.Booking;
import com.publicissapient.entity.Package;
import com.publicissapient.repository.BookingRepository;
import com.publicissapient.repository.PackageRepo;
import com.publicissapient.service.AdminServiceImpl;
import com.publicissapient.util.Message;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TourServiceRestTest {

	@Autowired
	MockMvc mvc;

	@LocalServerPort
	int port;

	@MockBean
	PackageRepo packageRepository;

	@MockBean
	BookingRepository bookingRepository;

	@Mock
	RestTemplate restTemplate;

	@InjectMocks
	private AdminServiceImpl adminService = new AdminServiceImpl();

	@Test
	void testAddStaffToBooking() throws Exception {
		when(bookingRepository.existsById(1)).thenReturn(true);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<Void> req = new HttpEntity<>(headers);

		UserDto userdto = new UserDto();

		when(restTemplate.exchange("http://registration-service/find/" + 3, HttpMethod.GET, req, UserDto.class))
				.thenReturn(new ResponseEntity<UserDto>(userdto, HttpStatus.OK));

		mvc.perform(put("/admin/assignStaff/3/1")).andDo(print()).andExpect(status().is2xxSuccessful());

	}

	@Test
	void testAddStaffToBookingFailure() throws Exception {
		when(bookingRepository.existsById(1000)).thenReturn(false);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<Void> req = new HttpEntity<>(headers);

		UserDto userdto = new UserDto();

		when(restTemplate.exchange("http://registration-service/find/" + 3, HttpMethod.GET, req, UserDto.class))
				.thenReturn(new ResponseEntity<UserDto>(userdto, HttpStatus.OK));

		mvc.perform(put("/admin/assignStaff/3/1000")).andDo(print())
				.andExpect(jsonPath("$.status").value("Booking Not Found"));

	}

	@Test
	void testAddStaffToBookingFailure2() throws Exception {

		when(bookingRepository.existsById(1)).thenReturn(true);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<Void> req = new HttpEntity<>(headers);

		UserDto userdto = new UserDto();

		when(restTemplate.exchange("http://registration-service/find/" + 300, HttpMethod.GET, req, UserDto.class))
				.thenReturn(new ResponseEntity<UserDto>(userdto, HttpStatus.OK));

		mvc.perform(put("/admin/assignStaff/300/1")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testMakeBooking() throws Exception {
		BookingDto bookingObj = new BookingDto("Payment-Pending", 1, "Executive", 2500.0, "False", 0, "20/10/2020",
				"21/10/2020", 2, 1, 4, true);
		ObjectMapper mapper = new ObjectMapper();
		String bookingJson = mapper.writeValueAsString(bookingObj);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> req = new HttpEntity<>(headers);

		UserDto userdto = new UserDto("Sahani", "Gaurav", 24, 87654321, "efg@gmail.com", "Jamnagar", "male", "gaurav",
				"$2a$10$jaaF5BFDGclJ49WJSZVEQOLLeeaMYRsHU92M2wXH44k5cReyu/Bv.");

		when(packageRepository.existsById(4)).thenReturn(true);
		when(packageRepository.findById(4))
				.thenReturn(Optional.of(new Package("Mumbai-tour", "7N/8D tour to Kerala", 8999.0, "random")));

		when(restTemplate.exchange("http://registration-service/find/" + 1, HttpMethod.GET, req, UserDto.class))
				.thenReturn(new ResponseEntity<UserDto>(userdto, HttpStatus.OK));

		mvc.perform(post("/customer/bookTour").content(bookingJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testMakeBookingFailure() throws Exception {
		BookingDto bookingObj = new BookingDto("Payment-Pending", 1, "Executive", 2500.0, "False", 0, "20/10/2020",
				"21/10/2020", 2, 1, 4, true);
		ObjectMapper mapper = new ObjectMapper();
		String bookingJson = mapper.writeValueAsString(bookingObj);

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> req = new HttpEntity<>(headers);

		UserDto userdto = new UserDto("Sahani", "Gaurav", 24, 87654321, "efg@gmail.com", "Jamnagar", "male", "gaurav",
				"$2a$10$jaaF5BFDGclJ49WJSZVEQOLLeeaMYRsHU92M2wXH44k5cReyu/Bv.");

		when(packageRepository.existsById(4)).thenReturn(false);

		when(restTemplate.exchange("http://localhost:8082/find/" + 1, HttpMethod.GET, req, UserDto.class))
				.thenReturn(new ResponseEntity<UserDto>(userdto, HttpStatus.OK));

		mvc.perform(post("/customer/bookTour").content(bookingJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Either Customer or Package does not exists"));

	}

	@Test
	void testMakePaymentSuccess() throws Exception {
		Booking bookingObj = new Booking("Payment-Pending", 1, "Executive", 2500.0, "False", 0, "20/10/2020",
				"21/10/2020", 2, 1, 4, true);
		EmailMessageDto emailMsg = new EmailMessageDto("test@gmail.com", "test", "test");
		
		when(bookingRepository.existsById(1)).thenReturn(true);
		when(bookingRepository.findById(1)).thenReturn(Optional.of(bookingObj));

		HttpHeaders headers = new HttpHeaders();
		HttpHeaders headersEmail = new HttpHeaders();
		
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> req = new HttpEntity<>(headers);
		
		headersEmail.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headersEmail.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<EmailMessageDto> request = new HttpEntity<>(emailMsg, headersEmail);

		UserDto userdto = new UserDto("Sahani", "Gaurav", 24, 87654321, "efg@gmail.com", "Jamnagar", "male", "gaurav",
				"$2a$10$jaaF5BFDGclJ49WJSZVEQOLLeeaMYRsHU92M2wXH44k5cReyu/Bv.");

		when(restTemplate.exchange("http://registration-service/find/" + 1, HttpMethod.GET, req, UserDto.class))
				.thenReturn(new ResponseEntity<UserDto>(userdto, HttpStatus.OK));

		Message messageDto = new Message("Email Sent");
		
		when(restTemplate.exchange("http://notification-service/email/send-email", HttpMethod.POST, request,
				Message.class)).thenReturn(new ResponseEntity<Message>(messageDto, HttpStatus.OK));

		mvc.perform(put("/customer/makePayment/1")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testMakePaymentFailure() throws Exception {
		when(bookingRepository.existsById(1)).thenReturn(false);

		mvc.perform(put("/customer/makePayment/1")).andDo(print()).andExpect(status().isOk());

	}

}

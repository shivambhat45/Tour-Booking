package com.publicissapient;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicissapient.dto.PackageDto;
import com.publicissapient.repository.BookingRepository;
import com.publicissapient.repository.PackageRepo;
import com.publicissapient.service.AdminServiceImpl;
import com.publicissapient.entity.Booking;
import com.publicissapient.entity.Package;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TourServiceApplicationTest {

	@Autowired
	MockMvc mvc;

	@LocalServerPort
	int port;

	@MockBean
	PackageRepo packageRepository;

	@MockBean
	BookingRepository bookingRepository;

	@Autowired
	TestRestTemplate template;

	@Autowired
	RestTemplate restTemplate;

	@InjectMocks
	private AdminServiceImpl adminService = new AdminServiceImpl();

	@Test
	void testCreatePackageSuccess() throws Exception {
		PackageDto packageObj = new PackageDto("Kerala-tour", "4N/5D tour to Kerala", 8999.0,
				"Hotel-Stay,Free-Food,Free-Transportation");
		ObjectMapper mapper = new ObjectMapper();
		String packageJson = mapper.writeValueAsString(packageObj);

		when(packageRepository.existsByPackageName("Kerala-tour")).thenReturn(false);

		mvc.perform(post("/admin/createPackage").content(packageJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Package Added Successfully"));

	}

	@Test
	void testCreatePackageFailure() throws Exception {
		PackageDto packageObj = new PackageDto("Kerala-tour", "4N/5D tour to Kerala", 8999.0,
				"Hotel-Stay,Free-Food,Free-Transportation");
		ObjectMapper mapper = new ObjectMapper();
		String packageJson = mapper.writeValueAsString(packageObj);

		when(packageRepository.existsByPackageName("Kerala-tour")).thenReturn(true);

		mvc.perform(post("/admin/createPackage").content(packageJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Package Already Exists"));

	}

	@Test
	void testUpdatePackageSuccess() throws Exception {
		PackageDto packageObj = new PackageDto("Kerala-tour", "4N/5D tour to Kerala", 8999.0,
				"Hotel-Stay,Free-Food,Free-Transportation");
		ObjectMapper mapper = new ObjectMapper();
		String packageJson = mapper.writeValueAsString(packageObj);

		when(packageRepository.existsByPackageName("Kerala-tour")).thenReturn(true);

		mvc.perform(put("/admin/updatePackage").content(packageJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Package Updated Successfully"));

	}

	@Test
	void testUpdatePackageFailure() throws Exception {
		PackageDto packageObj = new PackageDto("Kerala-tour", "4N/5D tour to Kerala", 8999.0,
				"Hotel-Stay,Free-Food,Free-Transportation");
		ObjectMapper mapper = new ObjectMapper();
		String packageJson = mapper.writeValueAsString(packageObj);

		when(packageRepository.existsByPackageName("Kerala-tour")).thenReturn(false);

		mvc.perform(put("/admin/updatePackage").content(packageJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Package Not Found"));

	}

	@Test
	void testDeletePackageSuccess() throws Exception {
		PackageDto packageObj = new PackageDto("Kerala-tour", "4N/5D tour to Kerala", 8999.0,
				"Hotel-Stay,Free-Food,Free-Transportation");
		ObjectMapper mapper = new ObjectMapper();
		String packageJson = mapper.writeValueAsString(packageObj);

		when(packageRepository.existsByPackageName("Kerala-tour")).thenReturn(true);

		mvc.perform(delete("/admin/deletePackage/Kerala-tour").content(packageJson)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Package Deleted Successfully"));

	}

	@Test
	void testDeletePackageFailure() throws Exception {
		PackageDto packageObj = new PackageDto("Kerala-tour", "4N/5D tour to Kerala", 8999.0,
				"Hotel-Stay,Free-Food,Free-Transportation");
		ObjectMapper mapper = new ObjectMapper();
		String packageJson = mapper.writeValueAsString(packageObj);

		when(packageRepository.existsByPackageName("Kerala-tour")).thenReturn(false);

		mvc.perform(delete("/admin/deletePackage/Kerala-tour").content(packageJson)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Package Not Found"));

	}

	@Test
	void testListPackage() throws Exception {
		List<Package> list = new ArrayList<>();
		list.add(
				new Package("Kerala-tour", "4N/5D tour to Kerala", 8999.0, "Hotel-Stay,Free-Food,Free-Transportation"));
		when(packageRepository.findAll()).thenReturn(list);

		MvcResult result = mvc.perform(get("/admin/listPackages")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		String content = result.getResponse().getContentAsString();

		Assertions.assertTrue(content.length() > 0);

	}

	@Test
	void testDeleteBookingSucess() throws Exception {

		when(bookingRepository.existsById(1)).thenReturn(true);

		mvc.perform(delete("/admin/deleteBooking/1").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Booking Deleted Successfully"));

	}

	@Test
	void testDeleteBookingFailure() throws Exception {

		when(bookingRepository.existsById(1)).thenReturn(false);

		mvc.perform(delete("/admin/deleteBooking/1").contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.status").value("Booking Not Found"));

	}

	@Test
	void testCustomerListPackage() throws Exception {
		List<Package> list = new ArrayList<>();
		list.add(
				new Package("Kerala-tour", "4N/5D tour to Kerala", 8999.0, "Hotel-Stay,Free-Food,Free-Transportation"));
		when(packageRepository.findAll()).thenReturn(list);

		MvcResult result = mvc.perform(get("/customer/listPackages")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		String content = result.getResponse().getContentAsString();

		Assertions.assertTrue(content.length() > 0);
	}

	@Test
	void testListBooking() throws Exception {

		List<Booking> list = new ArrayList<>();
		list.add(new Booking("Payment-Pending", 1, "Executive", 2500.0, "False", 0, "20/10/2020", "21/10/2020", 2, 1, 4,
				true));
		when(bookingRepository.findAll()).thenReturn(list);

		MvcResult result = mvc.perform(get("/admin/listBookings")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		String content = result.getResponse().getContentAsString();

		Assertions.assertTrue(content.length() > 0);

	}

}

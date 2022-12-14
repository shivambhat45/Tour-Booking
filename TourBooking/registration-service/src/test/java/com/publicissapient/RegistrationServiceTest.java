package com.publicissapient;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicissapient.dto.SignUpDto;
import com.publicissapient.entity.User;
import com.publicissapient.repository.UserRepository;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RegistrationServiceTest {
	
	@Autowired
	MockMvc mvc;

	@MockBean
	UserRepository userRepo;
	
	@Test
	void testSaveUserSuccess() throws Exception
	{
		SignUpDto user=new SignUpDto("Ranjan","Adarsh",22,87654321,"abcd@gmail.com","Jamnagar","male","adi","hello","ADMIN");
		ObjectMapper mapper = new ObjectMapper();
		String userJson=mapper.writeValueAsString(user);
		
		when(userRepo.findByUsername("adi")).thenReturn(null);
		 
		mvc.perform(post("/registration").content(userJson).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	
	@Test
	void testSaveUserFailure() throws Exception
	{
		SignUpDto user=new SignUpDto("Ranjan","Adarsh",22,87654321,"abcd@gmail.com","Jamnagar","male","adi","hello","ADMIN");
		ObjectMapper mapper = new ObjectMapper();
		String userJson=mapper.writeValueAsString(user);
		
		when(userRepo.findByUsername("adi")).thenReturn(new User());
		 
		 mvc.perform(post("/registration").content(userJson).contentType(MediaType.APPLICATION_JSON_VALUE))
		.andDo(print())
		.andExpect(status().isOk());
		
	}
	
	@Test
	void testRegistration() throws Exception
	{
		 mvc.perform(get("/registration"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.status").value("It will Display Registration Form"));
	}
	
	@Test
	void testLogin() throws Exception
	{
		 mvc.perform(get("/").with(user("shivam").password("1234")))
		.andDo(print());
		 Assertions.assertTrue(true);
	}
	
		
	@Test
	void testFindUserFailure() throws Exception
	{
		when(userRepo.existsById(1)).thenReturn(false);
		
		 mvc.perform(get("/find/1"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.status").value("User Not Found"));
	}
	
	@Test
	void testFindUserSuccess() throws Exception
	{
		when(userRepo.existsById(1)).thenReturn(true);
		when(userRepo.findById(1)).thenReturn(Optional.ofNullable(new User("Sahani","Gaurav",24,87654321,"efg@gmail.com","Jamnagar","male","gaurav","$2a$10$jaaF5BFDGclJ49WJSZVEQOLLeeaMYRsHU92M2wXH44k5cReyu/Bv.")));
				
		 mvc.perform(get("/find/1"))
		.andDo(print())
		.andExpect(status().isOk());
	}
	
	@Test
	void testList() throws Exception
	{

        List<User> list = new ArrayList<>();
        list.add(new User("Sahani","Gaurav",24,87654321,"efg@gmail.com","Jamnagar","male","gaurav","$2a$10$jaaF5BFDGclJ49WJSZVEQOLLeeaMYRsHU92M2wXH44k5cReyu/Bv."));
        when(userRepo.findAll()).thenReturn(list);

        MvcResult result= 
        mvc.perform(get("/list"))
       .andDo(print()).andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        
        String content=result.getResponse().getContentAsString();
        
        Assertions.assertTrue(content.length()>0);	 
		 
	}


	


}

package com.publicissapient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publicissapient.dto.SignUpDto;
import com.publicissapient.entity.User;
import com.publicissapient.exception.UserAlreadyExistsException;
import com.publicissapient.service.UserService;
import com.publicissapient.util.Message;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/registration")
public class UserRegistrationController {

	@Autowired
	UserService userService;
		
	@GetMapping(path="",produces=MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "request.registration")
	public ResponseEntity<Message> showRegistrationForm() {
		return ResponseEntity.ok(new Message("It will Display Registration Form"));
	}
	
	@PostMapping(path="",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	@Timed(value = "request.registration")
	public ResponseEntity<User> registerUserAccount(@RequestBody SignUpDto registrationDto) throws UserAlreadyExistsException {
		return ResponseEntity.ok(userService.save(registrationDto));
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<User> handleEmployeeNotExists(UserAlreadyExistsException e)
	{
		return ResponseEntity.ok(new User());
	}
}

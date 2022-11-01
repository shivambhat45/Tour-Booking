package com.publicissapient.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publicissapient.entity.User;
import com.publicissapient.exception.UserNotFoundException;
import com.publicissapient.service.UserServiceImpl;
import com.publicissapient.util.Message;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping(path = "")
public class MainController {

	@Autowired
	UserServiceImpl userService;

	@GetMapping("/")
	@Timed(value = "request.home")
	public void home(HttpServletResponse httpServletResponse) throws IOException {
		httpServletResponse.sendRedirect("http://34.229.14.37:8086/home");
	}

	@GetMapping("/find/{userId}")
	@Timed(value = "request.find")
	public ResponseEntity<User> getUser(@PathVariable("userId") Integer userId) throws UserNotFoundException {
		return ResponseEntity.ok(userService.findUser(userId));
	}

	@GetMapping("/list")
	@Timed(value = "request.list")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.findAllUsers());
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Message> handlePackageAlreadyExistsException(UserNotFoundException e) {
		return ResponseEntity.ok(new Message("User Not Found"));
	}

}

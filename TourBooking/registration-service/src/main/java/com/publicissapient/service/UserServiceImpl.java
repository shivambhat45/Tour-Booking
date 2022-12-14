package com.publicissapient.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.publicissapient.dto.SignUpDto;
import com.publicissapient.entity.Role;
import com.publicissapient.entity.User;
import com.publicissapient.exception.UserAlreadyExistsException;
import com.publicissapient.exception.UserNotFoundException;
import com.publicissapient.repository.RoleRepository;
import com.publicissapient.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User save(SignUpDto registrationDto) throws UserAlreadyExistsException {
		if (userRepository.findByUsername(registrationDto.getUsername()) == null) {
			User user;
			if (roleRepository.findByRoleName(registrationDto.getRole()) != null) {
				user = new User(registrationDto.getLastName(), registrationDto.getFirstName(), registrationDto.getAge(),
						registrationDto.getPhoneNo(), registrationDto.getEmail(), registrationDto.getAddress(),
						registrationDto.getGender(), registrationDto.getUsername(),
						passwordEncoder.encode(registrationDto.getPassword()),
						Arrays.asList(roleRepository.findByRoleName(registrationDto.getRole())));
			} else {
				user = new User(registrationDto.getLastName(), registrationDto.getFirstName(), registrationDto.getAge(),
						registrationDto.getPhoneNo(), registrationDto.getEmail(), registrationDto.getAddress(),
						registrationDto.getGender(), registrationDto.getUsername(),
						passwordEncoder.encode(registrationDto.getPassword()),
						Arrays.asList(new Role(registrationDto.getRole())));
			}
			return userRepository.save(user);
		} else {
			throw new UserAlreadyExistsException();
		}
	}

	@Override
	public UserDetails loadUserByUsername(String usernameorEmail) throws UsernameNotFoundException {

		User user = userRepository.findByUsernameOrEmail(usernameorEmail, usernameorEmail);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new RegistrationUserDetails(user);
	}



	@Override
	public User findUser(Integer userId) throws UserNotFoundException {
		if (userRepository.existsById(userId)) {
			return userRepository.findById(userId).get();
		} else {
			throw new UserNotFoundException();
		}
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}
}

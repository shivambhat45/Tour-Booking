package com.publicissapient.dto;

import java.util.Collection;

public class UserDto {

	private Integer userId;

	private String lastName;

	private String firstName;

	private Integer age;

	private Integer phoneNo;

	private String email;

	private String address;

	private String gender;

	private String username;

	private String password;

	private Collection<RoleDto> roles;

	public String getLastName() {
		return lastName;
	}

	public UserDto() {

	}

	public UserDto(String lastName, String firstName, Integer age, Integer phoneNo, String email, String address,
			String gender, String username, String password) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.age = age;
		this.phoneNo = phoneNo;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.username = username;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public Collection<RoleDto> getRoles() {
		return roles;
	}

}

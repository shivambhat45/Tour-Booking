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

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(Integer userId, String lastName, String firstName, Integer age, Integer phoneNo, String email,
			String address, String gender, String username, String password, Collection<RoleDto> roles) {
		super();
		this.userId = userId;
		this.lastName = lastName;
		this.firstName = firstName;
		this.age = age;
		this.phoneNo = phoneNo;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Integer phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(Collection<RoleDto> roles) {
		this.roles = roles;
	}

}

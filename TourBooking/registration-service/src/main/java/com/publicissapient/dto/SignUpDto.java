package com.publicissapient.dto;

public class SignUpDto {

	private String lastName;

	private String firstName;

	private Integer age;

	private Integer phoneNo;

	private String email;

	private String address;

	private String gender;

	private String username;

	private String password;

	private String role;

	public SignUpDto() {

	}

	public SignUpDto(String lastName, String firstName, Integer age, Integer phoneNo, String email, String address,
			String gender, String username, String password, String role) {
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
		this.role = role;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

}

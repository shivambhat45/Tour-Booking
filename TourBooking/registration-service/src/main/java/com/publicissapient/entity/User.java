package com.publicissapient.entity;

import java.util.Collection;
import java.util.Iterator;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "AGE")
	private Integer age;

	@Column(name = "PHONE_NO")
	private Integer phoneNo;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ADDRESS")
	private String address;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID") })
	Collection<Role> roles;

	public User() {

	}

	public User(String lastName, String firstName, Integer age, Integer phoneNo, String email, String address,
			String gender, String username, String password, Collection<Role> roles) {
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
		this.roles = roles;
	}

	public User(String lastName, String firstName, Integer age, Integer phoneNo, String email, String address,
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

	public Integer getUserId() {
		return userId;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public Integer getAge() {
		return age;
	}

	public Integer getPhoneNo() {
		return phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getGender() {
		return gender;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public boolean hasRole(String roleName) {
		Iterator<Role> iterator = roles.iterator();

		while (iterator.hasNext()) {
			Role role = iterator.next();
			if (role.getRoleName().equals(roleName)) {
				return true;
			}
		}

		return false;
	}

}

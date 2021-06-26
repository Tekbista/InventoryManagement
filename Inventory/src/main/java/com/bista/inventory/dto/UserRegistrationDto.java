package com.bista.inventory.dto;


import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserRegistrationDto {

	@NotNull
	@NotEmpty(message = "first name cannot be blank")
	private String firstName;
	@NotNull
	@NotEmpty(message = "last name cannot be blank")
	private String lastName;
	@NotNull
	@NotEmpty(message = "email name cannot be blank")
	private String email;
	@NotNull
	@NotEmpty(message = "password name cannot be blank")
	private String password;

	public UserRegistrationDto() {
		super();
	}
	
	public UserRegistrationDto(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

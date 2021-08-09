package com.bista.inventory.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRegistrationDto {

	
	@NotBlank(message = "First name cannot be blank")
	@Size(min = 2, max = 50, message = "First name must be between 2-50 characters")
	private String firstName;
	@NotBlank(message = "Last name cannot be blank")
	@Size(min = 2, max = 50, message = "Last name must be between 2-50 characters")
	private String lastName;
	@NotBlank(message = "Email name cannot be blank")
	@Size(max = 100, message = "Email must be less than 100 characters")
	@Email(message = "Invalid email")
	private String email;
	@NotBlank(message = "Password name cannot be blank")
	@Size(min = 8, max = 16, message = "Password must be between 8-16 characters")
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

package com.bista.inventory.model;


import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@Entity
@Table(name="user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	// Variable declaration
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@NotBlank(message = "first name cannot be blank")
	private String firstName;
	
	@NotBlank(message = "last name cannot be blank")
	private String lastName;
	
	
	@NotBlank(message = "email name cannot be blank")
	private String email;
	
	@NotBlank(message = "password name cannot be blank")
	@Size(min = 8, max = 100)
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="users_roles", joinColumns = @JoinColumn(name="user_id", referencedColumnName = "id"),
									inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
	private Collection<Role> roles;
	
	// Constructor
	// Default constructor
	public User() {
		super();
	}

	// Parameterized constructor
	public User(String firstName, String lastName, String email, String password, Collection<Role> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	public Collection<Role> getRole() {
		return roles;
	}
	
	public void setRole(Collection<Role> roles) {
		this.roles = roles;
	}


}

package com.bista.inventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "item")
public class Item {
	// Variable declaration
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "item_name")
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 1, max = 100, message = "Name must be between 1-100 characters")
	private String itemName;
	@NotBlank(message = "Location cannot be blank.")
	@Size(min = 1, max = 200, message = "Location must be between 1-100 characters")
	private String location;
	@Min(0)
	private Long quantity;
	@NotBlank(message = "Description cannot be blank.")
	@Size(min=20, message = "Description must be minimum of 20 characters.")
	private String description;
	
	private String image;

	// Default constructor
	public Item() {

	}

	// Parameterized constructor
	public Item(String itemName, String location, Long quantity, String description, String image) {
		super();
		this.itemName = itemName;
		this.location = location;
		this.quantity = quantity;
		this.description = description;
		this.image = image;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}

}

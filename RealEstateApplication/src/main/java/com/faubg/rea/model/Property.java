package com.faubg.rea.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "property", catalog = "aubg")
public class Property {

	@NotNull
	@NotEmpty
	private String id;
	@NotNull
	@NotEmpty
	private String address;
	@NotNull
	@NotEmpty
	private String price;
	@NotNull
	@NotEmpty
	private String rental;
	@NotNull
	@NotEmpty
	private String area;
	@NotNull
	@NotEmpty
	private String description;

	public Property() {
	}

	public Property(String id, String address, String price, String rental,
			String area, String description) {
		this.id = id;
		this.address = address;
		this.price = price;
		this.rental = rental;
		this.area = area;
		this.description = description;
	}

	@Id
	@Column(name = "p_id", unique = true, nullable = false, length = 11)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "p_address", nullable = false, length = 100)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "p_price", nullable = false, length = 11)
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Column(name = "p_rental", nullable = false, length = 1)
	public String getFirstName() {
		return rental;
	}

	@Column(name = "p_rental", nullable = false, length = 1)
	public void setFirstName(String firstName) {
		rental = firstName;
	}

	public void setRental(String rental) {
		this.rental = rental;
	}

	@Column(name = "p_area", nullable = false, length = 11)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "p_description", nullable = false, length = 255)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

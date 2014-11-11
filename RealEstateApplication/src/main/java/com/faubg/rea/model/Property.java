package com.faubg.rea.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
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
	private boolean rental;
	@NotNull
	@NotEmpty
	private String area;
	@NotNull
	@NotEmpty
	private String description;
	
	private Set<Image> images = new HashSet<Image>(0);
	
	public Property() {
	}

	public Property(String id, String address, String price, boolean rental,
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

	@Column(name = "p_rental", nullable = false, length = 1, columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public boolean isRental() {
		return rental;
	}

	public void setRental(boolean isRental) {
		rental = isRental;
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "property")
	public Set<Image> getImages(){
		return this.images;
	}
	
	public void setImages(Set<Image> images){
		this.images = images;
	}

}

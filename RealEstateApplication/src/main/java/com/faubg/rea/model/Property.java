package com.faubg.rea.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "property", catalog = "aubg")
public class Property {

	@NotNull
	private Integer id;
	@NotNull
	@NotEmpty
	private String address;
	@NotNull
	private Integer price;
	@NotNull
	private Boolean rental;
	@NotNull
	@NotEmpty
	private String area;
	@NotNull
	@NotEmpty
	private String description;
	
	private Set<Room> rooms = new HashSet<Room>(0);
	private Set<Offer> offers = new HashSet<Offer>(0);
	private Set<Image> images = new HashSet<Image>(0);

	public Property() {
	}

	public Property(Integer id, String address, Integer price, Boolean rental,
			String area, String description) {
		this.id = id;
		this.address = address;
		this.price = price;
		this.rental = rental;
		this.area = area;
		this.description = description;
	}

	@Id
	@GenericGenerator(name = "kaugen", strategy = "increment")
	@GeneratedValue(generator = "kaugen")
	@Column(name = "p_id", unique = true, nullable = false, length = 11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "p_rental", nullable = false, length = 1, columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public Boolean getRental() {
		return this.rental;
	}

	@Column(name = "p_rental", nullable = false, length = 1, columnDefinition = "TINYINT")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	public Boolean isRental() {
		return rental;
	}

	public void setRental(Boolean isRental) {
		rental = isRental;
	}

	@Column(name = "p_area", nullable = false, length = 11)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "p_description", nullable = false, length = 1000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "property")
	public Set<Image> getImages() {
		return this.images;
	}
	
	public void setImages(Set<Image> images) {
		this.images = images;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "property")
	public Set<Room> getRooms() {
		return this.rooms;
	}

	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;

	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "property")
	public Set<Offer> getOffers() {
		return offers;
	}

	public void setOffers(Set<Offer> offers) {
		this.offers = offers;
	}

	@Override
	public String toString() {
		StringBuilder htmlBuilder = new StringBuilder();
		htmlBuilder.append("<input type='hidden' name='id' value='").append(id)
				.append("' /><h3>").append(address).append("</h3>");
		htmlBuilder.append("Description: <div class=\"comment more\">")
				.append(description).append("</div><br />");
		htmlBuilder.append("Area: ").append(area).append("<br />");
		if (rental) {
			htmlBuilder.append("Rental: Yes<br />");
		} else {
			htmlBuilder.append("Rental: No<br />");
		}
		htmlBuilder.append("Price: ").append(price).append("<br />");
		return htmlBuilder.toString();
	}

	public String toEditHTML() {
		StringBuilder htmlBuilder = new StringBuilder();
		htmlBuilder
				.append("<form action='updateProperty' method='post'><input type='hidden' name='id' value='")
				.append(id).append("' /><h1>").append(address).append("</h1>");
		htmlBuilder
				.append("<table><tr><td>Address:</td><td><input type='text' name='address' value='")
				.append(address).append("' plaeholder='Address'></td></tr>");
		htmlBuilder
				.append("<tr><td>Description: </td><td><textarea rows='4' cols='50' name='description'>")
				.append(description).append("</textarea></td></tr>");
		htmlBuilder
				.append("<tr><td>Area: </td><td><input type='text' name='area' value='")
				.append(area).append("' plaeholder='Area'></td></tr>");
		if (rental) {
			htmlBuilder
					.append("<tr><td>Rental: </td><td><input type='checkbox' name='rental' id='rental' checked='true'></td></tr>");
		} else {
			htmlBuilder
					.append("<tr><td>Rental: </td><td><input type='checkbox' name='rental' id='rental' checked='false'></td></tr>");
		}
		htmlBuilder
				.append("<tr><td>Price: </td><td><input type='text' name='price' value='")
				.append(price)
				.append("' plaeholder='Price'></td></tr></table><input type='submit' name='commit' value='Update'></form>");
		return htmlBuilder.toString();
	}

}

package com.faubg.rea.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "offer", catalog = "aubg")
public class Offer {
	private Integer id;
	private User user;
	private Property property;
	private Integer price;
	private Timestamp timestamp;
	private String status;

	public Offer() {
	}

	public Offer(Integer id, User user, Property property, Integer price,
			Timestamp timestamp, String status) {
		this.id = id;
		this.user = user;
		this.property = property;
		this.price = price;
		this.timestamp = timestamp;
		this.status = status;
	}

	@Id
	@GenericGenerator(name = "kaugen", strategy = "increment")
	@GeneratedValue(generator = "kaugen")
	@Column(name = "o_id", unique = true, nullable = false, length = 11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "u_username")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "p_id")
	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	@Column(name = "o_price")
	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "o_timestamp")
	public Timestamp getDate() {
		return timestamp;
	}

	public void setDate(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	
	@Column(name = "s_name")
	public String getStatus() {
		
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder htmlBuilder = new StringBuilder();
		htmlBuilder.append("<input type='hidden' name='id' value='").append(id)
				.append("' /><h3>").append(user.getUsername())
				.append(" offers on ").append(property.getAddress())
				.append("</h1>");
		htmlBuilder.append("Price: ")
				.append(price).append("<br />");
		htmlBuilder.append("Status: ").append(status).append("<br />");
		return htmlBuilder.toString();
	}
}

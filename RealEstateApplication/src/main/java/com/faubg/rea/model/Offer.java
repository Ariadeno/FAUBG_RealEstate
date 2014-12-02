package com.faubg.rea.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "room", catalog = "aubg")
public class Offer {
	private Integer id;
	private User user;
	private Property property;
	private Integer price;
	private Timestamp timestamp;
	
	public Offer() {
	}
	
	public Offer(Integer id, User user, Property property, Integer price, Timestamp timestamp) {
		this.id = id;
		this.user = user;
		this.property = property;
		this.price = price;
		this.timestamp = timestamp;
	}
	
	@Id
	@Column(name = "o_id")
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
	
	@ManyToOne(fetch = FetchType.LAZY)
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
}

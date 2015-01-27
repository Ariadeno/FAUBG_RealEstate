package com.faubg.rea.model;

import java.io.IOException;
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
import org.joda.money.Money;

import com.coinbase.api.Coinbase;
import com.coinbase.api.CoinbaseBuilder;
import com.coinbase.api.entity.Button;
import com.coinbase.api.entity.Order;
import com.coinbase.api.exception.CoinbaseException;

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
	
	public String bitCoinPaymentButton() throws CoinbaseException, IOException{
		
		Coinbase cb = new CoinbaseBuilder()
        .withApiKey(System.getenv("irbVf5kzgOpTxK5W"), System.getenv("nnO0M8V6UGSP9TK5J8bk9CrJrs2gmThU"))
        .build();
	
		Button buttonParams = new Button();
		buttonParams.setText("Pay with Bitcoin"); 
		buttonParams.setDescription("You are about to buy or rent the property on " + property.getAddress());
		buttonParams.setPrice(Money.parse("USD 1.23"));
		buttonParams.setName(property.getAddress());
		buttonParams.setSuccessUrl("localhost:8080/");
		
		Button button = cb.createButton(buttonParams);
		Order order = cb.createOrder(button);
		
		return button.getCode();
		
		
		
	}
}

package com.faubg.rea.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "registeredname", catalog = "aubg")
public class RegisteredName {
	
	@NotNull
	@NotEmpty
	private String username;

	public RegisteredName() {
	}

	public RegisteredName(String username) {
		this.username = username;
	}

	@Id
	@Column(name = "rn_username", unique = true, nullable = false, length = 255)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}


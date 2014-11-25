package com.faubg.rea.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "roomtype", catalog = "aubg")
public class RoomType {
	@NotNull
	private String name;

	public RoomType() {
	}

	public RoomType(String name) {
		this.name = name;
	}
	
	@Id
	@Column(name = "rt_name", nullable = false, length = 11)
	public String getName() {
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}

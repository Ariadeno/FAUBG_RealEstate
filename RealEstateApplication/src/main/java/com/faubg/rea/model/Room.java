package com.faubg.rea.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "room", catalog = "aubg")
public class Room {
	@NotNull
	private Property property;
	@NotNull
	private RoomType type;
	@NotNull
	private Integer id;
	@NotNull
	private Integer number;

	public Room() {
	}

	public Room(Integer id, Property property, RoomType type, Integer number) {
		this.id = id;
		this.property = property;
		this.type = type;
		this.number = number;
	}

	@Id
	@GenericGenerator(name = "kaugen", strategy = "increment")
	@GeneratedValue(generator = "kaugen")
	@Column(name = "r_id", unique = true, nullable = false, length = 11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_id", nullable = false)
	public Property getProperty() {
		return this.property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rt_name", nullable = false)
	public RoomType getType() {
		return type;
	}

	public void setType(RoomType type) {
		this.type = type;
	}

	@Column(name = "r_number", nullable = false, length = 11)
	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}

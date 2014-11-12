package com.faubg.rea.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "image", catalog = "aubg")
public class Image {
	@NotNull
	@NotEmpty
	private String location;
	@NotNull
	private Property property;
	
	public Image(){
	}
	
	public Image(String location, Property property){
		this.location = location;
		this.property = property;
	}
	
	@Id
	@Column(name = "i_location")
	public String getLocation(){
		return this.location;
	}
	
	public void setLocation(String location){
		this.location = location;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_id", nullable = false)
	public Property getProperty(){
		return this.property;
	}
	
	public void setProperty(Property property){
		this.property = property;
	}
	
}

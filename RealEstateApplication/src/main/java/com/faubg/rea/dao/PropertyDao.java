package com.faubg.rea.dao;
import java.util.List;

import com.faubg.rea.model.Property;

public interface PropertyDao {
	List<Property> findAllResaleProperties();
	List<Property> findAllRentalProperties();
	void addProperty(Property property);
	Property findPropertyByID(Integer id);
	void saveProperty(Property property);
	void deleteProperty(Property property);
	public List<Property> findAllOccupiedProperties();
}
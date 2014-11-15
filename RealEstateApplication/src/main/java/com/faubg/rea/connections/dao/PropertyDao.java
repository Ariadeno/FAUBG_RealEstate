package com.faubg.rea.connections.dao;
import java.util.List;

import com.faubg.rea.model.Property;

public interface PropertyDao {
	List<Property> findAllResaleProperties();
	List<Property> findAllRentalProperties();
	void addProperty(Property property);
	Property findProprtyByID(Integer id);
	void saveProperty(Property property);
}
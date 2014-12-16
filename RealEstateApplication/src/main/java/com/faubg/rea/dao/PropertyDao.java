package com.faubg.rea.dao;
import java.util.List;

import com.faubg.rea.model.Property;

public interface PropertyDao {
	List<Property> findAllResaleProperties();
	List<Property> findAllRentalProperties();
	List<Property> findAllRentalPropertiesWithinPriceRange(int x, int y);
	List<Property> findAllResalePropertiesWithinPriceRange(int x, int y);
	void addProperty(Property property);
	Property findPropertyByID(Integer id);
	void saveProperty(Property property);
	void deleteProperty(Property property);
	public List<Property> findAllOccupiedProperties();
}
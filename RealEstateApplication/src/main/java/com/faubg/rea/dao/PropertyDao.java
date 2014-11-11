package com.faubg.rea.dao;
import java.util.List;

import com.faubg.rea.model.Property;

public interface PropertyDao {
	List<Property> findAllResaleProperties();
	List<Property> findAllRentalProperties();
}
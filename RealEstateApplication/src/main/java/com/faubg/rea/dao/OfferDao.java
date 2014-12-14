package com.faubg.rea.dao;

import java.util.List;

import com.faubg.rea.model.Offer;

public interface OfferDao {
	List<Offer> findAllOfers();
	void addOffer(Offer offer);
	void saveOffer(Offer offer);
	Offer findOfferByID(Integer id);
	
}

package com.faubg.rea.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.faubg.rea.model.Offer;
@Repository
public class OfferDaoImpl implements OfferDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public void addOffer(Offer offer) {
		sessionFactory.getCurrentSession().persist(offer);
	}
	
	@Transactional
	@Override
	public void saveOffer(Offer offer) {
		sessionFactory.getCurrentSession().saveOrUpdate(offer);
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Offer> findAllOfers() {
		List<Offer> offers = new ArrayList<Offer>();
		offers = sessionFactory.getCurrentSession().createQuery("from Offer").list();
		return offers;
	}
}

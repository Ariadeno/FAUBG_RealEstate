package com.faubg.rea.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.faubg.rea.model.Offer;
import com.faubg.rea.model.Property;

@Repository
public class PropertyDaoImpl implements PropertyDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Property> findAllResaleProperties() {
		List<Property> properties = new ArrayList<Property>();

		properties = sessionFactory.getCurrentSession().createQuery("from Property where rental is false").list();
		return properties;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Property> findAllRentalProperties() {
		List<Property> properties = new ArrayList<Property>();
		properties = sessionFactory.getCurrentSession().createQuery("from Property where rental is true").list();
		return properties;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Property> findAllRentalPropertiesWithinPriceRange(int x, int y) {
		List<Property> properties = new ArrayList<Property>();
		properties = sessionFactory.getCurrentSession().createQuery("from Property where rental is true and price between :from and :to").setParameter("from", x).setParameter("to", y).list();
		return properties;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Property> findAllResalePropertiesWithinPriceRange(int x, int y) {
		List<Property> properties = new ArrayList<Property>();
		properties = sessionFactory.getCurrentSession().createQuery("from Property where rental is false and price between :from and :to").setParameter("from", x).setParameter("to", y).list();
		return properties;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Property> findAllOccupiedProperties() {
		List<Offer> acceptedOffers = new ArrayList<Offer>();
		List<Property> occupiedProperties = new ArrayList<Property>();
		acceptedOffers = sessionFactory.getCurrentSession().createQuery("from Offer where status = ?").setParameter(0, "Accepted").list();
		for(Offer offer : acceptedOffers){
			occupiedProperties.add(offer.getProperty());
		}
		return occupiedProperties;
	}

	@Transactional
	@Override
	public void addProperty(Property property) {
		sessionFactory.getCurrentSession().persist(property);
	}

	@Transactional
	@Override
	public void saveProperty(Property property) {
		sessionFactory.getCurrentSession().saveOrUpdate(property);
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
	public Property findPropertyByID(Integer id) {
		List<Property> properties = new ArrayList<Property>();
		properties = sessionFactory.getCurrentSession().createQuery("from Property where id is " + id).list();
		return properties.get(0);
	}

	@Transactional
	@Override
	public void deleteProperty(Property property) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(property);
	}
}
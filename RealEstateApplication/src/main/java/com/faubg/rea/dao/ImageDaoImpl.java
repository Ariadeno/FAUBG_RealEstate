package com.faubg.rea.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.faubg.rea.model.Image;

@Repository
public class ImageDaoImpl implements ImageDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public void addImage(Image image) {
		sessionFactory.getCurrentSession().persist(image);
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}

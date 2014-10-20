package com.faubg.rea.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.faubg.rea.model.RegisteredName;
import com.faubg.rea.model.User;

@Repository
public class RegisteredNameDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public RegisteredName findByUsername(String username) {
		List<RegisteredName> registeredNames = new ArrayList<RegisteredName>();

		registeredNames = sessionFactory.getCurrentSession()
				.createQuery("from RegisteredName where rn_username=?")
				.setParameter(0, username).list();

		if (registeredNames.size() > 0) {
			return registeredNames.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional
	public void addRegisteredName(RegisteredName registeredName) {
		sessionFactory.getCurrentSession().persist(registeredName);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}

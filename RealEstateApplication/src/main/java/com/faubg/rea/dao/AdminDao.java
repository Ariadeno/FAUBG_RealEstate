package com.faubg.rea.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.faubg.rea.model.Admin;
import com.faubg.rea.model.RegisteredName;
import com.faubg.rea.model.User;

@Repository
public class AdminDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Admin findByUsername(String username) {
		List<Admin> admins = new ArrayList<Admin>();

		admins = sessionFactory.getCurrentSession()
				.createQuery("from Admin where username=?")
				.setParameter(0, username).list();

		if (admins.size() > 0) {
			return admins.get(0);
		} else {
			return null;
		}
	}
	
	@Transactional
	public void addAdmin(Admin admin) {
		sessionFactory.getCurrentSession().persist(admin);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}

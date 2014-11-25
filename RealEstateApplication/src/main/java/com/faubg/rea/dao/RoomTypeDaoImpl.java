package com.faubg.rea.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.faubg.rea.model.RoomType;

@Repository
public class RoomTypeDaoImpl implements RoomTypeDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public void addRoomType(RoomType roomtype) {
		sessionFactory.getCurrentSession().persist(roomtype);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}

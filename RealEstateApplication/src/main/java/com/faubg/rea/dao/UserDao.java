package com.faubg.rea.dao;

import com.faubg.rea.model.User;

public interface UserDao {
	User findByUsername(String username);
	void addUser(User user);
}
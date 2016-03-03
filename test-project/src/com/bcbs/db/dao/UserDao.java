package com.bcbs.db.dao;

import java.util.List;

import com.bcbs.db.model.User;

public interface UserDao {
	
	public User getUserById(Integer id);
	public User getUserByName(String  name);
	public List<User> listUsers();
}

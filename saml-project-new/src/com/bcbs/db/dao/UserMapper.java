package com.bcbs.db.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bcbs.db.model.User;

public class UserMapper implements RowMapper<User> {
	   public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		   	  User user = new User();
		      user.setUserId(rs.getInt("user_id"));
		      user.setUserName(rs.getString("user_name"));		      
		      return user;
		   }

}

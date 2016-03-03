package com.bcbs.db.dao;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bcbs.db.model.User;

public class UserDaoImpl implements UserDao {
	static Logger s_logger = Logger.getLogger(UserDaoImpl.class);

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
		s_logger.debug("configured the database coneectivity..");
	}

	@Override
	//@Cacheable(value="users")
	public User getUserByName(String name) {
		// TODO Auto-generated method stub
		String SQL = "select * from user_table where user_name = ?";
		List<User> results = jdbcTemplateObject.query(SQL,
				new Object[] { name }, new UserMapper());
		if (results.size() > 0) {
			return results.get(0);
		}
		return null;

	}

	@Override	
	public List<User> listUsers() {
		// TODO Auto-generated method stub
		String SQL = "select * from User";
		List<User> users = jdbcTemplateObject.query(SQL, new UserMapper());
		s_logger.debug("user list : " + users);
		return users;
	}
	
	@Override	
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		s_logger.debug("select * from User where user_Id = ?" + id);
		String SQL = "select * from User where user_Id = ?";
		User user = jdbcTemplateObject.queryForObject(SQL, new Object[] { id },
				new UserMapper());
		s_logger.debug("user : " + user.toString());

		return user;
	}



}

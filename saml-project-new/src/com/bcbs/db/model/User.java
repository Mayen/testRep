package com.bcbs.db.model;

import java.io.Serializable;

public class User  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int userId;
	String userName;

	public User(int userId, String userName) {
		super();
		this.userId = userId;
		this.userName = userName;
	}
	public User(String userName) {
		super();		
		this.userName = userName;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + "]";
	}

}

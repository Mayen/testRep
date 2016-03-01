package com.bcbs.saml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.bcbs.db.model.User;

public class FileManager {
	static Logger s_logger = Logger.getLogger(FileManager.class);
	
	InputStream is;
	List<User> userList;
	String strUserList;

	public FileManager(InputStream is) {		
		super();
		this.is = is;
		strUserList = getStringFromInputStream(is);
	}

	public FileManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String getStringFromInputStream(InputStream is) {
		s_logger.debug("Start Reading user list file from the path ");
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {			
			String line;
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			s_logger.debug("Finished Reading user list file from the path: ");
		} catch (IOException e) {
			//e.printStackTrace();
			s_logger.error("IOException: "+ e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					//e.printStackTrace();
					s_logger.error("IOException: "+ e);
				}
			}
		}
		s_logger.debug("User list from the file: "+ sb.toString());
		return sb.toString();

	}	

	public List<User> getUserList() {
		
		s_logger.debug("Start adding [File] User list into the User object list");
		List<User> _userList = new ArrayList<User>();
		String _separator=",";
		
		if(!StringUtils.isEmpty(this.strUserList)){
			
			String[] u_names = this.strUserList.split(_separator);
			for (String u_name : u_names) {
				User u = new User(u_name);
				_userList.add(u);
			}
		}
		
		s_logger.debug("Finish adding [File] User list into the User object List");
		return _userList;
	}

}

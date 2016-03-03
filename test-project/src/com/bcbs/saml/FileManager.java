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
	String filePath;
	InputStream is;
	List<User> userList;
	String strUserList;

	public FileManager(InputStream is) {		
		super();
		this.is = is;
		//this.strUserList = getStringFromInputStream();
	}

	public FileManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void LoadDataFromInputStream(InputStream is) {
		 this.is=is;
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
		
		this.strUserList = sb.toString();
		

	}	

	public List<User> getUserList(InputStream is) {
		
		this.LoadDataFromInputStream(is);
		
		s_logger.debug("Start adding [File] User list into the User object list");
		List<User> _userList = new ArrayList<User>();
		String _separator=",";
		
		if(!StringUtils.isEmpty(this.strUserList)){
			
			String[] u_names = this.strUserList.split(_separator);
			int id=0;
			for (String u_name : u_names) {
				User u = new User(id++,u_name);			
				_userList.add(u);
				s_logger.debug("user : [ "+u+" ]" );
			}
		}
		
		s_logger.debug("Finish adding [File] User list into the User object List");
		return _userList;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public InputStream getIs() {
		return is;
	}

	public void setIs(InputStream is) {
		this.is = is;
	}

}

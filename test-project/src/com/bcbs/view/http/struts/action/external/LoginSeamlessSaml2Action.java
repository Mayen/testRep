package com.bcbs.view.http.struts.action.external;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bcbs.common.model.BcbsSamlProfileData;
import com.bcbs.db.dao.UserDao;
import com.bcbs.db.model.User;
import com.bcbs.saml.BcbsPortalSaml;
import com.bcbs.saml.FileManager;
import com.bcbs.saml.StringUtils;

public class LoginSeamlessSaml2Action extends Action {


	static Logger s_logger = Logger.getLogger(LoginSeamlessSaml2Action.class);

	private String forward;
	public static final String FORWARD_FAILURE = "ssoFailure";
	public static final String FORWARD_SUCCESS = "success";

	ApplicationContext applicationContext = null;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		s_logger.debug("Starting Execution Action [LoginSeamlessSaml2Action] /loginSeamless2.jspx");
		
		forward = FORWARD_SUCCESS;
		try {

			String loginSAML2Response = request.getParameter("SAMLResponse");
			s_logger.debug("Received SAML Response: " + loginSAML2Response);
			applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			BcbsSamlProfileData bcbsSamlProfileData=new BcbsSamlProfileData();
			BcbsPortalSaml bcbsPortalSaml = (BcbsPortalSaml) applicationContext.getBean("bcbsPortalSaml");
			if (!StringUtils.isEmpty(loginSAML2Response)) {
				
				
				 bcbsSamlProfileData = bcbsPortalSaml.processBCBSLoginResponseSAML2(loginSAML2Response);
				s_logger.debug("\n\n BCBS Profile Data: "+ bcbsSamlProfileData.toString());

				if (bcbsSamlProfileData.getUserId() == null
						|| bcbsSamlProfileData.getUserId().isEmpty()
						|| bcbsSamlProfileData.getSecretKey() == null
						|| bcbsSamlProfileData.getSecretKey().isEmpty()) {
					s_logger.error("SAML Attribute value (UserId , SecretKey) is null or empty");
					//forward = FORWARD_FAILURE;
				}
				if (!isValidUserFromFile(bcbsSamlProfileData.getUserId(), request)) {
					
					s_logger.error("SAML Attribute value (UserId): "+bcbsSamlProfileData.getUserId() +" is invalid!!");
					bcbsSamlProfileData.setUserId("");
					bcbsSamlProfileData.setSecretKey("");
					s_logger.debug("SAML Attributes Set (UserId and SecretKey)  NULL!");
					// forward = FORWARD_FAILURE;
				}
				
				bcbsSamlProfileData.setDestinationUrl(bcbsPortalSaml.samlProps.getSamlPostURL());
				request.setAttribute("bcbsSamlProfileData", bcbsSamlProfileData);
				request.setAttribute("SAMLURL",bcbsPortalSaml.samlProps.getSamlPostURL());

				s_logger.debug("SAML Attribute value UserId - "
						+ bcbsSamlProfileData.getUserId() + " , SecretKey- "
						+ bcbsSamlProfileData.getSecretKey() + ",  post URL - "
						+ bcbsSamlProfileData.getDestinationUrl());

			}
			else{
				s_logger.error("Error in processing SAML Response is null or empty!!");
				request.setAttribute("bcbsSamlProfileData", bcbsSamlProfileData);
				request.setAttribute("SAMLURL",bcbsPortalSaml.samlProps.getSamlPostURL());
			}

			forward = FORWARD_SUCCESS;

		} catch (Exception e) {
			// e.printStackTrace();
			s_logger.error("Error in processing SAML Response", e);
			// throw e;
		}
		s_logger.debug("Ending Execution Action [LoginSeamlessSaml2Action] /loginSeamless2.jspx");
		s_logger.debug("Forwarding action ------------>BOE/BI/custom.jsp " + forward);
		return mapping.findForward(forward);

		
	}

	private boolean isValidUserFromFile(String userName,
			HttpServletRequest request) {
		
		s_logger.debug("Starting Execution isValidUserFromFile() method For User validation..");
		try {
			 ServletContext servletContext=request.getSession().getServletContext(); 			
			 List<User>	userList = loadDataFromFile(servletContext);
			 if(userList == null){
				 s_logger.error("Authenticated users file processing error !!");
				 s_logger.debug("Authenticated userlist is null/empty,the current user will unable to logged in to the BO site!! ");
				 return false;
			 }
			 s_logger.debug("USERS : "+userList);
			 for(User u:userList){				 
				 if(u.getUserName().equals(userName)){    //user name is case sensitive
					 s_logger.debug("USER: "+u  +" is valid!!");
					 return true;
				 }				 
			 }			 
		} catch (Exception e) {
			s_logger.error("File processing error :", e);
			return false;
		}

		return false;

	}
	private List<User> loadDataFromFile(ServletContext context)
	{
		s_logger.debug("Load data from file  started");
		List<User> userList=null;
		try {
			
			ApplicationContext applicationContex =WebApplicationContextUtils.getWebApplicationContext(context);
			FileManager fileManager = (FileManager) applicationContex.getBean("fileManager");
			s_logger.debug("FileManager loaded!");
			String fileUrl = fileManager.getFilePath();
			s_logger.debug("Authenticated user list file path: " + fileUrl);
			//Load file form class path of application
			InputStream is = context.getResourceAsStream(fileUrl);			
			if (is == null) {
				//Load file form local path
				is = new FileInputStream(fileUrl);
				s_logger.debug("Loaded File path: " + fileUrl);
			} else {
				s_logger.debug("Loaded File path: " + fileUrl);
			}

			if (is != null) {
				    //fileManager.setIs(is); 			
					 userList = fileManager.getUserList(is);
					if (userList != null) {
						//context.setAttribute("USERLIST",userList);
						//s_logger.debug("User data loaded  the context attribute[USERLIST] and its ready to use!");
						s_logger.debug("USERLIST : "+ userList);

					} else {
						s_logger.error("USERLIST is null/empty!! ");
					}
			}
		} catch (Exception e) {

			s_logger.error("Error: " + e);

		}
		s_logger.debug("Load data from file  ended");
	
	return userList;
	}
	
	private boolean isValidUser(String userName, HttpServletRequest request) {
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		try {
			UserDao userDao = (UserDao) applicationContext.getBean("userDAO");
			User user = userDao.getUserByName(userName);

			if (user != null) {
				if (!StringUtils.isEmpty(user.getUserName())) {
					return true;
				}
			}
		} catch (Exception e) {
			s_logger.error("Database connection Error :", e);
			return false;
		}

		return false;

	}

}
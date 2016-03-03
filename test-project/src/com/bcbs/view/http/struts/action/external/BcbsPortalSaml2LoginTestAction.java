package com.bcbs.view.http.struts.action.external;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.bcbs.saml.StringUtils;
import com.bcbs.db.model.User;
public class BcbsPortalSaml2LoginTestAction extends Action {

	// private static Log s_logger =
	// LogFactory.getLog(BcbsPortalSaml2LoginAction.class);
	static Logger s_logger = Logger
			.getLogger(BcbsPortalSaml2LoginTestAction.class);

	private String forward;
	public static final String FORWARD_FAILURE = "ssoFailure";
	public static final String FORWARD_SUCCESS = "success";

	ApplicationContext applicationContext = null;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		s_logger.debug("Starting Execution Action [BcbsPortalSaml2LoginTestAction] /portalLoginTestSaml.jspx");
		forward = FORWARD_SUCCESS;
		try {
		
			String loginSAML2Response = request.getParameter("SAMLResponse");
			s_logger.debug("Received SAML Response: " + loginSAML2Response);
			applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
			BcbsSamlProfileData bcbsSamlProfileData=new BcbsSamlProfileData();
			BcbsPortalSaml bcbsPortalSaml = (BcbsPortalSaml) applicationContext.getBean("bcbsPortalSaml");
			if (!StringUtils.isEmpty(loginSAML2Response)) {
				
				/*applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
				BcbsPortalSaml bcbsPortalSaml = (BcbsPortalSaml) applicationContext.getBean("bcbsPortalSaml");*/
				 bcbsSamlProfileData = bcbsPortalSaml.processBCBSLoginResponseSAML2(loginSAML2Response);

				s_logger.debug("\n\n BCBS Profile Data: "+ bcbsSamlProfileData.toString());

				if (bcbsSamlProfileData.getUserId() == null
						|| bcbsSamlProfileData.getUserId().isEmpty()
						|| bcbsSamlProfileData.getSecretKey() == null
						|| bcbsSamlProfileData.getSecretKey().isEmpty()) {
					s_logger.error("Attribute value (UserId , SecretKey) is null or empty");
					// forward = FORWARD_FAILURE;
				}
				else if (!isValidUserFromFile(bcbsSamlProfileData.getUserId(),
						request)) {
					s_logger.error("SAML Attribute value (UserId) is invalid!");
					bcbsSamlProfileData.setUserId("");
					bcbsSamlProfileData.setSecretKey("");
					s_logger.debug("SAML Attributes set (UserId and SecretKey)  NULL!");
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
				request.setAttribute("bcbsSamlProfileData", bcbsSamlProfileData);
				request.setAttribute("SAMLURL",bcbsPortalSaml.samlProps.getSamlPostURL());
			}
			forward = FORWARD_SUCCESS;

		} catch (Exception e) {
			s_logger.error("Error in processing SAML Response", e);

		}

		s_logger.debug("Ending Execution Action [BcbsPortalSaml2LoginTestAction] /portalLoginTestSaml.jspx");
		s_logger.debug("Forwarding action ------------>BOE/BI/custom.jsp " + forward);

		return mapping.findForward(forward);

	}

	private boolean isValidUserFromFile(String userName,
			HttpServletRequest request) {
		s_logger.debug("Starting Execution isValidUserFromFile() For User validation..");
		try {
			 ServletContext servletContext=request.getSession().getServletContext(); 
			 List<User>	userList = (ArrayList<User>) servletContext.getAttribute("USERLIST");
			 
			 if(userList == null){
				 return false;
			 }
			 s_logger.debug("USER: "+userList);
			 for(User u:userList){
				
				 if(u.getUserName().equals(userName)){  				//user name is case sensitive
					 s_logger.debug("USER: "+u  +" is valid!");
					 return true;
				 }
				 
			 }
			 
		} catch (Exception e) {
			s_logger.error("File Error :", e);
			return false;
		}

		return false;

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
			s_logger.error("Database related error :", e);
			return false;
		}

		return false;

	}
}
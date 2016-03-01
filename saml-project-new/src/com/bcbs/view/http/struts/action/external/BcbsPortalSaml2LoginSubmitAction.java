package com.bcbs.view.http.struts.action.external;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bcbs.common.model.BcbsSamlProfileData;
import com.bcbs.db.dao.UserDao;
import com.bcbs.db.model.User;
import com.bcbs.saml.BcbsLoginProps;
import com.bcbs.saml.BcbsPortalSaml;
import com.bcbs.saml.grcode.InitializeSAMLData;
import com.bcbs.view.http.struts.form.LoginForm;

public class BcbsPortalSaml2LoginSubmitAction extends Action {

	public static final String BCBS_PARTNER_ID = "BCBS";
	// private static Log s_logger =
	// LogFactory.getLog(BcbsPortalSaml2LoginAction.class);
	static Logger s_logger = Logger.getLogger(BcbsPortalSaml2LoginAction.class);

	ApplicationContext applicationContext = null;

	private String forward;
	public static final String FORWARD_FAILURE = "ssoFailure";
	public static final String FORWARD_SUCCESS = "success";

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		forward = FORWARD_FAILURE;

		LoginForm loginForm = (com.bcbs.view.http.struts.form.LoginForm) form;

		BcbsSamlProfileData memberData = new BcbsSamlProfileData();
		memberData.setSecretKey(loginForm.getPassword());
		memberData.setUserId(loginForm.getUserName());

		setBcbsSaml(memberData, BCBS_PARTNER_ID, request);

		forward = "success";

		/*
		 * if (loginForm.getUserName().equals("admin")) { if
		 * (loginForm.getPassword().equals("admin123")) {
		 * loginForm.setMessage("Welcome User..."); forward = "success"; } else
		 * { loginForm.setMessage("Wrong Password"); forward = "failure"; } }
		 * else{ loginForm.setMessage("User Name is Not Correct"); }
		 */

		

		s_logger.debug(" Forwarding action ------------> " + forward);

		return mapping.findForward(forward);
	}

	protected void setBcbsSaml(BcbsSamlProfileData memberData,
			String partnerId, HttpServletRequest request) throws Exception {

		applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession()
						.getServletContext());
		BcbsLoginProps props = (BcbsLoginProps) applicationContext
				.getBean("bcbsMockLoginProps");
		// BcbsPortalProps props =
		// (BcbsPortalProps)SpringContext.getBean("bcbsPortalProps");
		InitializeSAMLData initializeSAMLData = new InitializeSAMLData();
		initializeSAMLData.initializeSAMLData(partnerId, props, memberData,
				request);

	}
}
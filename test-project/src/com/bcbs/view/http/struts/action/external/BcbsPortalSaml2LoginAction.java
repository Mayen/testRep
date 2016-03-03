package com.bcbs.view.http.struts.action.external;



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
import com.bcbs.saml.BcbsPortalSaml;
import com.bcbs.saml.StringUtils;






 
public class BcbsPortalSaml2LoginAction extends Action  {
 
	
   // private static Log s_logger = LogFactory.getLog(BcbsPortalSaml2LoginAction.class);
	static Logger s_logger = Logger.getLogger(BcbsPortalSaml2LoginAction.class);

    private String forward;
    public static final String FORWARD_FAILURE = "ssoFailure";
    public static final String FORWARD_SUCCESS = "success";
    
    ApplicationContext applicationContext = null;
    
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
   
        
    		forward = FORWARD_FAILURE;
		

		try {
			
			 
			
			//is client behind something?
			   String ipAddress = request.getHeader("X-FORWARDED-FOR");  
			   if (ipAddress == null) {  
				   ipAddress = request.getRemoteAddr();  
			   }
			s_logger.debug("Received request from : " + ipAddress);
			
		     

			forward  = FORWARD_SUCCESS;	
		
		} catch (Exception e) {
			e.printStackTrace();
			
			s_logger.error("Error in processing Request", e);
			throw e;
		}
        
		s_logger.debug(" Forwarding action ------------> " + forward);

		 return mapping.findForward(forward);
		 
		 	
		   
    }
}
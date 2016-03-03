package com.bcbs.view.http.web.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bcbs.db.model.User;
import com.bcbs.saml.BcbsPortalSaml;
import com.bcbs.saml.FileManager;

/**
 * Application Lifecycle Listener implementation class
 * WebAppServletContextListener
 *
 */
public class WebAppServletContextListener implements ServletContextListener {
	ServletContext context;
	static Logger s_logger = Logger
			.getLogger(WebAppServletContextListener.class);
	private static final String USER_CONF_FILE_ClASS_PATH = "/WEB-INF/classes/userlist.txt";
	private static final String USER_CONF_FILE_LOCAL_PATH = "f:/BOUSER/userlist.txt";

	/**
	 * Default constructor.
	 */
	public WebAppServletContextListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent contextEvent) {
		s_logger.debug("ServletContextListener started");
		try {
			context = contextEvent.getServletContext();
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
					List<User> userList = fileManager.getUserList(is);
					if (userList != null) {
						context.setAttribute("USERLIST",userList);
						s_logger.debug("User data loaded into the context attribute[USERLIST] and its ready to use!");
						s_logger.debug("USERLIST : "+ userList);

					} else {
						s_logger.error("USERLIST is null/empty!! ");
					}
			}
		} catch (Exception e) {

			s_logger.error("Error: " + e);

		}
		s_logger.debug("ServletContextListener Ended");
	}

}

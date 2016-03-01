package com.bcbs.view.http.web.config;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import com.bcbs.saml.FileManager;

/**
 * Application Lifecycle Listener implementation class
 * WebAppServletContextListener
 *
 */
public class WebAppServletContextListener implements ServletContextListener {
	ServletContext context;
	static Logger s_logger = Logger.getLogger(WebAppServletContextListener.class);
	private static final String USER_CONF_FILE = "/WEB-INF/classes/userlist.txt";

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
			InputStream is = context.getResourceAsStream(USER_CONF_FILE);
			s_logger.debug("File path: " + USER_CONF_FILE);
			if (is != null) {
				FileManager fileManager = new FileManager(is);

				if (fileManager != null)
					if (fileManager.getUserList() != null) {
						
						context.setAttribute("USERLIST",fileManager.getUserList());
						
						s_logger.debug("File data loaded into the context attribute[USERLIST] and its ready to use!" );
						s_logger.debug("USERLIST : "+fileManager.getUserList());
						
					} else {
						s_logger.debug("USERLIST is null ");
					}
			}
			else{
				s_logger.debug("File is null/empty ");
			}
		} catch (Exception e) {

			s_logger.error("Error: " + e);

		}
		s_logger.debug("ServletContextListener Ended");
	}

}

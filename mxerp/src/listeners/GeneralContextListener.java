package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import utils.UserTracker;

/**
 * Contextlistener especially for the offline usage as MES client
 * @author reich
 *
 */
public class GeneralContextListener implements ServletContextListener, HttpSessionListener {

	public void contextDestroyed(ServletContextEvent event) {	  

	}

	public void contextInitialized(ServletContextEvent sce) {

	}

  @Override
  public void sessionCreated(HttpSessionEvent event) {

  }

  @Override
  public void sessionDestroyed(HttpSessionEvent event) {
	  UserTracker.deleteUser(event.getSession().getId());
  }

}

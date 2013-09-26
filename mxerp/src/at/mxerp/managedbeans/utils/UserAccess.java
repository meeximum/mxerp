package at.mxerp.managedbeans.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.eclnt.jsfserver.util.HttpSessionAccess;
import org.eclnt.jsfserver.util.useraccess.IUserAccess;

public class UserAccess implements IUserAccess {
	
	public static final String USER_KEY = "username"; 
	@Override
	public String getCurrentUser(FacesContext context) {
		HttpSession session = HttpSessionAccess.getCurrentHttpSession(context);
		return (String)session.getAttribute(USER_KEY);
	}

}

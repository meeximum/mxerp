package managedbeans;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.events.BaseActionEventFlush;
import org.eclnt.jsfserver.managedbean.DefaultDispatchedBean;
import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.jsfserver.util.HttpSessionAccess;

import utils.Helper;
import utils.UserTracker;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.LogonUI}")
public class LogonUI extends DefaultDispatchedBean implements Serializable {

	protected String language = "de";

	public String getLanguage() { return language; }

	public void setLanguage(String value) { this.language = value; }


	protected String password;
	public String getPassword() { return password; }
	public void setPassword(String value) { password = value; }

	protected String user = "admin";
	public String getUser() { return user; }
	public void setUser(String value) { user = value; }

	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------

	public LogonUI(IDispatcher dispatcher) {
		super(dispatcher);
	}

	// ------------------------------------------------------------------------

	public void onLogon(ActionEvent event) {
		if(("admin".equals(user) && "admin".equals(password)) || "mxerp".equals(HttpSessionAccess.getCurrentClientId())) {
			// set server language
			FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
			// set user in session					
			UserTracker.addUser(user);
			IndexUI indexUI = (IndexUI)getOwningDispatcher().getDispatchedBean(IndexUI.class);
			indexUI.setPage("/main.jsp");
		} else {
			Statusbar.outputAlert(Helper.getMessage("err_logon")).setLeftTopReferenceCentered();
		}
	}

	public void onLanguage(ActionEvent event) {
		if (event instanceof BaseActionEventFlush) {
			Locale locale = new Locale(language);
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		    	HttpSessionAccess.reloadClient();

		}
	}
}

package at.mxerp.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.cayenne.ObjectContext;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.events.BaseActionEventFlush;
import org.eclnt.jsfserver.managedbean.DefaultDispatchedBean;
import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.jsfserver.util.HttpSessionAccess;

import at.mxerp.db.erp.Users;
import at.mxerp.utils.CayenneUtils;
import at.mxerp.utils.Helper;
import at.mxerp.utils.UserTracker;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.LogonUI}")
public class LogonUI extends DefaultDispatchedBean implements Serializable {

	private ObjectContext context = CayenneUtils.createNewContext();

	protected String language = "de";

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String value) {
		this.language = value;
	}

	protected String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String value) {
		password = value;
	}

	protected String user = "admin";

	public String getUser() {
		return user;
	}

	public void setUser(String value) {
		user = value;
	}

	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------

	public LogonUI(IDispatcher dispatcher) {
		super(dispatcher);
	}

	// ------------------------------------------------------------------------

	public void onLogon(ActionEvent event) {
		try {
			Users users = Users.getByUser(context, user);
			if (users == null) {
				Statusbar.outputAlert("Benutzername falsch!").setLeftTopReferenceCentered();
				return;
			}

			if (users.getActive() == false) {
				Statusbar.outputAlert("Benutzer nicht aktiv!").setLeftTopReferenceCentered();
				return;
			}

			if (users.checkPassword(password) == false) {
				Statusbar.outputAlert("Passwort falsch!").setLeftTopReferenceCentered();
				return;
			}

			users.setLastLogin(new Date());
			users.setLastLoginIp(Helper.getClientIP());
			context.commitChanges();
			// set server language
			FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
			// set user in session
			UserTracker.addUser(user);
			IndexUI indexUI = (IndexUI) getOwningDispatcher().getDispatchedBean(IndexUI.class);
			indexUI.setPage("/main.jsp");
		} catch (Exception ex) {
			Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
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

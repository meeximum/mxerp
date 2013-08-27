package managedbeans;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.faces.context.FacesContext;

import managedbeans.utils.Unsecure;
import managedbeans.utils.UserAccess;

import org.apache.cayenne.BaseContext;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.access.DataContext;
import org.apache.commons.lang3.StringUtils;
import org.eclnt.jsfserver.util.HttpSessionAccess;
import org.eclnt.jsfserver.util.useraccess.UserAccessMgr;
import org.eclnt.workplace.IWorkpageDispatcher;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@SuppressWarnings("serial")
public class WorkpageDispatchedPageBean extends	org.eclnt.workplace.WorkpageDispatchedPageBean {

	public WorkpageDispatchedPageBean(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		Annotation unsecure = this.getClass().getAnnotation(Unsecure.class);		
 		if(unsecure == null) checkAuthorization();
	}

	@Override
	public String getPageName() {
		throw new NotImplementedException();
	}

	@Override
	public String getRootExpressionUsedInPage() {
		throw new NotImplementedException();
	}
	
	private void checkAuthorization() {
 		// return if viewed in editor
 		if(HttpSessionAccess.checkIfInLayoutEditorPreview()) return;
 		// check user
 		String user = UserAccessMgr.getCurrentUser();
 		if(StringUtils.isEmpty(user) || UserAccess.USER_UNDEFINED.equals(user)) {			
 			try {
 				HttpSessionAccess.getCurrentResponse().sendError(403);
 			} catch (IOException e) { 
 				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
 			}
 		}
 	}

	protected IndexUI getIndexUI() {
		return (IndexUI) getOwningDispatcher().getDispatchedBean(IndexUI.class);
	}
	
	protected MainUI getMainUI() {
		return (MainUI) getOwningDispatcher().getDispatchedBean(MainUI.class);
	}
	
	protected ObjectContext getContext() {return BaseContext.getThreadObjectContext();}

	protected DataContext getDataContext() {return (DataContext)BaseContext.getThreadObjectContext(); }

}

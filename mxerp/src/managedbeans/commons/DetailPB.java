package managedbeans.commons;

import java.io.Serializable;

import managedbeans.WorkpageDispatchedPageBean;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.workplace.IWorkpageDispatcher;

import utils.Constants;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.DetailPB}")
public class DetailPB extends WorkpageDispatchedPageBean implements Serializable {

	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------

	private String entityName;
	private String entityId;
	
	public DetailPB(IWorkpageDispatcher workpageDispatcher) {
		super(workpageDispatcher);
		
		entityName = workpageDispatcher.getWorkpage().getParam(Constants.WP_PARAMS_ENTITY);
		entityId = workpageDispatcher.getWorkpage().getParam(Constants.WP_PARAMS_ENTITYID);
	}

	public String getPageName() {
		return "/ui/commons/detail.jsp";
	}

	public String getRootExpressionUsedInPage() {
		return "#{d.DetailPB}";
	}

	// ------------------------------------------------------------------------
	// public usage
	// ------------------------------------------------------------------------

	// ------------------------------------------------------------------------
	// private usage
	// ------------------------------------------------------------------------
}

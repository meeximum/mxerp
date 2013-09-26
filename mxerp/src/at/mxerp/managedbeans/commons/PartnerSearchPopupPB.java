package at.mxerp.managedbeans.commons;

import java.io.Serializable;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.pagebean.PageBean;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.PartnerSearchPopupPB}")
public class PartnerSearchPopupPB extends PageBean implements Serializable {

	private ObjectContext context;

	public PartnerSearchPopupPB(ObjectContext ctxt) {
		this.context = ctxt;
	}
	
	public PartnerSearchPopupPB(ObjectContext ctxt, Expression expression) {
		this.context = ctxt;
	}

	public String getPageName() {
		return "/ui/commons/partner-search-popup.jsp";
	}

	public String getRootExpressionUsedInPage() {
		return "#{d.PartnerSearchPopupPB}";
	}

}

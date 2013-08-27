package managedbeans.masterdata;

import java.io.Serializable;

import managedbeans.WorkpageDispatchedPageBean;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.workplace.IWorkpageDispatcher;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.ArticlesPB}")
public class ArticlesPB extends WorkpageDispatchedPageBean implements Serializable {

	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------

	public ArticlesPB(IWorkpageDispatcher workpageDispatcher) {
		super(workpageDispatcher);
	}

	public String getPageName() {
		return "/ui/master-data/articles.jsp";
	}

	public String getRootExpressionUsedInPage() {
		return "#{d.ArticlesPB}";
	}


}

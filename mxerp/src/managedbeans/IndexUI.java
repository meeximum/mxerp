package managedbeans;

import java.io.Serializable;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.managedbean.DefaultDispatchedBean;
import org.eclnt.jsfserver.managedbean.IDispatcher;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.IndexUI}")
public class IndexUI extends DefaultDispatchedBean implements Serializable {

	String page;
	public String getPage() { return page; }
	public void setPage(String page) {
		this.page = page;
	}

	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------
	public IndexUI(IDispatcher dispatcher) {
		super(dispatcher);

		page = "/logon.jsp";
	}


	// ------------------------------------------------------------------------
}

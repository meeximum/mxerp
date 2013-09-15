package managedbeans.commons;

import java.io.Serializable;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.pagebean.PageBean;

@CCGenClass(expressionBase = "#{d.PartnerGroupingPopupPB}")
public class PartnerGroupingPopupPB extends PageBean implements Serializable {
	private FIXGRIDListBinding<GridGroupingItem> gridGrouping = new FIXGRIDListBinding<GridGroupingItem>();

	public FIXGRIDListBinding<GridGroupingItem> getGridGrouping() {
		return gridGrouping;
	}

	public void setGridGrouping(FIXGRIDListBinding<GridGroupingItem> value) {
		this.gridGrouping = value;
	}

	public class GridGroupingItem extends FIXGRIDItem implements java.io.Serializable {
	}


	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------

	public PartnerGroupingPopupPB() {
	}

	public String getPageName() {
		return "/ui/commons/partner-grouping-popup.jsp";
	}

	public String getRootExpressionUsedInPage() {
		return "#{d.PartnerGroupingPopupPB}";
	}

	// ------------------------------------------------------------------------
	// public usage
	// ------------------------------------------------------------------------


	// ------------------------------------------------------------------------
	// private usage
	// ------------------------------------------------------------------------
}

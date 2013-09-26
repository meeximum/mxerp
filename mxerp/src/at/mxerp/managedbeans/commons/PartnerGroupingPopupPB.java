package at.mxerp.managedbeans.commons;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.cayenne.ObjectContext;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.BaseActionEvent;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.pagebean.PageBean;

import at.mxerp.db.erp.GroupingsV;
import at.mxerp.utils.Helper;

@SuppressWarnings("serial")
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
		private GroupingsV grouping;
		
		public GroupingsV getGrouping() {
			return grouping;
		}
				
		public GridGroupingItem(GroupingsV grouping) {
			super();
			this.grouping = grouping;
		}

		public void onSelect(ActionEvent event) {
			BaseActionEvent bae = (BaseActionEvent)event;
			String type = bae.getSourceConfiginfo();
			callback.onSelect(grouping.getId(), type);
		}
		
	}


	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------

	public PartnerGroupingPopupPB(ObjectContext context) {
		gridGrouping.getItems().clear();
		List<GroupingsV> groupings = GroupingsV.getActive(context, Helper.getLanguageServer());
		for(GroupingsV grouping : groupings) gridGrouping.getItems().add(new GridGroupingItem(grouping));		
	}

	public String getPageName() {
		return "/ui/commons/partner-grouping-popup.jsp";
	}

	public String getRootExpressionUsedInPage() {
		return "#{d.PartnerGroupingPopupPB}";
	}
	
	private ICallback callback;
	
	public void prepare(ICallback callback) {
		this.callback = callback;
	}
	
	public interface ICallback {
		public void onSelect(String grouping, String type);
	}
}

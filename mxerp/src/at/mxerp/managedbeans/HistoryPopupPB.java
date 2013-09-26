package at.mxerp.managedbeans;

import java.io.Serializable;
import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.map.ObjEntity;
import org.apache.commons.collections.CollectionUtils;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.pagebean.PageBean;

import at.mxerp.db.erp.AuditHeader;
import at.mxerp.db.erp.AuditPosition;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.HistoryPopupPB}")
public class HistoryPopupPB extends PageBean implements Serializable {
	private FIXGRIDListBinding<GridHistoryItem> gridHistory = new FIXGRIDListBinding<GridHistoryItem>();

	public FIXGRIDListBinding<GridHistoryItem> getGridHistory() {
		return gridHistory;
	}

	public void setGridHistory(FIXGRIDListBinding<GridHistoryItem> value) {
		this.gridHistory = value;
	}

	public class GridHistoryItem extends FIXGRIDItem implements java.io.Serializable {
		private AuditHeader header;
		
		private AuditPosition position;		
		
		public AuditHeader getHeader() {
			return header;
		}
		
		public AuditPosition getPosition() {
			return position;
		}

		public GridHistoryItem(AuditHeader header) {
			super();
			this.header = header;
		}

		public GridHistoryItem(AuditPosition position) {
			super();
			this.position = position;
			this.header = position.getAuditHeader();
		}
		
		
	}

	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------

	public HistoryPopupPB(ObjectContext ctxt, ObjEntity objEntity) {
		String tableName = objEntity.getDbEntityName();
		List<AuditHeader> headers = AuditHeader.getByTableName(ctxt, tableName);
		getGridHistory().getItems().clear();
		for (AuditHeader header : headers) {
			List<AuditPosition> positions = header.getAuditPositions();
			if(CollectionUtils.isEmpty(positions)) {
				getGridHistory().getItems().add(new GridHistoryItem(header));
			} else {
				for(AuditPosition position : positions) {
					getGridHistory().getItems().add(new GridHistoryItem(position));
				}
			}
		}
			
	}

	public String getPageName() {
		return "/ui/commons/history-popup.jsp";
	}

	public String getRootExpressionUsedInPage() {
		return "#{d.HistoryPopupPB}";
	}

}

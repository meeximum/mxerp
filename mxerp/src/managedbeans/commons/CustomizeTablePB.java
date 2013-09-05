package managedbeans.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import managedbeans.WorkpageDispatchedPageBean;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.map.ObjAttribute;
import org.apache.cayenne.map.ObjEntity;
import org.apache.cayenne.query.SelectQuery;
import org.apache.commons.lang3.StringUtils;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.componentnodes.CALENDARFIELDNode;
import org.eclnt.jsfserver.elements.componentnodes.CHECKBOXNode;
import org.eclnt.jsfserver.elements.componentnodes.COMBOBOXNode;
import org.eclnt.jsfserver.elements.componentnodes.FIELDNode;
import org.eclnt.jsfserver.elements.componentnodes.FIXGRIDNode;
import org.eclnt.jsfserver.elements.componentnodes.FORMATTEDFIELDNode;
import org.eclnt.jsfserver.elements.componentnodes.GRIDCOLNode;
import org.eclnt.jsfserver.elements.componentnodes.ICONNode;
import org.eclnt.jsfserver.elements.componentnodes.LABELNode;
import org.eclnt.jsfserver.elements.componentnodes.PANENode;
import org.eclnt.jsfserver.elements.componentnodes.ROWNode;
import org.eclnt.jsfserver.elements.componentnodes.SCROLLPANENode;
import org.eclnt.jsfserver.elements.componentnodes.TEXTPANENode;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding.ComponentNode;
import org.eclnt.workplace.IWorkpageDispatcher;

import utils.Constants;
import utils.Helper;
import db.erp.Metadata;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.CustomizeTablePB}")
public class CustomizeTablePB extends WorkpageDispatchedPageBean implements Serializable {
	public void onRollback(ActionEvent event) {
		try {
			getContext().rollbackChanges();
			Statusbar.outputSuccess(Helper.getMessage("data_reset_succ"));
		} catch (Exception ex) {
			Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
		}
		loadData();

	}

	public void onCommit(ActionEvent event) {
		try {
			getContext().commitChanges();
			Statusbar.outputSuccess(Helper.getMessage("data_write_succ"));
		} catch (Exception ex) {
			Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
		}
		loadData();
	}

	private ROWDYNAMICCONTENTBinding content = new ROWDYNAMICCONTENTBinding();

	public ROWDYNAMICCONTENTBinding getContent() {
		return content;
	}

	public void onRefresh(ActionEvent event) {
		loadData();
	}

	public void onAddRow(ActionEvent event) {
		// TODO: dialog for entering primary key!!
		CayenneDataObject newObject;
		try {
			newObject = getContext().newObject(tableClazz);
			GridTableItem item = new GridTableItem(newObject);
			getGridTable().getItems().add(item);
			getGridTable().deselectCurrentSelection();
			getGridTable().selectAndFocusItem(item);
		} catch (Exception ex) {
			Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
		}
	}

	FIXGRIDListBinding<GridTableItem> gridTable = new FIXGRIDListBinding<GridTableItem>();

	public FIXGRIDListBinding<GridTableItem> getGridTable() {
		return gridTable;
	}

	public void setGridTable(FIXGRIDListBinding<GridTableItem> value) {
		this.gridTable = value;
	}

	public class GridTableItem extends FIXGRIDItem implements java.io.Serializable {
		private CayenneDataObject data;

		public CayenneDataObject getData() {
			return data;
		}

		public GridTableItem(CayenneDataObject data) {
			super();
			this.data = data;
		}

		public void onDelete(ActionEvent event) {

			// Confirmation dialog
			YESNOPopup ynp = YESNOPopup.createInstance(Helper.getLiteral("delete"), Helper.getMessage("del_date"), new IYesNoCancelListener() {
				public void reactOnCancel() {
				}

				public void reactOnNo() {
				}

				public void reactOnYes() {
					try {
						getContext().deleteObjects(data);
						getContext().commitChanges();
						getGridTable().getItems().remove(this);
						Statusbar.outputSuccessWithPopup(Helper.getMessage("date_del_succ")).setLeftTopReferenceCentered();
					} catch (Exception ex) {
						Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
					}
					loadData();
				}
			});
			ynp.getModalPopup().setLeftTopReferenceCentered();

		}
	}

	private String table;

	private ObjEntity objEntity;

	private Map<String, Metadata> metadataMap;

	private Class<CayenneDataObject> tableClazz;

	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public CustomizeTablePB(IWorkpageDispatcher workpageDispatcher) {
		super(workpageDispatcher);

		try {
			table = getWorkpage().getParam(Constants.WP_PARAMS_TABLE);

			objEntity = getContext().getEntityResolver().getObjEntity(table);
			assert objEntity != null;

			tableClazz = (Class<CayenneDataObject>) Class.forName(objEntity.getClassName());
			assert tableClazz != null;

			metadataMap = Metadata.getByEntityAsMap(getContext(), table);

			generateGrid();
			loadData();
		} catch (Exception e) {
			logger.error(e, e);
			renderError(e);
		}
	}

	private ROWDYNAMICCONTENTBinding generateGrid() throws Exception {

		List<ComponentNode> components = new ArrayList<ComponentNode>(2);

		// fixgrid
		{
			FIXGRIDNode fixgrid = new FIXGRIDNode();
			fixgrid.setObjectbinding("#{d.CustomizeTablePB.gridTable}");
			fixgrid.setSelectorcolumn("1");
			fixgrid.setHeight("100%");
			fixgrid.setDrawoddevenrows(true);
			fixgrid.setSbvisibleamount("50");

			// delete button
			{
				GRIDCOLNode gridcol = new GRIDCOLNode();
				gridcol.setWidth(25);

				ICONNode iconNode = new ICONNode();
				iconNode.setActionListener(".{onDelete}");
				iconNode.setImage("/eclntjsfserver/images/cross.png");

				gridcol.addSubNode(iconNode);
				fixgrid.addSubNode(gridcol);
			}

			// primary key(s)
			{
				for (ObjAttribute objAttribute : objEntity.getPrimaryKeys()) {
					GRIDCOLNode gridcol = new GRIDCOLNode();
					gridcol.setText(Helper.getColumnNameForEntity(table, objAttribute.getDbAttributeName()));
					gridcol.setWidth(100);
					gridcol.setDynamicwidthsizing(true);

					ComponentNode value = createComponentNodeForResultColumn(objAttribute, false);
					gridcol.addSubNode(value);
					fixgrid.addSubNode(gridcol);
				}
			}

			// fields
			{
				for (ObjAttribute objAttribute : objEntity.getAttributes()) {
					if(objEntity.getPrimaryKeys().contains(objAttribute)) continue;
					String field = objAttribute.getName();
					Metadata metadate = metadataMap.get(field);
					if (metadate != null && metadate.getTechnical())
						continue;

					GRIDCOLNode gridcol = new GRIDCOLNode();
					gridcol.setText(Helper.getColumnNameForEntity(table, objAttribute.getDbAttributeName()));
					gridcol.setWidth(100);
					gridcol.setDynamicwidthsizing(true);

					ComponentNode value = createComponentNodeForResultColumn(objAttribute, true);
					gridcol.addSubNode(value);
					fixgrid.addSubNode(gridcol);
				}
			}

			components.add(fixgrid);
		}
		// export
		{
			ICONNode iconNode = new ICONNode();
			iconNode.setActionListener("#{d.CustomizeTablePB.gridTable.onOpenGridFunctions}");
			iconNode.setImage("/images/export.png");
			iconNode.setRowalignmenty("top");
			iconNode.setFocusable(false);
			components.add(iconNode);
		}

		content.setContentNodes(components);
		return content;
	}

	public void renderError(Exception e) {

		PANENode paneNode = new PANENode();
		paneNode.setHeight("100%");
		paneNode.setWidth("100%");

		{
			ROWNode rowNode = new ROWNode();

			LABELNode labelNode = new LABELNode();
			labelNode.setFont("size:20;weight:bold");
			labelNode.setForeground("#ff0000");
			labelNode.setText(e.toString() + " / " + e.getMessage());

			rowNode.addSubNode(labelNode);

			paneNode.addSubNode(rowNode);
		}

		{
			ROWNode rowNode = new ROWNode();

			SCROLLPANENode scrollpaneNode = new SCROLLPANENode();
			scrollpaneNode.setHeight("100%");
			scrollpaneNode.setWidth("100%");

			{
				ROWNode rowNode2 = new ROWNode();

				TEXTPANENode textpaneNode = new TEXTPANENode();
				textpaneNode.setContenttype("text/plain");
				textpaneNode.setHeight("100%");
				textpaneNode.setWidth("100%");
				textpaneNode.setText(Helper.getStackTraceAsString(e));

				rowNode2.addSubNode(textpaneNode);
				scrollpaneNode.addSubNode(rowNode2);

			}

			rowNode.addSubNode(scrollpaneNode);
			paneNode.addSubNode(rowNode);
		}

		content.setContentNode(paneNode);
	}

	@SuppressWarnings("unchecked")
	private void loadData() {
		getGridTable().getItems().clear();		
		SelectQuery<CayenneDataObject> query = SelectQuery.query(tableClazz);
		List<CayenneDataObject> objects = getContext().performQuery(query);
		for (CayenneDataObject object : objects) {
			getGridTable().getItems().add(new GridTableItem(object));
		}
	}

	public String getPageName() {
		return "/common/customize-table.jsp";
	}

	public String getRootExpressionUsedInPage() {
		return "#{d.CustomizeTablePB}";
	}

	// ------------------------------------------------------------------------
	private ComponentNode createComponentNodeForResultColumn(ObjAttribute objAttribute, boolean enabled) {
		String type = objAttribute.getType();
		String field = objAttribute.getName();

		ComponentNode value = null;

		if ("java.util.Date".equals(type)) {
			CALENDARFIELDNode calendarFieldNode = new CALENDARFIELDNode();
			calendarFieldNode.setValue(".{data." + objAttribute.getName() + "}");
			calendarFieldNode.setTimezone("CET");
			calendarFieldNode.setFormat("datetime");
			calendarFieldNode.setFormatmask("short");
			calendarFieldNode.setEnabled(enabled);
			value = calendarFieldNode;
		} else if ("java.lang.Boolean".equals(type)) {
			CHECKBOXNode checkbox = new CHECKBOXNode();
			checkbox.setSelected(".{data." + objAttribute.getName() + "}");
			checkbox.setAlign("center");
			checkbox.setEnabled(enabled);
			value = checkbox;
		} else if ("java.lang.Integer".equals(type)) {
			FORMATTEDFIELDNode formattedFieldNode = new FORMATTEDFIELDNode();
			formattedFieldNode.setValue(".{data." + objAttribute.getName() + "}");
			formattedFieldNode.setFormat("int");
			formattedFieldNode.setAlign("right");
			formattedFieldNode.setEnabled(enabled);
			value = formattedFieldNode;
		} else if ("java.lang.Double".equals(type)) {
			FORMATTEDFIELDNode formattedFieldNode = new FORMATTEDFIELDNode();
			formattedFieldNode.setValue(".{data." + objAttribute.getName() + "}");
			formattedFieldNode.setFormat("double");
			formattedFieldNode.setAlign("right");
			formattedFieldNode.setEnabled(enabled);
			value = formattedFieldNode;
		} else {
			Metadata metadate = metadataMap.get(field);
			if (metadate != null && StringUtils.isNotBlank(metadate.getVvb())) {
				COMBOBOXNode combobox = new COMBOBOXNode();
				combobox.setValidvaluesbinding("#{h.vvb." + metadate.getVvb() + "}");
				combobox.setValue(".{data." + objAttribute.getName() + "}");
				combobox.setEnabled(enabled);
				combobox.setWidth(150);
				value = combobox;
			} else {
				FIELDNode fieldNode = new FIELDNode();
				fieldNode.setText(".{data." + objAttribute.getName() + "}");
				fieldNode.setEnabled(enabled);
				value = fieldNode;
			}
		}
		return value;
	}
}

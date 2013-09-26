package at.mxerp.managedbeans.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.map.ObjAttribute;
import org.apache.cayenne.map.ObjEntity;
import org.apache.cayenne.query.SelectQuery;
import org.apache.commons.lang3.StringUtils;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.BasePopup.IPopupListener;
import org.eclnt.jsfserver.defaultscreens.ModalPopup;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.BaseActionEvent;
import org.eclnt.jsfserver.elements.componentnodes.CALENDARFIELDNode;
import org.eclnt.jsfserver.elements.componentnodes.CHECKBOXNode;
import org.eclnt.jsfserver.elements.componentnodes.COMBOBOXNode;
import org.eclnt.jsfserver.elements.componentnodes.FIELDNode;
import org.eclnt.jsfserver.elements.componentnodes.FIXGRIDNode;
import org.eclnt.jsfserver.elements.componentnodes.FORMATTEDFIELDNode;
import org.eclnt.jsfserver.elements.componentnodes.GRIDCOLNode;
import org.eclnt.jsfserver.elements.componentnodes.ICONNode;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding.ComponentNode;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.joor.Reflect;

import at.mxerp.db.erp.ICustomizing;
import at.mxerp.db.erp.Metadata;
import at.mxerp.managedbeans.HistoryPopupPB;
import at.mxerp.managedbeans.WorkpageDispatchedPageBean;
import at.mxerp.managedbeans.commons.TableKeyPopupPB.ICallback;
import at.mxerp.services.vvb.IVvb;
import at.mxerp.utils.CayenneUtils;
import at.mxerp.utils.Constants;
import at.mxerp.utils.Helper;

// TODO: import functionality
@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.CustomizeTablePB}")
public class CustomizeTablePB extends WorkpageDispatchedPageBean implements Serializable {
	private ObjectContext localContext;
	
	public void onHistory(ActionEvent event) {
		HistoryPopupPB popup = new HistoryPopupPB(localContext, objEntity);
		final ModalPopup mp = openModalPopup(popup, "Änderungshistorie", 0, 0, null);
		mp.setPopupListener(new IPopupListener() {
			
			@Override
			public void reactOnPopupClosedByUser() {
				mp.close();
				
			}
		});
		mp.setCloseonclickoutside(true);
		mp.setLeftTopReferenceCentered();
	}
	
	
	public void onRollback(ActionEvent event) {
		try {
			localContext.rollbackChanges();
			loadData();
			Statusbar.outputSuccess(Helper.getMessage("data_reset_succ"));
		} catch (Exception ex) {
			Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
		}
		

	}

	public void onCommit(ActionEvent event) {
		try {
			GridTableItem selectedItem = getGridTable().getSelectedItem();
			List<CayenneDataObject> newObjects = (List<CayenneDataObject>) localContext.newObjects();
			localContext.commitChanges();
			loadData();
			getGridTable().selectAndFocusItem(selectedItem);
			Statusbar.outputSuccess(Helper.getMessage("data_write_succ"));			
		} catch (Exception ex) {
			Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
		}
	}

	private ROWDYNAMICCONTENTBinding content = new ROWDYNAMICCONTENTBinding();

	public ROWDYNAMICCONTENTBinding getContent() {
		return content;
	}

	public void onRefresh(ActionEvent event) {
		loadData();
	}
	
	public void onAddRow(ActionEvent event) {
		String configInfo = ((BaseActionEvent) event).getSourceConfiginfo();

		try {
			if ("date".equals(configInfo)) {
				addDate();
			} else if ("text".equals(configInfo)) {
				if (getGridTable().getSelectedItem() == null)
					return;
				String id = getGridTable().getSelectedItem().getData().getId();
				addDateT(id);
			}

		} catch (Exception ex) {
			logger.error(ex, ex);
			Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
		}
	}
	

	private void addDate() throws Exception {
		final ICustomizing newObject = (ICustomizing) localContext.newObject(tableClazz);
		TableKeyPopupPB popup = new TableKeyPopupPB(newObject, objEntity);
		final ModalPopup mp = openModalPopup(popup, "Datensatz anlegen", 0, 0, null);

		popup.setCallback(new ICallback() {

			@Override
			public void ok(ICustomizing newObject) {
				mp.close();
				GridTableItem item = new GridTableItem(newObject);
				getGridTable().getItems().add(item);
				getGridTable().deselectCurrentSelection();
				getGridTable().selectAndFocusItem(item);
			}

			@Override
			public void cancel() {
				mp.close();
				localContext.deleteObjects(newObject);
			}
		});
		mp.setLeftTopReferenceCentered();
	}

	private void addDateT(String id) throws Exception {
		IVvb newObjectT = (IVvb) localContext.newObject(tableTClazz);
		newObjectT.setId(id);
		GridTableTItem item = new GridTableTItem(newObjectT);
		getGridTableT().getItems().add(item);
		getGridTableT().deselectCurrentSelection();
		getGridTableT().selectAndFocusItem(item);
	}

	FIXGRIDListBinding<GridTableItem> gridTable = new FIXGRIDListBinding<GridTableItem>();

	public FIXGRIDListBinding<GridTableItem> getGridTable() {
		return gridTable;
	}

	public void setGridTable(FIXGRIDListBinding<GridTableItem> value) {
		this.gridTable = value;
	}

	public class GridTableItem extends FIXGRIDItem implements java.io.Serializable {
		private ICustomizing data;

		@Override
		public void onRowSelect() {
			if(hasTexts) loadDataT(data.getId());
		}

		public ICustomizing getData() {
			return data;
		}
		
		public GridTableItem(ICustomizing data) {
			super();
			this.data = data;
		}
		
		public void onCopy(ActionEvent event) {
			try {
				
				final ICustomizing newObject = (ICustomizing) localContext.newObject(tableClazz);
				for(ObjAttribute attribute : objEntity.getAttributes()) {
					String attrName = StringUtils.capitalize(attribute.getName());
					try {
						Reflect.on(newObject).call("set" + attrName, Reflect.on(data).call("get" + attrName).get());
					} catch (Exception e) {
					}
				}
				
				TableKeyPopupPB popup = new TableKeyPopupPB(newObject, objEntity);
				final ModalPopup mp = openModalPopup(popup, "Datensatz kopieren", 0, 0, null);
				
				popup.setCallback(new ICallback() {

					@Override
					public void ok(ICustomizing newObject) {
						mp.close();
					
						GridTableItem item = new GridTableItem(newObject);						
						getGridTable().getItems().add(item);
						getGridTable().deselectCurrentSelection();
						getGridTable().selectAndFocusItem(item);
					}

					@Override
					public void cancel() {
						mp.close();
						localContext.deleteObjects(newObject);
					}
				});
				mp.setLeftTopReferenceCentered();
				
				
			} catch (Exception ex) {
				logger.error(ex,ex);
				Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
			}
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
						localContext.deleteObjects(data);
						localContext.commitChanges();
						getGridTable().getItems().remove(this);
						Statusbar.outputSuccessWithPopup(Helper.getMessage("date_del_succ")).setLeftTopReferenceCentered();
					} catch (Exception ex) {
						logger.error(ex,ex);
						Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
					}
					loadData();
				}
			});
			ynp.getModalPopup().setLeftTopReferenceCentered();

		}
	}

	FIXGRIDListBinding<GridTableTItem> gridTableT = new FIXGRIDListBinding<GridTableTItem>();

	public FIXGRIDListBinding<GridTableTItem> getGridTableT() {
		return gridTableT;
	}

	public void setGridTableT(FIXGRIDListBinding<GridTableTItem> gridTableT) {
		this.gridTableT = gridTableT;
	}

	public class GridTableTItem extends FIXGRIDItem implements java.io.Serializable {
		private IVvb vvb;

		public IVvb getVvb() {
			return vvb;
		}

		public GridTableTItem(IVvb vvb) {
			super();
			this.vvb = vvb;
		}
	}

	private String object;

	private ObjEntity objEntity;

	private ObjEntity objEntityT;

	private Map<String, Metadata> metadataMap;

	private Class<CayenneDataObject> tableClazz;
	
	private Class<CayenneDataObject> tableTClazz;

	private boolean hasTexts = false;
	
	public boolean getRenderTexts() {
		return hasTexts && gridTable.getSelectedItem()!=null;
	}
	
	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public CustomizeTablePB(IWorkpageDispatcher workpageDispatcher) {
		super(workpageDispatcher);

		try {
			localContext = CayenneUtils.createNewContext();
			
			object = getWorkpage().getParam(Constants.WP_PARAMS_OBJECT);

			objEntity = localContext.getEntityResolver().getObjEntity(object);
			assert objEntity != null;

			tableClazz = (Class<CayenneDataObject>) Class.forName(objEntity.getClassName());
			assert tableClazz != null;
			
			if(!ICustomizing.class.isAssignableFrom(tableClazz)) throw new Exception("object must implement interface ICustomizing");

			objEntityT = localContext.getEntityResolver().getObjEntity(object + "T");
			if (objEntityT != null) {
				tableTClazz = (Class<CayenneDataObject>) Class.forName(objEntityT.getClassName());
				hasTexts = true;
			}				

			metadataMap = Metadata.getByObjectAsMap(localContext, object);

			generateTableGrid();
			loadData();
		} catch (Exception ex) {
			logger.error(Helper.getStackTraceAsString(ex), ex);
			renderError(ex, content);
		}
	}

	private ROWDYNAMICCONTENTBinding generateTableGrid() throws Exception {

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
				iconNode.setEnabled(".{data.locked==false}");

				gridcol.addSubNode(iconNode);
				fixgrid.addSubNode(gridcol);
			}

			// copy button
			{
				GRIDCOLNode gridcol = new GRIDCOLNode();
				gridcol.setWidth(25);

				ICONNode iconNode = new ICONNode();
				iconNode.setActionListener(".{onCopy}");
				iconNode.setImage("/images/copy_document_16_16.png");

				gridcol.addSubNode(iconNode);
				fixgrid.addSubNode(gridcol);
			}

			// primary key(s)
			{
				for (ObjAttribute objAttribute : objEntity.getPrimaryKeys()) {
					GRIDCOLNode gridcol = new GRIDCOLNode();
					gridcol.setText(Helper.getColumnNameForObject(object, objAttribute.getDbAttributeName()));
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
					if (objEntity.getPrimaryKeys().contains(objAttribute))
						continue;
					String field = objAttribute.getName();
					if("locked".equals(field)) continue;
					Metadata metadate = metadataMap.get(field);
					if (metadate != null && metadate.getTechnical())
						continue;

					GRIDCOLNode gridcol = new GRIDCOLNode();
					gridcol.setText(Helper.getColumnNameForObject(object, objAttribute.getDbAttributeName()));
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

	@SuppressWarnings("unchecked")
	private void loadData() {
		getGridTable().getItems().clear();
		SelectQuery<CayenneDataObject> query = SelectQuery.query(tableClazz);
		List<CayenneDataObject> objects = localContext.performQuery(query);
		for (CayenneDataObject object : objects) {
			getGridTable().getItems().add(new GridTableItem((ICustomizing)object));
		}
	}

	@SuppressWarnings("unchecked")
	private void loadDataT(String id) {
		getGridTableT().getItems().clear();
		Expression expression = IVvb.ID.eq(id);
		SelectQuery<CayenneDataObject> query = SelectQuery.query(tableTClazz, expression);

		List<IVvb> vvbList = localContext.performQuery(query);

		for (IVvb vvb : vvbList) {
			getGridTableT().getItems().add(new GridTableTItem(vvb));
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
			value = createComponentNodeForMetadate(objAttribute.getName(), enabled, metadate);
		}
		return value;
	}
	
	public ComponentNode createComponentNodeForMetadate(String name, boolean enabled, Metadata metadate) {
		if(metadate!=null) {
			boolean isVvb = StringUtils.isNotBlank(metadate.getVvb());
			boolean isDom = StringUtils.isNotBlank(metadate.getDomain());

			String validValuesbinding = null;

			if (isVvb) {
				validValuesbinding = "#{h.vvb." + metadate.getVvb() + "}";
			} else if (isDom) {
				validValuesbinding = "#{h.dom." + metadate.getDomain() + "}";
			}

			if (validValuesbinding != null) {
				COMBOBOXNode combobox = new COMBOBOXNode();
				combobox.setValidvaluesbinding(validValuesbinding);
				combobox.setValue(".{data." + name + "}");
				combobox.setEnabled(enabled);
				combobox.setWidth(150);
				return combobox;
			}
		}

		FIELDNode fieldNode = new FIELDNode();
		fieldNode.setText(".{data." + name + "}");
		fieldNode.setEnabled(enabled);
		return fieldNode;
	}
}

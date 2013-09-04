package managedbeans.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.faces.event.ActionEvent;

import managedbeans.WorkpageDispatchedPageBean;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.map.ObjAttribute;
import org.apache.cayenne.map.ObjEntity;
import org.apache.cayenne.query.SelectQuery;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang3.StringUtils;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.BaseComponent;
import org.eclnt.jsfserver.elements.componentnodes.CALENDARFIELDNode;
import org.eclnt.jsfserver.elements.componentnodes.CHECKBOXNode;
import org.eclnt.jsfserver.elements.componentnodes.COLDISTANCENode;
import org.eclnt.jsfserver.elements.componentnodes.COLSYNCHEDPANENode;
import org.eclnt.jsfserver.elements.componentnodes.COLSYNCHEDROWNode;
import org.eclnt.jsfserver.elements.componentnodes.COMBOBOXNode;
import org.eclnt.jsfserver.elements.componentnodes.FIELDNode;
import org.eclnt.jsfserver.elements.componentnodes.FIXGRIDNode;
import org.eclnt.jsfserver.elements.componentnodes.FORMATTEDFIELDNode;
import org.eclnt.jsfserver.elements.componentnodes.GRIDCOLNode;
import org.eclnt.jsfserver.elements.componentnodes.ICONNode;
import org.eclnt.jsfserver.elements.componentnodes.LABELNode;
import org.eclnt.jsfserver.elements.impl.COLSYNCHEDROWComponent;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding.ComponentNode;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.jsfserver.session.RequestFocusManager;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.joor.Reflect;

import services.variants.IVariants;
import services.variants.Variant;
import services.variants.Variant.GridValues;
import utils.Constants;
import utils.Helper;
import db.erp.Metadata;
//TODO: dynamic handling of operators
//TODO: metadata for ignoring fields and referencing to dropdowns

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.SearchPB}")
public class SearchPB extends WorkpageDispatchedPageBean implements Serializable, IVariants {

	private Map<String, Metadata> metadataMap;

	private enum Operator {
		eq, like, gt, gte, lt, lte, between;

		public String getDescription() {
			return Helper.getLiteral(this.name());
		}

	}

	private String entityName;

	private String savedSearchName;

	public String getSavedSearchName() {
		return savedSearchName;
	}

	public void setSavedSearchName(String value) {
		this.savedSearchName = value;
	}

	private int fetchLimit = 100;

	public int getFetchLimit() {
		return fetchLimit;
	}

	public void setFetchLimit(int value) {
		this.fetchLimit = value;
	}

	private FIXGRIDListBinding<GridResultItem> gridResult = new FIXGRIDListBinding<GridResultItem>();

	public FIXGRIDListBinding<GridResultItem> getGridResult() {
		return gridResult;
	}

	public void setGridResult(FIXGRIDListBinding<GridResultItem> value) {
		this.gridResult = value;
	}

	public class GridResultItem extends FIXGRIDItem implements java.io.Serializable {
		CayenneDataObject data;

		public CayenneDataObject getData() {
			return data;
		}

		public GridResultItem(CayenneDataObject data) {
			super();
			this.data = data;
		}

	}

	private ValidValuesBinding fieldsVVB;

	private ValidValuesBinding opsVVB = new ValidValuesBinding() {
		{
			for (Operator operator : Operator.values()) {
				addValidValue(operator.name(), operator.getDescription());
			}
		}
	};

	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	public SearchPB(IWorkpageDispatcher workpageDispatcher) throws Exception {
		super(workpageDispatcher);
		entityName = workpageDispatcher.getWorkpage().getParam(Constants.WP_PARAMS_ENTITY);

		objEntity = getContext().getEntityResolver().getObjEntity(entityName);
		assert objEntity != null;

		metadataMap = Metadata.getByEntityAsMap(getContext(), entityName);
		
		fieldsVVB = new ValidValuesBinding();
		for (ObjAttribute objAttribute : objEntity.getAttributes()) {
			String field = objAttribute.getName();
			Metadata metadate = metadataMap.get(field);
			if (metadate != null && metadate.getTechnical())
				continue;
			String description = Helper.getColumnNameForEntity(entityName, objAttribute.getDbAttributeName());
			fieldsVVB.addValidValue(field, description);
		}
		entityClazz = (Class<CayenneDataObject>) Class.forName(objEntity.getClassName());
		assert entityClazz != null;

		selectionRowObjects.clear();
		addSelectionRow(0);

		getGridResult().getItems().clear();

		loadUsersOrGlobalDefaultVariant();
	}

	public String getPageName() {
		return "/ui/commons/search.jsp";
	}

	public String getRootExpressionUsedInPage() {
		return "#{d.SearchPB}";
	}

	// ------------------------------------------------------------------------
	// public usage
	// ------------------------------------------------------------------------
	public void onChangeSelection(ActionEvent event) {
		try {
			BaseComponent base = (BaseComponent) event.getSource();
			COLSYNCHEDROWComponent parent = (COLSYNCHEDROWComponent) base.getParent();
			String id = parent.getAttributeValueAsString("configinfo");
			SelectionRowObject selectionRowObject = getSelectionRowObjects().get(id);
			selectionRowObject.recreateSelectionRow();
		} catch (Exception ex) {
			logger.error(ex);
			Statusbar.outputAlert(ex.toString() + " / " + ex.getMessage(), "ERROR", Helper.getStackTraceAsString(ex)).setLeftTopReferenceCentered();
		}
	}

	public void onAddSearchField(ActionEvent event) {
		try {
			BaseComponent base = (BaseComponent) event.getSource();
			String field = base.getAttributeValueAsString("configinfo");
			addSelectionRowForSpecificField(field);
		} catch (Exception ex) {
			logger.error(ex);
			Statusbar.outputAlert(ex.toString() + " / " + ex.getMessage(), "ERROR", Helper.getStackTraceAsString(ex)).setLeftTopReferenceCentered();
		}
	}

	public void onReset(ActionEvent event) {
		try {
			init();
		} catch (Exception ex) {
			logger.error(ex);
			Statusbar.outputAlert(ex.toString() + " / " + ex.getMessage(), "ERROR", Helper.getStackTraceAsString(ex)).setLeftTopReferenceCentered();
		}
	}

	public void onSaveSearch(ActionEvent event) {
	}

	public void onInit(ActionEvent event) throws Exception {
		init();
	}

	public void onSearch(ActionEvent event) throws Exception {
		getGridResult().getItems().clear();
		List<CayenneDataObject> result = select();

		for (CayenneDataObject row : result) {
			getGridResult().getItems().add(new GridResultItem(row));
		}

		Statusbar.outputMessage(String.format("Es wurden %s Datensätze gefunden", result.size()));
	}

	public void onAddSelectionRow(ActionEvent event) {
		try {
			BaseComponent base = (BaseComponent) event.getSource();
			COLSYNCHEDROWComponent parent = (COLSYNCHEDROWComponent) base.getParent();
			String id = parent.getAttributeValueAsString("configinfo");
			duplicateSelectionRow(id);
		} catch (Exception ex) {
			logger.error(ex);
			Statusbar.outputAlert(ex.toString() + " / " + ex.getMessage(), "ERROR", Helper.getStackTraceAsString(ex)).setLeftTopReferenceCentered();
		}
	}

	public void onRemoveSelectionRow(ActionEvent event) {
		BaseComponent base = (BaseComponent) event.getSource();
		COLSYNCHEDROWComponent parent = (COLSYNCHEDROWComponent) base.getParent();
		String id = parent.getAttributeValueAsString("configinfo");
		removeSelectionRow(id);
	}

	public ValidValuesBinding getFieldsVVB() {
		return fieldsVVB;
	}

	public ValidValuesBinding getOpsVVB() {
		return opsVVB;
	}

	public ROWDYNAMICCONTENTBinding getResultGrid() throws Exception {
		return generateResultGrid();
	}

	public ROWDYNAMICCONTENTBinding getSelectionPanel() throws Exception {
		return generateSelectionPanel();
	}

	// ------------------------------------------------------------------------
	// private usage
	// ------------------------------------------------------------------------
	private ObjEntity objEntity;
	private Class<CayenneDataObject> entityClazz;

	@SuppressWarnings("unchecked")
	private void init() throws Exception {
		objEntity = getContext().getEntityResolver().getObjEntity(entityName);
		assert objEntity != null;
		fieldsVVB = new ValidValuesBinding();
		for (ObjAttribute objAttribute : objEntity.getAttributes()) {
			String description = Helper.getColumnNameForEntity(entityName, objAttribute.getDbAttributeName());
			fieldsVVB.addValidValue(objAttribute.getName(), description);
		}
		entityClazz = (Class<CayenneDataObject>) Class.forName(objEntity.getClassName());
		assert entityClazz != null;

		selectionRowObjects.clear();
		addSelectionRow(0);

		getGridResult().getItems().clear();
	}

	private void addSelectionRow(int index) throws Exception {
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		selectionRowObjects.put(index, id, new SelectionRowObject(id));
	}

	private void addSelectionRowForSpecificField(String field) throws Exception {
		String id = UUID.randomUUID().toString().replaceAll("-", "");	
		SelectionRowObject newSelectionRowObject = new SelectionRowObject(id, field, Operator.eq);
		newSelectionRowObject.focus();
		selectionRowObjects.put(0, id, newSelectionRowObject);
	}

	private void duplicateSelectionRow(String idToCopy) throws Exception {
		SelectionRowObject selectionRowObjectToCopy = getSelectionRowObjects().get(idToCopy);
		int index = selectionRowObjects.indexOf(idToCopy);
		String newId = UUID.randomUUID().toString().replaceAll("-", "");
		SelectionRowObject newSelectionRowObject = new SelectionRowObject(newId, selectionRowObjectToCopy.getField(), selectionRowObjectToCopy.getOperator());
		newSelectionRowObject.focus();
		selectionRowObjects.put(index + 1, newId, newSelectionRowObject);
	}

	private void removeSelectionRow(String id) {
		if (getSelectionRowObjects().size() > 1)
			getSelectionRowObjects().remove(id);
	}

	private List<CayenneDataObject> select() {
		Map<String, List<Expression>> expressionsMap = new HashMap<String, List<Expression>>();
		for (SelectionRowObject selectionRowObject : getSelectionRowObjects().values()) {
			if (selectionRowObject.valueLow == null)
				continue;
			String key = selectionRowObject.getField();
			if (StringUtils.isBlank(key))
				continue;
			if (expressionsMap.containsKey(key) == false) {
				expressionsMap.put(key, new ArrayList<Expression>());
			}
			expressionsMap.get(key).add(selectionRowObject.buildExpression());

		}

		List<Expression> expressions = new ArrayList<Expression>(expressionsMap.size());
		for (String key : expressionsMap.keySet()) {
			Expression expression = ExpressionFactory.joinExp(Expression.OR, expressionsMap.get(key));
			expressions.add(expression);
		}

		Expression expression = ExpressionFactory.joinExp(Expression.AND, expressions);

		SelectQuery<CayenneDataObject> query = SelectQuery.query(entityClazz, expression);
		query.setFetchLimit(fetchLimit);
		return getContext().select(query);
	}

	private COLSYNCHEDROWNode createSelectionRow(final SelectionRowObject selectionRowObject) throws Exception {
		String id = selectionRowObject.getId();
		String type = selectionRowObject.getTypeOfField();
		String field = selectionRowObject.getField();
		Operator operator = selectionRowObject.getOperator();

		COLSYNCHEDROWNode colSynchedRow = new COLSYNCHEDROWNode();
		colSynchedRow.setConfiginfo(id);
		// field
		{
			COMBOBOXNode combobox = new COMBOBOXNode();
			combobox.setValidvaluesbinding("#{d.SearchPB.fieldsVVB}");
			combobox.setValue("#{d.SearchPB.selectionRowObjects." + id + ".field}");
			combobox.setFlush(true);
			combobox.setActionListener("#{d.SearchPB.onChangeSelection}");
			combobox.setWidth(150);
			colSynchedRow.addSubNode(combobox);
		}

		// operator
		{
			COMBOBOXNode combobox = new COMBOBOXNode();
			combobox.setValidvaluesbinding("#{d.SearchPB.opsVVB}");
			combobox.setValue("#{d.SearchPB.selectionRowObjects." + id + ".operatorAsString}");
			combobox.setFlush(true);
			combobox.setActionListener("#{d.SearchPB.onChangeSelection}");
			combobox.setWidth(100);
			colSynchedRow.addSubNode(combobox);
		}

		// valueLow
		{
			colSynchedRow.addSubNode(createComponentNodeForSelectionRow(id, field, type, "valueLow"));
		}

		// valueHigh
		if (operator == Operator.between) {
			colSynchedRow.addSubNode(createComponentNodeForSelectionRow(id, field, type, "valueHigh"));
		} else {
			COLDISTANCENode coldistance = new COLDISTANCENode();
			coldistance.setWidth(0);
			colSynchedRow.addSubNode(coldistance);
		}

		// plus
		{
			ICONNode icon = new ICONNode();
			icon.setImage("/images/search_plus.gif");
			icon.setActionListener("#{d.SearchPB.onAddSelectionRow}");
			colSynchedRow.addSubNode(icon);
		}

		// minus
		{
			ICONNode icon = new ICONNode();
			icon.setImage("/images/search_minus.gif");
			icon.setActionListener("#{d.SearchPB.onRemoveSelectionRow}");
			colSynchedRow.addSubNode(icon);
		}

		return colSynchedRow;
	}

	private ROWDYNAMICCONTENTBinding generateSelectionPanel() throws Exception {

		List<ComponentNode> components = new ArrayList<ComponentNode>();
		ROWDYNAMICCONTENTBinding content = new ROWDYNAMICCONTENTBinding();

		COLSYNCHEDPANENode colSynchedPane = new COLSYNCHEDPANENode();
		colSynchedPane.setColdistance(5);
		colSynchedPane.setRowdistance(5);

		for (SelectionRowObject selectionRowObject : getSelectionRowObjects().values()) {
			COLSYNCHEDROWNode colSynchedRow = selectionRowObject.getColSynchedRow();
			colSynchedPane.addSubNode(colSynchedRow);
		}

		components.add(colSynchedPane);

		content.setContentNodes(components);

		return content;
	}

	private ROWDYNAMICCONTENTBinding generateResultGrid() throws Exception {

		List<ComponentNode> components = new ArrayList<ComponentNode>();
		ROWDYNAMICCONTENTBinding content = new ROWDYNAMICCONTENTBinding();

		FIXGRIDNode fixgrid = new FIXGRIDNode();
		fixgrid.setObjectbinding("#{d.SearchPB.gridResult}");
		fixgrid.setSelectorcolumn("1");
		fixgrid.setWidth("100%");
		fixgrid.setHeight("100%");
		fixgrid.setSbvisibleamount("50");

		for (ObjAttribute objAttribute : objEntity.getAttributes()) {

			String field = objAttribute.getName();
			Metadata metadate = metadataMap.get(field);
			if (metadate != null && metadate.getTechnical())
				continue;

			GRIDCOLNode gridcol = new GRIDCOLNode();
			gridcol.setText(Helper.getColumnNameForEntity(entityName, objAttribute.getDbAttributeName()));
			gridcol.setWidth(100);
			gridcol.setDynamicwidthsizing(true);

			ComponentNode value = createComponentNodeForResultColumn(objAttribute);
			gridcol.addSubNode(value);

			ICONNode icon = new ICONNode();
			icon.setImage("/eclntjsfserver/images/magnifier.png&buffer");
			icon.setConfiginfo(objAttribute.getName());
			icon.setActionListener("#{d.SearchPB.onAddSearchField}");
			gridcol.addSubNode(icon);

			fixgrid.addSubNode(gridcol);

		}
		components.add(fixgrid);

		// ICONNode icon = new ICONNode();
		// icon.setActionListener("#{d.SearchPB.gridResult.onEditColumnDetails}");
		// icon.setImage("/images/setting_tool_16_16.png&buffer");
		// icon.setRowalignmenty("top");
		// components.add(icon);

		content.setContentNodes(components);
		return content;
	}

	private ComponentNode createComponentNodeForResultColumn(ObjAttribute objAttribute) {
		String type = objAttribute.getType();
		String field = objAttribute.getName();

		ComponentNode value = null;

		if ("java.util.Date".equals(type)) {
			LABELNode label = new LABELNode();
			label.setText(".{data." + objAttribute.getName() + "}");
			label.setFormat("datetime");
			label.setFormatmask("short");
			value = label;
		} else if ("java.lang.Boolean".equals(type)) {
			CHECKBOXNode checkbox = new CHECKBOXNode();
			checkbox.setSelected(".{data." + objAttribute.getName() + "}");
			checkbox.setAlign("center");
			checkbox.setEnabled(false);
			value = checkbox;
		} else if ("java.lang.Integer".equals(type)) {
			LABELNode label = new LABELNode();
			label.setText(".{data." + objAttribute.getName() + "}");
			label.setFormat("int");
			label.setAlign("right");
			value = label;
		} else {
			Metadata metadate = metadataMap.get(field);
			if (metadate != null && StringUtils.isNotBlank(metadate.getVvb())) {
				COMBOBOXNode combobox = new COMBOBOXNode();
				combobox.setValidvaluesbinding("#{h.vvb." + metadate.getVvb() + "}");
				combobox.setValue(".{data." + objAttribute.getName() + "}");
				combobox.setEnabled(false);
				combobox.setWidth(150);
				value = combobox;
			} else {
				LABELNode label = new LABELNode();
				label.setText(".{data." + objAttribute.getName() + "}");
				value = label;
			}
		}
		return value;
	}

	private ComponentNode createComponentNodeForSelectionRow(String id, String field, String type, String value) {
		ComponentNode component = null;
		if ("java.util.Date".equals(type)) {
			CALENDARFIELDNode calendarNode = new CALENDARFIELDNode();
			calendarNode.setValue("#{d.SearchPB.selectionRowObjects." + id + "." + value + "}");
			calendarNode.setTimezone("CET");
			calendarNode.setFormat("datetime");
			calendarNode.setFormatmask("short");
			calendarNode.setWidth(200);
			component = calendarNode;
		} else if ("java.lang.Boolean".equals(type)) {
			CHECKBOXNode checkboxNode = new CHECKBOXNode();
			checkboxNode.setSelected("#{d.SearchPB.selectionRowObjects." + id + "." + value + "}");
			checkboxNode.setAlign("center");
			component = checkboxNode;
		} else if ("java.lang.Integer".equals(type)) {
			FORMATTEDFIELDNode fieldNode = new FORMATTEDFIELDNode();
			fieldNode.setValue("#{d.SearchPB.selectionRowObjects." + id + "." + value + "}");
			fieldNode.setAlign("right");
			fieldNode.setFormat("int");
			fieldNode.setRestricttokeys("-0123456789");
			fieldNode.setRequestfocus("#{d.SearchPB.selectionRowObjects." + id + ".focus}");
			fieldNode.setWidth(200);
			component = fieldNode;
		} else {
			Metadata metadate = metadataMap.get(field);
			if (metadate != null && StringUtils.isNotBlank(metadate.getVvb())) {
				COMBOBOXNode comboboxNode = new COMBOBOXNode();
				comboboxNode.setValidvaluesbinding("#{h.vvb." + metadate.getVvb() + "}");
				comboboxNode.setValue("#{d.SearchPB.selectionRowObjects." + id + "." + value + "}");
				comboboxNode.setRequestfocus("#{d.SearchPB.selectionRowObjects." + id + ".focus}");
				comboboxNode.setEnabled(true);
				comboboxNode.setWidth(200);
				component = comboboxNode;
			} else {
				FIELDNode fieldNode = new FIELDNode();
				fieldNode.setText("#{d.SearchPB.selectionRowObjects." + id + "." + value + "}");
				fieldNode.setRequestfocus("#{d.SearchPB.selectionRowObjects." + id + ".focus}");
				fieldNode.setWidth(200);
				component = fieldNode;
			}
		}
		return component;
	}

	private ListOrderedMap selectionRowObjects = new ListOrderedMap();

	@SuppressWarnings("unchecked")
	public Map<String, SelectionRowObject> getSelectionRowObjects() {
		return selectionRowObjects;
	}

	public class SelectionRowObject {
		private String id;
		private COLSYNCHEDROWNode colSynchedRow;
		private String field;
		private Operator operator;
		private Object valueLow;
		private Object valueHigh;
		private long focus;

		public COLSYNCHEDROWNode getColSynchedRow() {
			return colSynchedRow;
		}

		public String getId() {
			return id;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public Operator getOperator() {
			return operator;
		}

		public void setOperator(Operator operator) {
			this.operator = operator;
		}

		public String getOperatorAsString() {
			return operator.name();
		}

		public void setOperatorAsString(String operator) {
			this.operator = Operator.valueOf(operator);
		}

		public Object getValueLow() {
			return valueLow;
		}

		public void setValueLow(Object valueLow) {
			this.valueLow = valueLow;
		}

		public Object getValueHigh() {
			return valueHigh;
		}

		public void setValueHigh(Object valueHigh) {
			this.valueHigh = valueHigh;
		}

		public long getFocus() {
			return focus;
		}

		public SelectionRowObject(String id) throws Exception {
			super();
			this.id = id;
			this.colSynchedRow = createSelectionRow(this);
		}

		public SelectionRowObject(String id, String field, Operator operator) throws Exception {
			super();
			this.id = id;
			this.field = field;
			this.operator = operator;
			this.colSynchedRow = createSelectionRow(this);
		}

		public void focus() {
			this.focus = RequestFocusManager.getNewRequestFocusCounter();
		}

		public void recreateSelectionRow() throws Exception {
			this.valueLow = null;
			this.valueHigh = null;
			this.colSynchedRow = createSelectionRow(this);
		}

		private String getTypeOfField() {
			if (field == null)
				return null;
			return objEntity.getAttributeMap().get(field).getType();
		}

		public Expression buildExpression() {
			String dbName = objEntity.getAttributeMap().get(field).getDbAttributeName();
			Reflect refField = Reflect.on(SearchPB.this.entityClazz).field(dbName.toUpperCase());
			Reflect refMethod = null;
			if (operator == Operator.between) {
				refMethod = refField.call(operator.name(), valueLow, valueHigh);
			} else {
				refMethod = refField.call(operator.name(), operator == Operator.like ? "%" + valueLow + "%" : valueLow);
			}
			Expression expression = refMethod.get();
			return expression;

		}
	}

	@Override
	public Variant buildActualVariant(String name, String description) {
		Variant variant = new Variant(name, description);

		String columnSequence;
		String columnWidths;

		columnSequence = getGridResult().getColumnsequence();
		columnWidths = getGridResult().getModcolumnwidths();
		variant.addGrid("GRID", new GridValues(columnSequence, columnWidths));

		return variant;
	}

	@Override
	public void loadVariant(Variant variant) {
		for (Entry<String, GridValues> entry : variant.getGridsMap().entrySet()) {
			if ("GRID".equals(entry.getKey())) {
				getGridResult().setColumnsequence(entry.getValue().getColumnSequence());
				getGridResult().setModcolumnwidths(entry.getValue().getColumnWidths());
			}
		}
	}
}

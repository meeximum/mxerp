package managedbeans.commons;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.apache.cayenne.map.ObjAttribute;
import org.apache.cayenne.map.ObjEntity;
import org.apache.commons.lang3.StringUtils;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.componentnodes.COLSYNCHEDPANENode;
import org.eclnt.jsfserver.elements.componentnodes.COLSYNCHEDROWNode;
import org.eclnt.jsfserver.elements.componentnodes.FIELDNode;
import org.eclnt.jsfserver.elements.componentnodes.LABELNode;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding;
import org.eclnt.jsfserver.pagebean.PageBean;
import org.joor.Reflect;

import utils.Helper;
import db.erp.ICustomizing;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.TableKeyPopupPB}")
public class TableKeyPopupPB extends PageBean implements Serializable {

	public void onOk(ActionEvent event) {
		if (checkMandatoryFields()) {
			callback.ok(newObject);
		} else {
			Statusbar.outputError("Alle Schlüsselfelder müssen befüllt werden!");
		}
	}

	public void onCancel(ActionEvent event) {
		callback.cancel();
	}

	private ROWDYNAMICCONTENTBinding content = new ROWDYNAMICCONTENTBinding();

	public ROWDYNAMICCONTENTBinding getContent() {
		return content;
	}

	private ICustomizing newObject;

	public ICustomizing getNewObject() {
		return newObject;
	}

	private ObjEntity objEntity;

	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------

	public TableKeyPopupPB(ICustomizing newObject2, ObjEntity objEntity) throws Exception {
		this.newObject = newObject2;
		this.objEntity = objEntity;
		generatePane();
	}

	private ROWDYNAMICCONTENTBinding generatePane() throws Exception {

		COLSYNCHEDPANENode paneNode = new COLSYNCHEDPANENode();
		paneNode.setColspan(2);
		paneNode.setColdistance(10);
		paneNode.setRowdistance(5);

		for (ObjAttribute objAttribute : objEntity.getPrimaryKeys()) {
			COLSYNCHEDROWNode rowNode = new COLSYNCHEDROWNode();
			LABELNode label = new LABELNode();

			label.setText(Helper.getColumnNameForEntity(objEntity.getDbEntityName(), objAttribute.getDbAttributeName()));
			rowNode.addSubNode(label);
			FIELDNode field = new FIELDNode();
			field.setText("#{d.TableKeyPopupPB.newObject." + objAttribute.getName() + "}");
			field.setWidth(150);
			field.setBgpaint("mandatory()");
			rowNode.addSubNode(field);

			paneNode.addSubNode(rowNode);
		}

		content.setContentNode(paneNode);
		return content;
	}

	private boolean checkMandatoryFields() {
		for (ObjAttribute objAttribute : objEntity.getPrimaryKeys()) {
			String value = Reflect.on(newObject).call("get" + StringUtils.capitalize(objAttribute.getName())).get();
			if (StringUtils.isBlank(value))
				return false;
		}
		return true;
	}

	public String getPageName() {
		return "/ui/commons/table-key-popup.jsp";
	}

	public String getRootExpressionUsedInPage() {
		return "#{d.TableKeyPopupPB}";
	}

	private ICallback callback;

	public void setCallback(ICallback callback) {
		this.callback = callback;
	}

	public interface ICallback {
		public void ok(ICustomizing newObject);

		public void cancel();
	}
}

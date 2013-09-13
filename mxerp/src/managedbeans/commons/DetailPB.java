package managedbeans.commons;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.faces.event.ActionEvent;

import managedbeans.WorkpageDispatchedPageBean;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.map.ObjEntity;
import org.apache.cayenne.query.SelectQuery;
import org.apache.commons.lang3.StringUtils;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.joor.Reflect;

import services.entities.IEntity;
import utils.CayenneUtils;
import utils.Constants;
import utils.Helper;
import db.erp.Metadata;
import exceptions.ValidationException;

// TODO: Check onClose if not in EDIT mode!
// TODO: Delete functionality
@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.DetailPB}")
public class DetailPB extends WorkpageDispatchedPageBean implements Serializable {
	public enum Mode {
		EDIT, READ
	}

	public void onDelete(ActionEvent event) {
		try {
			YESNOPopup ynp = YESNOPopup.createInstance("Löschen", String.format("Wollen sie wirklich %s löschen?", data.toString()), new IYesNoCancelListener() {
				public void reactOnCancel() {
				}

				public void reactOnNo() {
				}

				public void reactOnYes() {
					data.setChangedAt(new Date());
					data.setChangedBy(Helper.getUserName());
					data.setDeleted(true);
					context.commitChanges();
					getWorkpageContainer().closeWorkpageForced(getWorkpage());
				}

			});
			ynp.getModalPopup().setLeftTopReferenceCentered();
		} catch (Exception ex) {
			logger.error(Helper.getStackTraceAsString(ex), ex);
			Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
		}
	}

	public void onRollback(ActionEvent event) {
		context.rollbackChanges();
		if (newEntity) {
			getWorkpageContainer().closeWorkpageForced(getWorkpage());
		} else {
			mode = Mode.READ;
		}
	}

	public void onCommit(ActionEvent event) {
		try {
			checkMandatoryFields();
			// when created then no change date is filled
			if (data.getChangedAt() == null) {
				data.setChangedAt(data.getCreatedAt());
				data.setChangedBy(data.getCreatedBy());
			} else {
				data.setChangedAt(new Date());
				data.setChangedBy(Helper.getUserName());
			}
			context.commitChanges();
			mode = Mode.READ;
			newEntity = false;
			getWorkpage().setTitle(data.toString());
			Statusbar.outputSuccess("Daten erfolgreich gesichert!");
		} catch (Exception ex) {
			logger.error(Helper.getStackTraceAsString(ex), ex);
			Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
		} catch (ValidationException ve) {
			Statusbar.outputAlert(String.format("Das Feld %s ist ein Mußfeld!", ve.getField())).setLeftTopReferenceCentered();
		}
	}

	public void onEdit(ActionEvent event) {
		// set global lock
		mode = Mode.EDIT;
	}

	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------

	private IEntity data;

	public IEntity getData() {
		return data;
	}

	private String entityName;
	private String entityId;
	private String detailView;

	private ObjEntity objEntity;
	private Class<CayenneDataObject> entityClazz;

	private Map<String, Metadata> metadataMap;

	private ObjectContext context;

	private Mode mode;

	private boolean newEntity = false;

	public boolean isInEditMode() {
		return mode == Mode.EDIT;
	}

	public boolean isInReadMode() {
		return mode == Mode.READ;
	}
	
	@SuppressWarnings("unchecked")
	public DetailPB(IWorkpageDispatcher workpageDispatcher) throws Exception {
		super(workpageDispatcher);
	
		context = CayenneUtils.createNewContext();

		entityName = workpageDispatcher.getWorkpage().getParam(Constants.WP_PARAMS_ENTITY);
		entityId = workpageDispatcher.getWorkpage().getParam(Constants.WP_PARAMS_ENTITYID);
		detailView = workpageDispatcher.getWorkpage().getParam(Constants.WP_PARAMS_DETAILVIEW);

		objEntity = getContext().getEntityResolver().getObjEntity(entityName);
		entityClazz = (Class<CayenneDataObject>) Class.forName(objEntity.getClassName());

		metadataMap = Metadata.getByEntityAsMap(getContext(), entityName);

		if ("NEW".equals(entityId)) {
			mode = Mode.EDIT;

			newEntity = true;

			entityId = UUID.randomUUID().toString();

			data = (IEntity) context.newObject(entityClazz);
			data.setId(entityId);
			data.setCreatedAt(new Date());
			data.setCreatedBy(Helper.getUserName());
			data.setDeleted(false);
			Reflect.on(data).call("setType", "O");
			Reflect.on(data).call("setGrouping", "CUST01");
			// TODO: put into db class create method (handle prefill of
			// partnerno

			getWorkpage().setTitle("NEW");
		} else {
			mode = Mode.READ;

			Expression expression = IEntity.ID.eq(entityId);
			SelectQuery<CayenneDataObject> query = SelectQuery.query(entityClazz, expression);

			data = (IEntity) context.performQuery(query).get(0);

			getWorkpage().setTitle(data.toString());
		}

	}

	private void checkMandatoryFields() throws ValidationException {
		for (Metadata metadate : metadataMap.values()) {
			if (metadate.getMandatory()) {
				String field = metadate.getField();
				Object value = Reflect.on(data).call("get" + StringUtils.capitalize(field)).get();
				if (value == null || StringUtils.isBlank(String.valueOf(value)))
					throw new ValidationException(field);
			}
		}
	}

	public String getPageName() {
		return detailView;
	}

	public String getRootExpressionUsedInPage() {
		return "#{d.DetailPB}";
	}

	// ------------------------------------------------------------------------
	// public usage
	// ------------------------------------------------------------------------

	// ------------------------------------------------------------------------
	// private usage
	// ------------------------------------------------------------------------
}

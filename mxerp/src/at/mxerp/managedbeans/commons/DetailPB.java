package at.mxerp.managedbeans.commons;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.faces.event.ActionEvent;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.map.ObjEntity;
import org.apache.cayenne.query.SelectQuery;
import org.apache.commons.lang3.StringUtils;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.joor.Reflect;

import at.mxerp.db.erp.Metadata;
import at.mxerp.db.erp.UserPresets;
import at.mxerp.exceptions.EntityLockedException;
import at.mxerp.exceptions.ValidationException;
import at.mxerp.managedbeans.WorkpageDispatchedPageBean;
import at.mxerp.services.entities.Entity;
import at.mxerp.services.entities.IEntity;
import at.mxerp.services.entities.LockManager;
import at.mxerp.utils.CayenneUtils;
import at.mxerp.utils.Constants;
import at.mxerp.utils.Helper;

@SuppressWarnings("serial")
public abstract class DetailPB extends WorkpageDispatchedPageBean implements Serializable {
	public enum Mode {
		EDIT, READ
	}

	public boolean getRenderDeleteInfo() {
		return data.getDeleted();
	}

	public boolean getRenderDeleteBtn() {
		return data.getDeleted() == false && isInReadMode();
	}

	public boolean getRenderCommitBtn() {
		return isInEditMode();
	}

	public boolean getRenderRollbackBtn() {
		return isInEditMode();
	}

	public boolean getRenderEditBtn() {
		return data.getDeleted() == false && isInReadMode();
	}

	public void onDelete(ActionEvent event) {

		YESNOPopup ynp = YESNOPopup.createInstance("Löschen", String.format("Wollen sie wirklich %s löschen?", data.toString()), new IYesNoCancelListener() {
			public void reactOnCancel() {
			}

			public void reactOnNo() {
			}

			public void reactOnYes() {
				try {
					LockManager.lockEntity(getId());
					data.setChangedAt(new Date());
					data.setChangedBy(Helper.getUserName());
					data.setDeleted(true);
					localContext.commitChanges();
					closeWorkpage(true);
					LockManager.unlockEntity(entity.name() + entityId);
				} catch (EntityLockedException ele) {
					Statusbar.outputWarningWithPopup(String.format("Datensatz ist druch %s gesperrt!", ele.getUser())).setLeftTopReferenceCentered();
				} catch (Exception ex) {
					logger.error(Helper.getStackTraceAsString(ex), ex);
					Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
				}
			}
		});
		ynp.getModalPopup().setLeftTopReferenceCentered();

	}

	public String getId() {
		return entity.name() + ":" + entityId;
	}

	public void onRollback(ActionEvent event) {
		try {
			localContext.rollbackChanges();
			afterRollback();
			if (newEntity) {
				closeWorkpage(true);
			} else {
				mode = Mode.READ;
			}
			LockManager.unlockEntity(getId());
		} catch (Exception ex) {
			logger.error(Helper.getStackTraceAsString(ex), ex);
			Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
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
			beforeCommit();
			localContext.commitChanges();
			afterCommit();
			mode = Mode.READ;
			newEntity = false;
			getWorkpage().setTitle(data.toString());
			Statusbar.outputSuccess("Daten erfolgreich gesichert!");
			LockManager.unlockEntity(getId());
		} catch (ValidationException ve) {
			Statusbar.outputAlert(String.format("Das Feld %s ist ein Mußfeld!", ve.getField())).setLeftTopReferenceCentered();

		} catch (Exception ex) {
			logger.error(Helper.getStackTraceAsString(ex), ex);
			Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
		}
	}

	public void onEdit(ActionEvent event) {
		try {
			// check global lock
			LockManager.lockEntity(getId());
			loadEntity();
			// set global lock
			mode = Mode.EDIT;
		} catch (EntityLockedException ele) {
			Statusbar.outputWarningWithPopup(String.format("Datensatz ist durch %s gesperrt!", ele.getUser())).setLeftTopReferenceCentered();
		} catch (Exception ex) {
			logger.error(Helper.getStackTraceAsString(ex), ex);
			Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
		}
	}

	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------

	private IEntity data;

	public IEntity getData() {
		return data;
	}

	// private String entityName;
	private Entity entity;
	private String entityId;

	private ObjEntity objEntity;
	private Class<CayenneDataObject> entityClazz;

	private Map<String, Metadata> metadataMap;

	private ObjectContext localContext;

	protected ObjectContext getLocalContext() {
		return localContext;
	}

	private Mode mode;

	private boolean newEntity = false;

	protected boolean isNewEntity() {
		return newEntity;
	}

	public boolean isInEditMode() {
		return mode == Mode.EDIT;
	}

	public boolean isInReadMode() {
		return mode == Mode.READ;
	}

	@SuppressWarnings("unchecked")
	public DetailPB(IWorkpageDispatcher workpageDispatcher) throws Exception {
		super(workpageDispatcher);

		localContext = CayenneUtils.createNewContext();

		entity = Entity.valueOf(workpageDispatcher.getWorkpage().getParam(Constants.WP_PARAMS_ENTITY));
		entityId = workpageDispatcher.getWorkpage().getParam(Constants.WP_PARAMS_ENTITYID);

		objEntity = getLocalContext().getEntityResolver().getObjEntity(entity.getObjName());
		entityClazz = (Class<CayenneDataObject>) Class.forName(objEntity.getClassName());

		metadataMap = Metadata.getByObjectAsMap(getLocalContext(), entity.getObjName());

		if ("NEW".equals(entityId)) {
			mode = Mode.EDIT;

			newEntity = true;

			entityId = UUID.randomUUID().toString();

			data = (IEntity) localContext.newObject(entityClazz);
			data.setId(entityId);
			data.setCreatedAt(new Date());
			data.setCreatedBy(Helper.getUserName());
			data.setDeleted(false);

			// prefill
			List<UserPresets> presets = UserPresets.getByUserAndEntity(localContext, Helper.getUserName(), entity);
			for (UserPresets preset : presets) {
				try {
					Reflect.on(data).call("set" + StringUtils.capitalize(preset.getField()), preset.getValue());
				} catch (Exception ex) {
					// logger.error(String.format("Field s% not valid for entity %s",
					// preset.getField(), preset.getEntity()));
				}
			}

			getWorkpage().setTitle("NEW");
		} else {
			mode = Mode.READ;

			loadEntity();

			getWorkpage().setTitle(data.toString());
		}

	}

	private void loadEntity() throws Exception {
		Expression expression = IEntity.ID.eq(entityId);
		SelectQuery<CayenneDataObject> query = SelectQuery.query(entityClazz, expression);
		data = (IEntity) Cayenne.objectForSelect(localContext, query);
		if (data == null)
			throw new Exception(String.format("No entity for id %s found!", entityId));
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

	private boolean isCloseHideOfWorkpagePossible() {
		if (mode == Mode.EDIT) {
			Statusbar.outputWarningWithPopup("Daten zuerst speichern!").setLeftTopReferenceCentered();
			return false;
		} else {
			return true;
		}
	}

	@Override
	protected boolean beforeCloseWorkpage() {
		return isCloseHideOfWorkpagePossible();
	}

	@Override
	protected boolean beforeHideWorkpage() {
		return isCloseHideOfWorkpagePossible();
	}

	protected void beforeCommit() throws Exception {

	}

	protected void afterCommit() throws Exception {

	}

	protected void afterRollback() throws Exception {

	}

}

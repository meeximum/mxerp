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
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.joor.Reflect;

import services.entities.IEntity;
import services.entities.LockManager;
import utils.CayenneUtils;
import utils.Constants;
import utils.Helper;
import db.erp.Metadata;
import exceptions.EntityLockedException;
import exceptions.ValidationException;

@SuppressWarnings("serial")
public abstract class DetailPB extends WorkpageDispatchedPageBean implements Serializable {
	public enum Mode {
		EDIT, READ
	}
	
	public boolean getRenderDeleteInfo() {
		return data.getDeleted();
	}
	
	public boolean getRenderDeleteBtn() {
		return data.getDeleted()==false && isInReadMode();
	}
	
	public boolean getRenderCommitBtn() {
		return isInEditMode();
	}
	
	public boolean getRenderRollbackBtn() {
		return isInEditMode();
	}
	
	public boolean getRenderEditBtn() {
		return data.getDeleted()==false && isInReadMode();
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
					context.commitChanges();
					closeWorkpage(true);
					LockManager.unlockEntity(entityName + entityId);
				} catch (EntityLockedException ele) {
					Statusbar.outputWarningWithPopup(String.format("Datensatz ist druch %s gesperrt!", ele.getUser())).setLeftTopReferenceCentered();
				}  catch (Exception ex) {
					logger.error(Helper.getStackTraceAsString(ex), ex);
					Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
				}
			}
		});
		ynp.getModalPopup().setLeftTopReferenceCentered();

	}
	
	public String getId() {
		return entityName + ":" + entityId;
	}

	public void onRollback(ActionEvent event) {
		context.rollbackChanges();
		if (newEntity) {
			closeWorkpage(true);
		} else {
			mode = Mode.READ;
		}
		LockManager.unlockEntity(getId());
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
			beforeSave();
			context.commitChanges();
			afterSave();
			mode = Mode.READ;
			newEntity = false;
			getWorkpage().setTitle(data.toString());			
			Statusbar.outputSuccess("Daten erfolgreich gesichert!");
			LockManager.unlockEntity(getId());
		} catch (Exception ex) {
			logger.error(Helper.getStackTraceAsString(ex), ex);
			Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
		} catch (ValidationException ve) {
			Statusbar.outputAlert(String.format("Das Feld %s ist ein Mußfeld!", ve.getField())).setLeftTopReferenceCentered();
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
			Statusbar.outputWarningWithPopup(String.format("Datensatz ist druch %s gesperrt!", ele.getUser())).setLeftTopReferenceCentered();
		}
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

	private ObjEntity objEntity;
	private Class<CayenneDataObject> entityClazz;

	private Map<String, Metadata> metadataMap;

	private ObjectContext context;
	
	protected ObjectContext getContext() {
		return context;
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

		context = CayenneUtils.createNewContext();

		entityName = workpageDispatcher.getWorkpage().getParam(Constants.WP_PARAMS_ENTITY);
		entityId = workpageDispatcher.getWorkpage().getParam(Constants.WP_PARAMS_ENTITYID);		

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

			// TODO: put into db class create method (handle prefill of
			// partnerno

			getWorkpage().setTitle("NEW");
		} else {
			mode = Mode.READ;

			loadEntity();

			getWorkpage().setTitle(data.toString());
		}

	}

	private void loadEntity() {
		Expression expression = IEntity.ID.eq(entityId);			
		SelectQuery<CayenneDataObject> query = SelectQuery.query(entityClazz, expression);
		data = (IEntity) context.performQuery(query).get(0);
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
	
	protected void beforeSave() throws Exception {
		
	}
	
	protected void afterSave() throws Exception {
		
	}

}

package at.mxerp.managedbeans.misc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.cayenne.ObjectContext;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import at.mxerp.db.erp.SavedSearches;
import at.mxerp.db.erp.UserPresets;
import at.mxerp.managedbeans.WorkpageDispatchedPageBean;
import at.mxerp.utils.CayenneUtils;
import at.mxerp.utils.Helper;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.UserSettingsPB}")
public class UserSettingsPB extends WorkpageDispatchedPageBean implements Serializable {
	public void onRefreshUserPresets(ActionEvent event) {
		loadDataUserPresets();
	}
	
	public void onRefreshSavedSearches(ActionEvent event) {
		loadDataSavedSearches();
	}

	public void onAddUserPreset(ActionEvent event) {
		UserPresets preset = localContext.newObject(UserPresets.class);
		preset.setUser(Helper.getUserName());
		GridUserPresetsItem item = new GridUserPresetsItem(preset);
		getGridUserPresets().getItems().add(item);
		getGridUserPresets().deselectCurrentSelection();
		getGridUserPresets().selectAndFocusItem(item);
	}

	private ObjectContext localContext;

	public void onRollback(ActionEvent event) {
		try {
			localContext.rollbackChanges();
			Statusbar.outputSuccess(Helper.getMessage("data_reset_succ"));
		} catch (Exception ex) {
			Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onCommit(ActionEvent event) {
		try {
			boolean reloadSavedSearches = false;
			for(Object newObject : localContext.newObjects()) {
				if(checkMandatory(newObject)==false) {
					Statusbar.outputWarningWithPopup("Es müssen alle Mußfelder befüllt sein!").setLeftTopReferenceCentered();					
					return;
				}
			}	
			List changedObjects = new ArrayList();
			changedObjects.addAll(localContext.deletedObjects());
			changedObjects.addAll(localContext.modifiedObjects());
			for(Object changedObject : changedObjects) {
				if(changedObject instanceof SavedSearches) reloadSavedSearches = true;
			}
			localContext.commitChanges();
			if(reloadSavedSearches) getMainUI().reloadSavedSearches();
			Statusbar.outputSuccess(Helper.getMessage("data_write_succ"));
		} catch (Exception ex) {
			Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
		}
	}
	
	private boolean checkMandatory(Object object) {
		if(object instanceof UserPresets) {
			return ((UserPresets)object).checkMandatory();
		} else if(object instanceof SavedSearches) {
			return ((SavedSearches)object).checkMandatory();
		}
		return true;
	}
	
	private FIXGRIDListBinding<GridSavedSearchesItem> gridSavedSearches = new FIXGRIDListBinding<GridSavedSearchesItem>();

	public FIXGRIDListBinding<GridSavedSearchesItem> getGridSavedSearches() {
		return gridSavedSearches;
	}

	public void setGridSavedSearches(FIXGRIDListBinding<GridSavedSearchesItem> value) {
		this.gridSavedSearches = value;
	}

	public class GridSavedSearchesItem extends FIXGRIDItem implements java.io.Serializable {
		private SavedSearches search;

		public SavedSearches getSearch() {
			return search;
		}

		public GridSavedSearchesItem(SavedSearches search) {
			super();
			this.search = search;
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
						localContext.deleteObjects(search);
						localContext.commitChanges();
						getGridSavedSearches().getItems().remove(this);
						Statusbar.outputSuccessWithPopup(Helper.getMessage("date_del_succ")).setLeftTopReferenceCentered();
					} catch (Exception ex) {
						logger.error(ex, ex);
						Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
					}
					loadDataSavedSearches();
				}
			});
			ynp.getModalPopup().setLeftTopReferenceCentered();

		}

		
	}


	private FIXGRIDListBinding<GridUserPresetsItem> gridUserPresets = new FIXGRIDListBinding<GridUserPresetsItem>();

	public FIXGRIDListBinding<GridUserPresetsItem> getGridUserPresets() {
		return gridUserPresets;
	}

	public void setGridUserPresets(FIXGRIDListBinding<GridUserPresetsItem> value) {
		this.gridUserPresets = value;
	}

	public class GridUserPresetsItem extends FIXGRIDItem implements java.io.Serializable {

		private UserPresets preset;

		public UserPresets getPreset() {
			return preset;
		}

		public GridUserPresetsItem(UserPresets preset) {
			super();
			this.preset = preset;
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
						localContext.deleteObjects(preset);
						localContext.commitChanges();
						getGridUserPresets().getItems().remove(this);
						Statusbar.outputSuccessWithPopup(Helper.getMessage("date_del_succ")).setLeftTopReferenceCentered();
					} catch (Exception ex) {
						logger.error(ex, ex);
						Statusbar.outputAlert(Helper.getStackTraceAsString(ex), ex.toString()).setLeftTopReferenceCentered();
					}
					loadDataUserPresets();
				}
			});
			ynp.getModalPopup().setLeftTopReferenceCentered();

		}

	}

	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------

	public UserSettingsPB(IWorkpageDispatcher workpageDispatcher) {
		super(workpageDispatcher);
		this.localContext = CayenneUtils.createNewContext();
		loadData();
	}

	public String getPageName() {
		return "/ui/misc/user-settings.jsp";
	}

	public String getRootExpressionUsedInPage() {
		return "#{d.UserSettingsPB}";
	}
	
	private void loadData() {
		loadDataUserPresets();
		loadDataSavedSearches();
	}

	private void loadDataUserPresets() {
		getGridUserPresets().getItems().clear();
		
		List<UserPresets> presets = UserPresets.getByUser(localContext, Helper.getUserName());
		for(UserPresets preset : presets) {
			getGridUserPresets().getItems().add(new GridUserPresetsItem(preset));
		}

	}
	
	private void loadDataSavedSearches() {
		getGridSavedSearches().getItems().clear();
		
		List<SavedSearches> searches = SavedSearches.findByUserExclGlobal(Helper.getUserName(), localContext);
		for(SavedSearches search : searches) {
			getGridSavedSearches().getItems().add(new GridSavedSearchesItem(search));
		}

	}
	
}

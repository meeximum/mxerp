package managedbeans;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import managedbeans.commons.VariantsConfigPB;
import managedbeans.trees.MasterDataFT;
import managedbeans.utils.Beanhelper;
import managedbeans.utils.Unsecure;
import managedbeans.utils.UserAccess;

import org.apache.cayenne.BaseContext;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.access.DataContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.defaultscreens.ModalPopup;
import org.eclnt.jsfserver.defaultscreens.ModalPopup.IModalPopupListener;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup;
import org.eclnt.jsfserver.defaultscreens.YESNOPopup.IYesNoCancelListener;
import org.eclnt.jsfserver.elements.BaseComponentTag;
import org.eclnt.jsfserver.elements.impl.DYNAMICCONTENTBinding;
import org.eclnt.jsfserver.elements.impl.MENUITEMComponent;
import org.eclnt.jsfserver.util.HttpSessionAccess;
import org.eclnt.jsfserver.util.useraccess.UserAccessMgr;
import org.eclnt.workplace.IWorkpage;
import org.eclnt.workplace.IWorkpageContainer;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.WorkpageByPageBean;
import org.eclnt.workplace.WorkpageStartInfo;

import services.variants.IVariants;
import services.variants.Variant;
import services.variants.VariantsManager;
import services.variants.VariantsManager.VariantType;
import services.variants.VariantsService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import utils.Helper;

@SuppressWarnings("serial")
public class WorkpageDispatchedPageBean extends org.eclnt.workplace.WorkpageDispatchedPageBean {

	public WorkpageDispatchedPageBean(IWorkpageDispatcher dispatcher) {
		super(dispatcher);
		logger = LogFactory.getLog(super.getClass());
		Annotation unsecure = this.getClass().getAnnotation(Unsecure.class);
		if (unsecure == null)
			checkAuthorization();
		if (WorkpageDispatchedPageBean.this instanceof IVariants) {
			variantsObject = getWorkpage().getId() == null ? getClass().getSimpleName() : getClass().getSimpleName() + "_" + getWorkpage().getId();
			variantsManager = new VariantsManager((IVariants) WorkpageDispatchedPageBean.this, Helper.getUserName(), variantsObject);
		}
	}

	@Override
	public String getPageName() {
		throw new NotImplementedException();
	}

	@Override
	public String getRootExpressionUsedInPage() {
		throw new NotImplementedException();
	}

	private void checkAuthorization() {
		// return if viewed in editor
		if (HttpSessionAccess.checkIfInLayoutEditorPreview())
			return;
		// check user
		String user = UserAccessMgr.getCurrentUser();
		if (StringUtils.isEmpty(user) || UserAccess.USER_UNDEFINED.equals(user)) {
			try {
				HttpSessionAccess.getCurrentResponse().sendError(403);
			} catch (IOException e) {
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			}
		}
	}

	protected IndexUI getIndexUI() {
		return (IndexUI) getOwningDispatcher().getDispatchedBean(IndexUI.class);
	}

	protected MainUI getMainUI() {
		return (MainUI) getOwningDispatcher().getDispatchedBean(MainUI.class);
	}

	protected MasterDataFT getMasterDataFT() {
		return (MasterDataFT) getOwningDispatcher().getDispatchedBean(MasterDataFT.class);
	}

	protected Beanhelper getBeanhelper() {
		return (Beanhelper) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("h");
	}

	protected Log logger;

	private String variantsObject;

	VariantsManager variantsManager;

	public void onVariantSelected(ActionEvent event) {
		if (variantsManager == null)
			return;
		if (event.getSource() instanceof MENUITEMComponent) {
			MENUITEMComponent menuItemComponent = (MENUITEMComponent) event.getSource();
			String name = menuItemComponent.getAttributeString(BaseComponentTag.ATT_COMMENT);
			try {
				selectedVariant = variantsManager.onVariantSelected(name);
			} catch (Exception ex) {
				Statusbar.outputAlert(Helper.getMessage("error_loading_variant"), ex.toString());
			}
		}
	}

	public void onVariantConfig(ActionEvent event) {
		VariantsConfigPB variantsConfigPB = new VariantsConfigPB(variantsObject);

		final ModalPopup mp = openModalPopup(variantsConfigPB, Helper.getLiteral("variants"), 0, 0, null);
		mp.setPopupListener(new IModalPopupListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void reactOnPopupClosedByUser() {
				variantsManager.fillVariantsForMenu();
				mp.close();
			}
		});

		variantsConfigPB.setCallback(new VariantsConfigPB.ICallback() {
			@Override
			public void save(final String user, final String object, final String name, final String description) {
				final Variant variant = ((IVariants) WorkpageDispatchedPageBean.this).buildActualVariant(name, description);
				try {
					if (VariantsService.checkIfVariantExists(user, object, name)) {
						YESNOPopup ynp = YESNOPopup.createInstance(Helper.getLiteral("variants"), String.format(Helper.getMessage("variants_overwrite"), name), new IYesNoCancelListener() {
							@Override
							public void reactOnCancel() {
							}

							@Override
							public void reactOnNo() {
							}

							@Override
							public void reactOnYes() {
								try {
									VariantsService.save(user, object, variant);
									variantsManager.fillVariantsForMenu();
									mp.close();
								} catch (Exception ex) {
									Statusbar.outputAlert(Helper.getMessage("error_saving_variant"), Helper.getStackTraceAsString(ex));
								}
							}
						});
						ynp.getModalPopup().setLeftTopReferenceCentered();
					} else {
						VariantsService.save(user, object, variant);
						variantsManager.fillVariantsForMenu();
						mp.close();
					}
				} catch (Exception ex) {
					Statusbar.outputAlert(Helper.getMessage("error_saving_variant"), Helper.getStackTraceAsString(ex));
				}
			}
		});
	}

	private String selectedVariant;

	public String getSelectedVariant() {
		return selectedVariant;
	}

	public DYNAMICCONTENTBinding getVariantsBinding() {
		if (variantsManager == null)
			return null;
		return variantsManager.getVariantsBinding(buildExpression("selectedVariant"), buildExpression("onVariantSelected"), buildExpression("onVariantConfig"));
	}

	protected void loadUsersOrGlobalDefaultVariant() {
		if (variantsManager == null)
			return;
		VariantType variantType = variantsManager.loadUsersOrGlobalDefaultVariant();
		if (variantType == VariantType.USER)
			selectedVariant = Helper.getUserName();
	}

	private String buildExpression(String property) {
		String rootExpression = getRootExpressionUsedInPage().trim();
		String base = rootExpression.substring(0, rootExpression.length() - 1) + "." + property + "}";
		return base;
	}

	protected ObjectContext getContext() {
		return BaseContext.getThreadObjectContext();
	}

	protected DataContext getDataContext() {
		return (DataContext) BaseContext.getThreadObjectContext();
	}
	
	protected void openWorkpage(WorkpageStartInfo wpsi) {
		IWorkpageDispatcher wpd = (IWorkpageDispatcher) getOwningDispatcher().getTopOwner();
		IWorkpageContainer wpc = wpd.getWorkpageContainer();
		
		IWorkpage wp = wpc.getWorkpageForId(wpsi.getId());
		if (wp != null) {
			wpc.switchToWorkpage(wp);
		} else {
			wpc.addWorkpage(new WorkpageByPageBean(wpd, wpsi.getText(), wpsi));
		}
	}

}

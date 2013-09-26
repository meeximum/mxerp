package at.mxerp.managedbeans;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

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
import org.eclnt.jsfserver.elements.componentnodes.LABELNode;
import org.eclnt.jsfserver.elements.componentnodes.PANENode;
import org.eclnt.jsfserver.elements.componentnodes.ROWNode;
import org.eclnt.jsfserver.elements.componentnodes.SCROLLPANENode;
import org.eclnt.jsfserver.elements.componentnodes.TEXTPANENode;
import org.eclnt.jsfserver.elements.impl.DYNAMICCONTENTBinding;
import org.eclnt.jsfserver.elements.impl.MENUITEMComponent;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding;
import org.eclnt.jsfserver.util.HttpSessionAccess;
import org.eclnt.jsfserver.util.useraccess.UserAccessMgr;
import org.eclnt.workplace.IWorkpage;
import org.eclnt.workplace.IWorkpageContainer;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.IWorkpageLifecycleListener;
import org.eclnt.workplace.IWorkpageStarter;
import org.eclnt.workplace.WorkpageStartInfo;
import org.eclnt.workplace.WorkpageStarterFactory;

import at.mxerp.managedbeans.commons.VariantsConfigPB;
import at.mxerp.managedbeans.trees.MasterDataFT;
import at.mxerp.managedbeans.utils.Beanhelper;
import at.mxerp.managedbeans.utils.Unsecure;
import at.mxerp.managedbeans.utils.UserAccess;
import at.mxerp.services.variants.IVariants;
import at.mxerp.services.variants.Variant;
import at.mxerp.services.variants.VariantsManager;
import at.mxerp.services.variants.VariantsService;
import at.mxerp.services.variants.VariantsManager.VariantType;
import at.mxerp.utils.Helper;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
		getWorkpage().addLifecycleListener(new IWorkpageLifecycleListener() {
			
			@Override
			public void setCloseContinueOperation(Runnable closeContinueOperation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reactOnShownInPopup() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reactOnShownInContentArea() {
				
			}
			
			@Override
			public void reactOnHiddenInContentArea() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reactOnDestroyed() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean reactOnBeforeHiddenInContentArea() {
				return beforeHideWorkpage();
			}
			
			@Override
			public Runnable getCloseContinueOperation() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void closeForced() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean close() {
				return beforeCloseWorkpage();
			}
		});
	}
	
	protected boolean beforeCloseWorkpage() {
		return true;
	}
	
	protected boolean beforeHideWorkpage() {
		return true;
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
		return (IndexUI) getOwningDispatcher().getTopOwner().getDispatchedBean(IndexUI.class);
	}

	protected MainUI getMainUI() {
		return (MainUI) getOwningDispatcher().getTopOwner().getDispatchedBean(MainUI.class);
	}

	protected MasterDataFT getMasterDataFT() {
		return (MasterDataFT) getOwningDispatcher().getTopOwner().getDispatchedBean(MasterDataFT.class);
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

	protected ObjectContext getLocalContext() {
		return BaseContext.getThreadObjectContext();
	}

	protected DataContext getDataContext() {
		return (DataContext) BaseContext.getThreadObjectContext();
	}
	
	protected void openWorkpage(WorkpageStartInfo wpsi) {
		IWorkpageStarter wps = WorkpageStarterFactory.getWorkpageStarter();		
		IWorkpageDispatcher wpd = (IWorkpageDispatcher) getOwningDispatcher().getTopOwner();
		IWorkpageContainer wpc = wpd.getWorkpageContainer();		
		wps.startWorkpage(wpd, wpc, wpsi);
		
//		IWorkpage wp = wpc.getWorkpageForId(wpsi.getId());
//		if (wp != null) {
//			wpc.switchToWorkpage(wp);
//		} else {
//			wpc.addWorkpage(new WorkpageByPageBean(wpd, wpsi.getText(), wpsi));
//		}
	}
	
	protected void reopenWorkpage(WorkpageStartInfo wpsi) {
		IWorkpageStarter wps = WorkpageStarterFactory.getWorkpageStarter();		
		IWorkpageDispatcher wpd = (IWorkpageDispatcher) getOwningDispatcher().getTopOwner();
		IWorkpageContainer wpc = wpd.getWorkpageContainer();
		
		IWorkpage wp = wpc.getWorkpageForId(wpsi.getId());
		if(wp!=null) wpc.closeWorkpageForced(wp);
		
		wps.startWorkpage(wpd, wpc, wpsi);
		
		
		
//		IWorkpage wp = wpc.getWorkpageForId(wpsi.getId());
//		if (wp != null) {
//			wpc.switchToWorkpage(wp);
//		} else {
//			wpc.addWorkpage(new WorkpageByPageBean(wpd, wpsi.getText(), wpsi));
//		}
	}
	
	protected void closeWorkpage(boolean force) {
		if(force) {
			getWorkpageContainer().closeWorkpageForced(getWorkpage());
		} else {
			getWorkpageContainer().closeWorkpage(getWorkpage());
		}		
	}
	
	protected void renderError(Exception e, ROWDYNAMICCONTENTBinding content) {

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

}

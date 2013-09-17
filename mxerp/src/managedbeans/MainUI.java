package managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.event.ActionEvent;

import managedbeans.trees.CustomizingFT;
import managedbeans.trees.IWPFunctionTree;
import managedbeans.trees.MasterDataFT;
import managedbeans.trees.ReportingFT;

import org.apache.cayenne.access.DataContext;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.ISetIdText;
import org.eclnt.jsfserver.defaultscreens.IdTextSelection;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.BaseActionEvent;
import org.eclnt.jsfserver.elements.events.BaseActionEventFlush;
import org.eclnt.jsfserver.elements.events.BaseActionEventValueHelp;
import org.eclnt.jsfserver.elements.impl.OUTLOOKBARITEMComponent;
import org.eclnt.jsfserver.util.HttpSessionAccess;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.WorkpageStartInfo;

import utils.Constants;
import utils.Helper;
import db.erp.SavedSearches;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.MainUI}")
public class MainUI extends WorkpageDispatchedPageBean implements Serializable {
	
	private List<SavedSearches> savedSearches;
	
	public void reloadSavedSearches() {
		savedSearches = SavedSearches.findByUserInclGlobal(Helper.getUserName(), getContext());
	}
	
	public void onSavedSearchAction(ActionEvent event) {
		if (event instanceof BaseActionEventFlush && ((BaseActionEventFlush) event).getFlushWasTriggeredByTimer() || event instanceof BaseActionEventValueHelp) {
			
			IdTextSelection idts = IdTextSelection.createInstance();
			for (SavedSearches savedSearch : savedSearches)
				idts.addLine(savedSearch.getEntity() + ":" + savedSearch.getId(), savedSearch.getName());
			idts.filterByInputId(savedSearch);
			idts.setRenderIdColumn(false);
			idts.setSuppressHeadline(true);
			idts.setPopupWidth(200);
			idts.setCallBack(new ISetIdText() {
				public void setIdText(String entityId, String text) {
					String[] entityIdArr = entityId.split(":");
					String entity = entityIdArr[0];
					if(entity.equals("Accounts")) entity = "partners";
					String id = entityIdArr[1];
					savedSearch = text;
					try {
						WorkpageStartInfo wpsi = (WorkpageStartInfo)BeanUtils.cloneBean(getWpsiByTransaction(entity));
						if(wpsi!=null) {
							wpsi.getParamMap().put(Constants.WP_PARAMS_SAVEDSEARCH, id);
							openWorkpage(wpsi);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} 
				}
			});
		}

	}
	
	private List<IWPFunctionTree> treeList;

	String savedSearch;

	public String getSavedSearch() {
		return savedSearch;
	}

	public void setSavedSearch(String value) {
		this.savedSearch = value;
	}

	public void onLoadSavedSearch(ActionEvent event) {
		System.out.println("Lade gesicherte Suche!");
	}

	private final static int DIVIDERLOCATION = 150;
	int dividerlocation = DIVIDERLOCATION;

	public int getDividerlocation() {
		return dividerlocation;
	}

	public void setDividerlocation(int value) {
		this.dividerlocation = value;
	}

	String transaction;

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String value) {
		this.transaction = value;
	}

	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------

	public MainUI(IWorkpageDispatcher dispatcher) throws Exception {
		super(dispatcher);
		
		// invalidate
	    Boolean invalidateCayenne = Boolean.valueOf(HttpSessionAccess.getCurrentRequest().getParameter(Constants.SESSION_INVALIDATE));
	    if (invalidateCayenne == Boolean.TRUE) {
	      invalidateCayenneCache();
	      Statusbar.outputMessageWithPopup("Daten wurden invalidiert!").setLeftTopReferenceCentered();
	    }
		
		treeList = new ArrayList<IWPFunctionTree>();		
		masterDataFT = new MasterDataFT(dispatcher);
		treeList.add(masterDataFT);
		customizingFT = new CustomizingFT(dispatcher);
		treeList.add(customizingFT);
		reportingFT = new ReportingFT(dispatcher);
		treeList.add(reportingFT);
		reloadSavedSearches();
	}

	// ------------------------------------------------------------------------
	protected int selectedNode = 1;

	private MasterDataFT masterDataFT;

	public MasterDataFT getMasterDataFT() {
		return masterDataFT;
	}

	private CustomizingFT customizingFT;

	public CustomizingFT getCustomizingFT() {
		return customizingFT;
	}
	
	private ReportingFT reportingFT;
	
	public ReportingFT getReportingFT() {
		return reportingFT;
	}

	public int getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(int value) {
		selectedNode = value;
	}

	public void onSwitchNode(ActionEvent event) {
		if (event.getSource() instanceof OUTLOOKBARITEMComponent) {
			String configInfo = ((BaseActionEvent) event).getSourceConfiginfo();
			selectedNode = Integer.valueOf(configInfo);
		}
	}

	public void onExpandCollapseSidebar(ActionEvent event) {
		String configInfo = ((BaseActionEvent) event).getSourceConfiginfo();
		if ("collapse".equals(configInfo)) {
			setDividerlocation(0);
		} else if ("expand".equals(configInfo)) {
			setDividerlocation(DIVIDERLOCATION);
		}

	}

	public void onCallTransaction(ActionEvent event) throws Exception {
		WorkpageStartInfo wpsi = getWpsiByTransaction(getTransaction());
		if(wpsi!=null) {
			openWorkpage(wpsi);
			setTransaction(StringUtils.EMPTY);
		} else {
			Statusbar.outputError(String.format(Helper.getMessage("err_trx_not_found"), getTransaction()));
		}
	}
	
	private WorkpageStartInfo getWpsiByTransaction(String transaction) {
		WorkpageStartInfo wpsi = null;
		for(IWPFunctionTree tree : treeList) {
			wpsi = tree.getWorkpageInfoById(transaction);
			if(wpsi!=null) break;
		}		
		return wpsi;
	}
	
	@SuppressWarnings("unchecked")
	private void invalidateCayenneCache() {
		DataContext context = (DataContext)getContext();	    
		context.getObjectStore().getDataRowCache().clear();
	    context.getQueryCache().clear();
	    Iterator<?> iter = context.getObjectStore().getObjectIterator();
	    while (iter.hasNext()) {
	      context.invalidateObjects(iter.next());
	    }
	  }
	
	

}
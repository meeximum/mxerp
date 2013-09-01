package managedbeans;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import managedbeans.trees.MasterDataFT;

import org.apache.commons.lang3.StringUtils;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.ISetIdText;
import org.eclnt.jsfserver.defaultscreens.IdTextSelection;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.BaseActionEvent;
import org.eclnt.jsfserver.elements.events.BaseActionEventFlush;
import org.eclnt.jsfserver.elements.events.BaseActionEventValueHelp;
import org.eclnt.jsfserver.elements.impl.OUTLOOKBARITEMComponent;
import org.eclnt.workplace.IWorkpageContainer;
import org.eclnt.workplace.IWorkpageDispatcher;
import org.eclnt.workplace.WorkpageByPageBean;
import org.eclnt.workplace.WorkpageStartInfo;

import utils.Helper;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.MainUI}")
public class MainUI extends WorkpageDispatchedPageBean implements Serializable {
	static String[] NAMES = new String[] { "Clark", "Monday", "Moretimer", "Mortadella", "Miller", "Mittermeier", "Mittenwald", "Meyer", "Smith", "Zappa" };

	public void onSavedSearchAction(ActionEvent event) {
		if (event instanceof BaseActionEventFlush && ((BaseActionEventFlush) event).getFlushWasTriggeredByTimer() || event instanceof BaseActionEventValueHelp) {
			IdTextSelection idts = IdTextSelection.createInstance();
			for (String name : NAMES)
				idts.addLine(name, name);
			idts.filterByInputId(m_savedSearch);
			idts.setRenderIdColumn(false);
			idts.setSuppressHeadline(true);
			idts.setPopupWidth(200);
			idts.setCallBack(new ISetIdText() {				
				public void setIdText(String id, String text) {
					m_savedSearch = text;
				}
			});
		}

	}

	String m_savedSearch;

	public String getSavedSearch() {
		return m_savedSearch;
	}

	public void setSavedSearch(String value) {
		this.m_savedSearch = value;
	}

	public void onLoadSavedSearch(ActionEvent event) {
		System.out.println("Lade gesicherte Suche!");
	}

	private final static int DIVIDERLOCATION = 150;
	int m_dividerlocation = DIVIDERLOCATION;

	public int getDividerlocation() {
		return m_dividerlocation;
	}

	public void setDividerlocation(int value) {
		this.m_dividerlocation = value;
	}

	String m_transaction;

	public String getTransaction() {
		return m_transaction;
	}

	public void setTransaction(String value) {
		this.m_transaction = value;
	}

	// ------------------------------------------------------------------------
	// constructors & initialization
	// ------------------------------------------------------------------------

	public MainUI(IWorkpageDispatcher dispatcher) throws Exception {
		super(dispatcher);
		masterDataFT = new MasterDataFT(dispatcher);
	}

	// ------------------------------------------------------------------------
	protected int selectedNode = 1;

	private MasterDataFT masterDataFT;

	public MasterDataFT getMasterDataFT() {
		return masterDataFT;
	}

	public void setMasterDataFT(MasterDataFT masterDataFT) {
		this.masterDataFT = masterDataFT;
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

		IWorkpageDispatcher wpd = (IWorkpageDispatcher) getOwningDispatcher().getTopOwner();
		IWorkpageContainer wpc = wpd.getWorkpageContainer();
		// TODO: Generalize for all functiontrees
		WorkpageStartInfo wpsi = getMasterDataFT().getWorkpageInfoById(getTransaction());

		if (wpsi != null) {
			wpc.addWorkpage(new WorkpageByPageBean(wpd, wpsi.getText(), wpsi));
			setTransaction(StringUtils.EMPTY);
		} else {
			Statusbar.outputError(String.format(Helper.getMessage("err_trx_not_found"), getTransaction()));
		}

	}

}
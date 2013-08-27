package managedbeans;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import managedbeans.trees.MasterDataFT;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.BaseActionEvent;
import org.eclnt.jsfserver.elements.impl.OUTLOOKBARITEMComponent;
import org.eclnt.workplace.IWorkpageDispatcher;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.MainUI}")
public class MainUI extends WorkpageDispatchedPageBean implements Serializable {
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
			String configInfo = ((BaseActionEvent)event).getSourceConfiginfo();
			selectedNode = Integer.valueOf(configInfo);
		}
	}

	

}
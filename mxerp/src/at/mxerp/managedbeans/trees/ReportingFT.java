package at.mxerp.managedbeans.trees;


import org.eclnt.jsfserver.elements.impl.FIXGRIDTreeItem;
import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.workplace.WorkpageStartInfo;
import org.eclnt.workplace.WorkplaceFunctionTree;

@SuppressWarnings("serial")
public class ReportingFT extends WorkplaceFunctionTree implements IWPFunctionTree {	
	
	  public ReportingFT(IDispatcher owner) {
	    super(owner);
	  }

	  @Override
	  protected void loadFunctionTree() {
	    // reset functiontree
	    getFtree().getRootNode().removeAllChildNodes(true);

//	    FunctionNode node = new FormattedFunctionNode(getFtree().getRootNode(), Constants.UI_COMMONS_PATH + "customize-table.jsp");
//	    node.setId("countries");
//	    node.setParam(Constants.WP_PARAMS_TABLE, "Countries");
//	    node.setPageBeanName("CustomizeTablePB");
//	    node.setOpenMultipleInstances(false);
//	    node.setText(Helper.getLiteral("countries"));
//	    node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);

	  }

	  public class FormattedFunctionNode extends FunctionNode {
	    private static final long serialVersionUID = 1L;

	    private String font = "weight:bold;size:12";

	    public String getFont() {
	      return font;
	    }

	    public void setFont(String font) {
	      this.font = font;
	    }

	    private String foreground = "#000000";

	    public String getForeground() {
	      return foreground;
	    }

	    public void setForeground(String foreground) {
	      this.foreground = foreground;
	    }

	    public FormattedFunctionNode(FIXGRIDTreeItem arg0, String arg1) {
	      super(arg0, arg1);
	    }

	    public FormattedFunctionNode(FIXGRIDTreeItem arg0) {
	      super(arg0);
	    }
	  }
	  
	  public WorkpageStartInfo getWorkpageInfoById(String id) {
		  for(FIXGRIDTreeItem item : getFtree().getRootNode().getChildNodes()) {
			  FunctionNode node = (FunctionNode)item;
			  if(node.getId().equals(id)) return node.getWorkpageStartInfo();
		  }
		  return null;
	  }

	}
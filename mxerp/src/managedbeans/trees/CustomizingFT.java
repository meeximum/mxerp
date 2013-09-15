package managedbeans.trees;


import org.eclnt.jsfserver.elements.impl.FIXGRIDTreeItem;
import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.workplace.WorkpageStartInfo;
import org.eclnt.workplace.WorkplaceFunctionTree;

import utils.Constants;
import utils.Helper;

@SuppressWarnings("serial")
public class CustomizingFT extends WorkplaceFunctionTree implements IWPFunctionTree {	
	
	  public CustomizingFT(IDispatcher owner) {
	    super(owner);
	  }

	  @Override
	  protected void loadFunctionTree() {
	    // reset functiontree
	    getFtree().getRootNode().removeAllChildNodes(true);

	    // countries
	    FunctionNode node = new FormattedFunctionNode(getFtree().getRootNode(), Constants.UI_COMMONS_PATH + "customize-table.jsp");
	    node.setId("countries");
	    node.setParam(Constants.WP_PARAMS_TABLE, "Countries");
	    node.setPageBeanName("CustomizeTablePB");
	    node.setOpenMultipleInstances(false);
	    node.setText(Helper.getLiteral("countries"));
	    node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);
	    
	    // languages
	    node = new FormattedFunctionNode(getFtree().getRootNode(), Constants.UI_COMMONS_PATH + "customize-table.jsp");
	    node.setId("languages");
	    node.setParam(Constants.WP_PARAMS_TABLE, "Languages");
	    node.setPageBeanName("CustomizeTablePB");
	    node.setOpenMultipleInstances(false);
	    node.setText(Helper.getLiteral("languages"));
	    node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);
	    
	    // groupings
	    node = new FormattedFunctionNode(getFtree().getRootNode(), Constants.UI_COMMONS_PATH + "customize-table.jsp");
	    node.setId("groupings");
	    node.setParam(Constants.WP_PARAMS_TABLE, "Groupings");
	    node.setPageBeanName("CustomizeTablePB");
	    node.setOpenMultipleInstances(false);
	    node.setText(Helper.getLiteral("groupings"));
	    node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);

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
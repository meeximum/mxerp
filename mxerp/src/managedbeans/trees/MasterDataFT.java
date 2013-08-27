package managedbeans.trees;


import org.eclnt.jsfserver.elements.impl.FIXGRIDTreeItem;
import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.workplace.WorkplaceFunctionTree;

import utils.Helper;

@SuppressWarnings("serial")
public class MasterDataFT extends WorkplaceFunctionTree {
	
	private static final String path = "/ui/master-data/";
	  public MasterDataFT(IDispatcher owner) {
	    super(owner);
	  }

	  @Override
	  protected void loadFunctionTree() {
	    // reset functiontree
	    getFtree().getRootNode().removeAllChildNodes(true);

	    // reports
	    FunctionNode node = new FormattedFunctionNode(getFtree().getRootNode(), path + "articles.jsp");
	    node.setId("articles");
	    node.setPageBeanName("ArticlesPB");
	    node.setOpenMultipleInstances(false);
	    node.setText(Helper.getLiteral("articles"));
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

	}
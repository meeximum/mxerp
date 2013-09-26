package at.mxerp.managedbeans.trees;

import org.eclnt.jsfserver.elements.impl.FIXGRIDTreeItem;
import org.eclnt.jsfserver.managedbean.IDispatcher;
import org.eclnt.workplace.WorkpageStartInfo;
import org.eclnt.workplace.WorkplaceFunctionTree;

import at.mxerp.utils.Constants;
import at.mxerp.utils.Helper;

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
		node.setParam(Constants.WP_PARAMS_OBJECT, "Countries");
		node.setPageBeanName("CustomizeTablePB");
		node.setOpenMultipleInstances(false);
		node.setText(Helper.getLiteral("countries"));
		node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);

		// languages
		node = new FormattedFunctionNode(getFtree().getRootNode(), Constants.UI_COMMONS_PATH + "customize-table.jsp");
		node.setId("languages");
		node.setParam(Constants.WP_PARAMS_OBJECT, "Languages");
		node.setPageBeanName("CustomizeTablePB");
		node.setOpenMultipleInstances(false);
		node.setText(Helper.getLiteral("languages"));
		node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);

		// groupings
		node = new FormattedFunctionNode(getFtree().getRootNode(), Constants.UI_COMMONS_PATH + "customize-table.jsp");
		node.setId("groupings");
		node.setParam(Constants.WP_PARAMS_OBJECT, "Groupings");
		node.setPageBeanName("CustomizeTablePB");
		node.setOpenMultipleInstances(false);
		node.setText(Helper.getLiteral("groupings"));
		node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);

		// partner views
		node = new FormattedFunctionNode(getFtree().getRootNode(), Constants.UI_COMMONS_PATH + "customize-table.jsp");
		node.setId("partner_views");
		node.setParam(Constants.WP_PARAMS_OBJECT, "PartnerViews");
		node.setPageBeanName("CustomizeTablePB");
		node.setOpenMultipleInstances(false);
		node.setText(Helper.getLiteral("partner_views"));
		node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);

		// numberranges
		node = new FormattedFunctionNode(getFtree().getRootNode(), Constants.UI_COMMONS_PATH + "customize-table.jsp");
		node.setId("number_ranges");
		node.setParam(Constants.WP_PARAMS_OBJECT, "NumberRanges");
		node.setPageBeanName("CustomizeTablePB");
		node.setOpenMultipleInstances(false);
		node.setText(Helper.getLiteral("number_ranges"));
		node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);

		// relations
		node = new FormattedFunctionNode(getFtree().getRootNode(), Constants.UI_COMMONS_PATH + "customize-table.jsp");
		node.setId("relations");
		node.setParam(Constants.WP_PARAMS_OBJECT, "Relations");
		node.setPageBeanName("CustomizeTablePB");
		node.setOpenMultipleInstances(false);
		node.setText(Helper.getLiteral("relations"));
		node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);

		// functions
		node = new FormattedFunctionNode(getFtree().getRootNode(), Constants.UI_COMMONS_PATH + "customize-table.jsp");
		node.setId("functions");
		node.setParam(Constants.WP_PARAMS_OBJECT, "Functions");
		node.setPageBeanName("CustomizeTablePB");
		node.setOpenMultipleInstances(false);
		node.setText(Helper.getLiteral("functions"));
		node.setStatus(FIXGRIDTreeItem.STATUS_ENDNODE);

		// departments
		node = new FormattedFunctionNode(getFtree().getRootNode(), Constants.UI_COMMONS_PATH + "customize-table.jsp");
		node.setId("departments");
		node.setParam(Constants.WP_PARAMS_OBJECT, "Departments");
		node.setPageBeanName("CustomizeTablePB");
		node.setOpenMultipleInstances(false);
		node.setText(Helper.getLiteral("departments"));
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
		for (FIXGRIDTreeItem item : getFtree().getRootNode().getChildNodes()) {
			FunctionNode node = (FunctionNode) item;
			if (node.getId().equals(id))
				return node.getWorkpageStartInfo();
		}
		return null;
	}

}
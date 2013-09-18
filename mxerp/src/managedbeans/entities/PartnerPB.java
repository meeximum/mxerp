package managedbeans.entities;

import managedbeans.commons.DetailPB;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.workplace.IWorkpageDispatcher;

import services.entities.Entity;
import services.entities.NumberRangeManager;
import utils.Constants;
import db.erp.NumberRanges;
import db.erp.PartnerViews;
import db.erp.Partners;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.PartnerPB}")
public class PartnerPB extends DetailPB {
	
	public Partners getPartner() {
		return (Partners)getData();
	}

	private String detailView;
	
	public PartnerPB(IWorkpageDispatcher workpageDispatcher) throws Exception {
		super(workpageDispatcher);
		
		if(isNewEntity()) {
			String grouping = getWorkpage().getParam(Constants.WP_PARAMS_GROUPING);
			String type = getWorkpage().getParam(Constants.WP_PARAMS_TYPE);
			getPartner().setGrouping(grouping);
			getPartner().setType(type);
		}		
		
		PartnerViews partnerView = PartnerViews.getByPartner(getContext(), getPartner());
		String view = partnerView==null?"partner.jsp":partnerView.getView();
		detailView = Constants.UI_DETAILVIEWS_PATH + view;
	}
	
	public String getPageName() {
		return detailView;
	}

	public String getRootExpressionUsedInPage() {
		return "#{d.PartnerPB}";
	}

	@Override
	protected void beforeSave() throws Exception {
		super.beforeSave();
		// set partnerno when new
		NumberRanges numberRange = NumberRangeManager.get(Entity.PARTNER, getPartner().getGrouping());
		getPartner().setPartnerNo(numberRange.increment());
		if(numberRange.isWarnLevelReached()) {
			Statusbar.outputWarningWithPopup(String.format("Achtung der Nummernkreis hat nur noch %d freie Nummern!", numberRange.getHigh()-numberRange.getActual())).setLeftTopReferenceCentered();;
		}
		
		// concatenate name
		getPartner().generateName();
	}	
		
	

}

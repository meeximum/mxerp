package managedbeans.entities;

import managedbeans.commons.DetailPB;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.workplace.IWorkpageDispatcher;

import utils.Constants;
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
		// TODO: remove logic from SearchPB
		detailView = Constants.UI_DETAILVIEWS_PATH + "partner.jsp";
	}
	
	public String getPageName() {
		return detailView;
	}

	public String getRootExpressionUsedInPage() {
		return "#{d.PartnerPB}";
	}

	@Override
	protected void beforeSave() {
		super.beforeSave();
		// set partnerno when new
		
		// concatenate name
		getPartner().generateName();
	}
	
	

}

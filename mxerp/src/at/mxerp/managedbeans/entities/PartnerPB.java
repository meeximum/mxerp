package at.mxerp.managedbeans.entities;

import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.StringUtils;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.workplace.IWorkpageDispatcher;

import at.mxerp.db.erp.Contacts;
import at.mxerp.db.erp.Groupings;
import at.mxerp.db.erp.NumberRanges;
import at.mxerp.db.erp.PartnerViews;
import at.mxerp.db.erp.Partners;
import at.mxerp.managedbeans.commons.DetailPB;
import at.mxerp.services.entities.NumberRangeManager;
import at.mxerp.utils.Constants;

@SuppressWarnings("serial")
@CCGenClass(expressionBase = "#{d.PartnerPB}")
public class PartnerPB extends DetailPB {
	
	public void onAddContact(ActionEvent event) {
		// call PopUp
		Contacts contact = getLocalContext().newObject(Contacts.class);
		getGridContacts().getItems().add(new GridContactsItem(contact));
	}
	
	private FIXGRIDListBinding<GridContactsItem> gridContacts = new FIXGRIDListBinding<GridContactsItem>();

	public FIXGRIDListBinding<GridContactsItem> getGridContacts() {
		return gridContacts ;
	}

	public void setGridContacts (FIXGRIDListBinding<GridContactsItem> value) {
		this.gridContacts  = value;
	}

	public class GridContactsItem extends FIXGRIDItem implements java.io.Serializable {
		private Contacts contact;

		private Partners partner2;
		
		public Contacts getContact() {
			return contact;
		}
		
		public Partners getPartner2() {
			return partner2;
		}

		public GridContactsItem(Contacts contact) {
			super();
			this.contact = contact;
			this.partner2 = Partners.getById(getLocalContext(), contact.getPartner2());
			System.out.println(partner2.getName());
		}		
	}
	
	private void loadContacts() {
		getGridContacts().getItems().clear();
		List<Contacts> contacts = Contacts.getByPartner1(getLocalContext(), getPartner().getId());
		for(Contacts contact : contacts) {
			getGridContacts().getItems().add(new GridContactsItem(contact));
		}
	}
	
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
		
		PartnerViews partnerView = PartnerViews.getByPartner(getLocalContext(), getPartner());
		String view = partnerView==null?"partner.jsp":partnerView.getView();
		detailView = Constants.UI_DETAILVIEWS_PATH + view;
		
		loadContacts();
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
		if(StringUtils.isBlank(getPartner().getPartnerNo())) {
			Groupings grouping = Groupings.getById(getLocalContext(), getPartner().getGrouping());
			NumberRanges numberRange = NumberRangeManager.get(grouping.getNumberRange());
			getPartner().setPartnerNo(numberRange.increment());
			if(numberRange.isWarnLevelReached()) {
				Statusbar.outputWarningWithPopup(String.format("Achtung der Nummernkreis hat nur noch %d freie Nummern!", numberRange.getHigh()-numberRange.getActual())).setLeftTopReferenceCentered();;
			}
		}
		// concatenate name
		getPartner().generateName();
	}	
		
	

}

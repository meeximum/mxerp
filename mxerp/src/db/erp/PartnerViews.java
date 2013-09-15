package db.erp;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.SelectQuery;



@SuppressWarnings("serial")
public class PartnerViews extends _PartnerViews { 
	@SuppressWarnings("unchecked")
	public static List<PartnerViews> getAll(ObjectContext ctxt) {
		SelectQuery<PartnerViews> query = new SelectQuery<PartnerViews>(PartnerViews.class);
		return ctxt.performQuery(query);
	}
}

package at.mxerp.db.erp;

import java.util.List;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;


@SuppressWarnings("serial")
public class PartnerViews extends _PartnerViews implements ICustomizing { 
			
	public PartnerViews() {
		super();
		setLocked(false);
	}

	@SuppressWarnings("unchecked")
	public static List<PartnerViews> getAll(ObjectContext ctxt) {
		SelectQuery<PartnerViews> query = new SelectQuery<PartnerViews>(PartnerViews.class);
		return ctxt.performQuery(query);
	}
	
	public static PartnerViews getByPartner(ObjectContext ctxt, Partners partner) {
		Expression expression = PartnerViews.GROUPING.eq(partner.getGrouping());
		expression = expression.andExp(PartnerViews.TYPE.eq(partner.getType()));
		SelectQuery<PartnerViews> query = new SelectQuery<PartnerViews>(PartnerViews.class, expression);
		return (PartnerViews) Cayenne.objectForQuery(ctxt, query);
	}
}

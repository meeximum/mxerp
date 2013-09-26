package at.mxerp.db.erp;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;
import org.apache.commons.collections.CollectionUtils;


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
	
	@SuppressWarnings("unchecked")
	public static PartnerViews getByPartner(ObjectContext ctxt, Partners partner) {
		Expression expression = PartnerViews.GROUPING.eq(partner.getGrouping());
		expression = expression.andExp(PartnerViews.TYPE.eq(partner.getType()));
		SelectQuery<PartnerViews> query = new SelectQuery<PartnerViews>(PartnerViews.class, expression);
		List<PartnerViews> result = ctxt.performQuery(query);
		if(CollectionUtils.isEmpty(result)) return null;
		return result.get(0);
	}
}

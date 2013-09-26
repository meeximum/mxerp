package at.mxerp.db.erp;

import java.util.Date;
import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;

import at.mxerp.utils.Helper;



@SuppressWarnings("serial")
public class Contacts extends _Contacts {
	public Contacts() {
		super();
		setValidFrom(Helper.getMinDate());	
		setValidTo(Helper.getMaxDate());		
		setCreatedAt(new Date());
		setDeleted(false);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Contacts> getByPartner1(ObjectContext ctxt, String partner1) {
		Date now = new Date();
		Expression expression = Contacts.PARTNER1.eq(partner1);
		expression = expression.andExp(Contacts.DELETED.eq(false));
		expression = expression.andExp(Contacts.VALID_FROM.lte(now));
		expression = expression.andExp(Contacts.VALID_TO.gte(now));
		SelectQuery<Contacts> query = new SelectQuery<Contacts>(Contacts.class, expression);
		List<Contacts> result = ctxt.performQuery(query);
		return result;	
	}
}

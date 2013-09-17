package db.erp;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;



@SuppressWarnings("serial")
public class Domains extends _Domains {
	@SuppressWarnings("unchecked")
	public static List<Domains> getById(ObjectContext ctxt, String id) {
		Expression expression = Domains.ID.eq(id);
		SelectQuery<Domains> query = new SelectQuery<Domains>(Domains.class, expression);
		return ctxt.performQuery(query);
	}
}

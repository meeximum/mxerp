package at.mxerp.db.erp;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;



@SuppressWarnings("serial")
public class GroupingsV extends _GroupingsV {
	@SuppressWarnings("unchecked")
	public static List<GroupingsV> getActive(ObjectContext ctxt, String language) {
		Expression expression = GroupingsV.ACTIVE.eq(true);
		expression = expression.andExp(GroupingsV.LANGUAGE.eq(language));
		SelectQuery<GroupingsV> query = new SelectQuery<GroupingsV>(GroupingsV.class, expression);
		return ctxt.performQuery(query);
	}
}

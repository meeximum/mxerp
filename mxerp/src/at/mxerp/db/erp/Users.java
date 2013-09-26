package at.mxerp.db.erp;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;
import org.apache.commons.collections.CollectionUtils;



@SuppressWarnings("serial")
public class Users extends _Users {
	@SuppressWarnings("unchecked")
	public static Users getByUser(ObjectContext ctxt, String user) {
		Expression expression = Users.USER.eq(user);
		SelectQuery<Users> query = new SelectQuery<Users>(Users.class, expression);
		List<Users> result = ctxt.performQuery(query);
		if(CollectionUtils.isEmpty(result)) return null;
		return result.get(0);
	}
	
	public boolean checkPassword(String password) {
		return password.equals(getPassword());
	}
}

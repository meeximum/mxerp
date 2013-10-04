package at.mxerp.db.erp;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;



@SuppressWarnings("serial")
public class Users extends _Users {
	public static Users getByUser(ObjectContext ctxt, String user) {
		Expression expression = Users.USER.eq(user);
		SelectQuery<Users> query = new SelectQuery<Users>(Users.class, expression);
		return (Users) Cayenne.objectForQuery(ctxt, query);
	}
	
	public boolean checkPassword(String password) {
		return password.equals(getPassword());
	}
}

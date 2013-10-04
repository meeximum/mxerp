package at.mxerp.db.erp;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;
import org.apache.commons.lang3.StringUtils;

import at.mxerp.services.entities.Entity;



@SuppressWarnings("serial")
public class UserPresets extends _UserPresets {
	@SuppressWarnings("unchecked")
	public static List<UserPresets> getByUserAndEntity(ObjectContext ctxt, String user, Entity entity) {
		Expression expression = UserPresets.USER.eq(user);
		expression = expression.andExp(UserPresets.OBJECT.eq(entity.name()));
		SelectQuery<UserPresets> query = new SelectQuery<UserPresets>(UserPresets.class, expression);
		return ctxt.performQuery(query);
	}
	
	@SuppressWarnings("unchecked")
	public static List<UserPresets> getByUser(ObjectContext ctxt, String user) {
		Expression expression = UserPresets.USER.eq(user);
		SelectQuery<UserPresets> query = new SelectQuery<UserPresets>(UserPresets.class, expression);
		query.addOrdering(UserPresets.OBJECT.asc());
		query.addOrdering(UserPresets.FIELD.asc());
		return ctxt.performQuery(query);
	}
	
	public boolean checkMandatory() {
		if(StringUtils.isEmpty(getObject()) || StringUtils.isEmpty(getField()) || StringUtils.isEmpty(getValue())) return false;
		return true;
	}
}

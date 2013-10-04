package at.mxerp.db.erp;

import java.util.List;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;
import org.apache.commons.lang3.StringUtils;

import at.mxerp.services.entities.Entity;



@SuppressWarnings("serial")
public class SavedSearches extends _SavedSearches {
	
	
	
	public SavedSearches() {
		super();
		setGlobal(false);
		setDefault(false);
	}

	public static List<SavedSearches> findByUserInclGlobal(String user, ObjectContext context) {
		Expression expression = SavedSearches.USER.eq(user);
		expression = expression.orExp(SavedSearches.GLOBAL.eq(true));
		SelectQuery<SavedSearches> query = new SelectQuery<SavedSearches>(SavedSearches.class, expression);
		return context.select(query);
	}
	
	public static List<SavedSearches> findByUserExclGlobal(String user, ObjectContext context) {
		Expression expression = SavedSearches.USER.eq(user);
		SelectQuery<SavedSearches> query = new SelectQuery<SavedSearches>(SavedSearches.class, expression);
		return context.select(query);
	}
	
	public static SavedSearches findById(String id, ObjectContext context) {
		Expression expression = SavedSearches.ID.eq(id);
		SelectQuery<SavedSearches> query = new SelectQuery<SavedSearches>(SavedSearches.class, expression);
		return (SavedSearches)Cayenne.objectForQuery(context, query);	
	}
	
	public static List<SavedSearches> findByUserAndName(String user, String name, ObjectContext context) {
		Expression expression = SavedSearches.USER.eq(user);
		expression = expression.andExp(SavedSearches.NAME.eq(name));
		SelectQuery<SavedSearches> query = new SelectQuery<SavedSearches>(SavedSearches.class, expression);
		return context.select(query);
	}
	
	public static SavedSearches findByUserAndEntityAndDefault(String user, Entity entity, ObjectContext context) {
		Expression expression = SavedSearches.USER.eq(user);
		expression = expression.andExp(SavedSearches.ENTITY.eq(entity.name()));
		expression = expression.andExp(SavedSearches.DEFAULT.eq(true));		
		SelectQuery<SavedSearches> query = new SelectQuery<SavedSearches>(SavedSearches.class, expression);
		List<SavedSearches> result = context.select(query);
		if(result==null || result.size()<1) return null;
		return result.get(0);
	}
	
	public boolean checkMandatory() {
		if(StringUtils.isEmpty(getEntity()) || StringUtils.isEmpty(getName())) return false;
		return true;
	}

}

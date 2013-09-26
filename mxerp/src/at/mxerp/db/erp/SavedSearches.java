package at.mxerp.db.erp;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;
import org.apache.commons.collections.CollectionUtils;



@SuppressWarnings("serial")
public class SavedSearches extends _SavedSearches {
	
	public static List<SavedSearches> findByUserInclGlobal(String user, ObjectContext context) {
		Expression expression = SavedSearches.USER.eq(user);
		expression = expression.orExp(SavedSearches.GLOBAL.eq(true));
		SelectQuery<SavedSearches> query = new SelectQuery<SavedSearches>(SavedSearches.class, expression);
		return context.select(query);
	}
	
	public static SavedSearches findById(String id, ObjectContext context) {
		Expression expression = SavedSearches.ID.eq(id);
		SelectQuery<SavedSearches> query = new SelectQuery<SavedSearches>(SavedSearches.class, expression);
		List<SavedSearches> savedSearches = context.select(query);
		return CollectionUtils.isEmpty(savedSearches)?null:savedSearches.get(0);		
	}
	
	public static List<SavedSearches> findByUserAndName(String user, String name, ObjectContext context) {
		Expression expression = SavedSearches.USER.eq(user);
		expression = expression.andExp(SavedSearches.NAME.eq(name));
		SelectQuery<SavedSearches> query = new SelectQuery<SavedSearches>(SavedSearches.class, expression);
		return context.select(query);
	}

}

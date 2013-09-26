package at.mxerp.db.erp;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;
import org.apache.commons.collections.CollectionUtils;




@SuppressWarnings("serial")
public class Groupings extends _Groupings implements ICustomizing {

	public Groupings() {
		super();
		setLocked(false);
		setPersons(true);
		setOrganizations(true);
	}
	
	@SuppressWarnings("unchecked")
	public static Groupings getById(ObjectContext ctxt, String id) {
		Expression expression = Groupings.ID.eq(id);
		SelectQuery<Groupings> query = new SelectQuery<Groupings>(Groupings.class, expression);
		List<Groupings> result = ctxt.performQuery(query);
		if(CollectionUtils.isEmpty(result)) return null;
		return result.get(0);
	}

}

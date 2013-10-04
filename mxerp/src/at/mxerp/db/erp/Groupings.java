package at.mxerp.db.erp;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;




@SuppressWarnings("serial")
public class Groupings extends _Groupings implements ICustomizing {

	public Groupings() {
		super();
		setLocked(false);
		setPersons(true);
		setOrganizations(true);
	}
	
	public static Groupings getById(ObjectContext ctxt, String id) {
		Expression expression = Groupings.ID.eq(id);
		SelectQuery<Groupings> query = new SelectQuery<Groupings>(Groupings.class, expression);
		return (Groupings) Cayenne.objectForQuery(ctxt, query);
	}

}

package db.erp;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;



@SuppressWarnings("serial")
public class Groupings extends _Groupings implements ICustomizing {

	public Groupings() {
		super();
		setLocked(false);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Groupings> getActive(ObjectContext ctxt) {
		Expression expression = Groupings.ACTIVE.eq(true);
		SelectQuery<Groupings> query = new SelectQuery<Groupings>(Groupings.class, expression);
		return ctxt.performQuery(query);
	}

}

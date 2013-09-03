package db;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;



@SuppressWarnings("serial")
public class Metadata extends _Metadata {
	@SuppressWarnings("unchecked")
	public static List<Metadata> getByEntity(ObjectContext ctxt, String entity) {
		Expression expression = Metadata.ENTITY.eq(entity);
		SelectQuery<Metadata> query = new SelectQuery<Metadata>(Metadata.class, expression);
		return ctxt.performQuery(query);
	}
}

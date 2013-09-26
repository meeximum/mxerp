package at.mxerp.db.erp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;

@SuppressWarnings("serial")
public class Metadata extends _Metadata {
	@SuppressWarnings("unchecked")
	public static List<Metadata> getByObject(ObjectContext ctxt, String object) {
		Expression expression = Metadata.OBJECT.eq(object);
		SelectQuery<Metadata> query = new SelectQuery<Metadata>(Metadata.class, expression);
		return ctxt.performQuery(query);
	}

	public static Map<String, Metadata> getByObjectAsMap(ObjectContext ctxt, String object) {
		HashMap<String, Metadata> metadataMap = new HashMap<String, Metadata>();
		for (Metadata metadate : getByObject(ctxt, object)) {
			metadataMap.put(metadate.getField(), metadate);
		}
		return metadataMap;
	}
}

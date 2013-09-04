package db.erp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public static Map<String, Metadata> getByEntityAsMap(ObjectContext ctxt, String entity) {		
		HashMap<String, Metadata> metadataMap = new HashMap<String, Metadata>();
		for (Metadata metadate : getByEntity(ctxt, entity)) {
			metadataMap.put(metadate.getField(), metadate);
		}
		return metadataMap;
	}
	
}

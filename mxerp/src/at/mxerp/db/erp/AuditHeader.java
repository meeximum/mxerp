package at.mxerp.db.erp;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;



@SuppressWarnings("serial")
public class AuditHeader extends _AuditHeader {
	
	@SuppressWarnings("unchecked")
	public static List<AuditHeader> getByTableName(ObjectContext ctxt, String tableName) {
		Expression expression = AuditHeader.TABLE_NAME.eq(tableName);
		SelectQuery<AuditHeader> query = new SelectQuery<AuditHeader>(AuditHeader.class, expression);
		query.addOrdering(AuditHeader.CREATED_AT.desc());
		return ctxt.performQuery(query);
	}
}

package at.mxerp.db.erp;

import org.apache.cayenne.Cayenne;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;

import at.mxerp.services.entities.IEntity;



@SuppressWarnings("serial")
public class Partners extends _Partners implements IEntity {

	@Override
	public String toString() {
		return getPartnerNo() + " " + getName();
	}
	
	public void generateName() {
		String name;
		if("P".equals(getType())) {
			name = (getNameFirst()==null?"":getNameFirst()) + " " + (getNameLast()==null?"":getNameLast()); 			
		} else {
			name = (getNameOrg1()==null?"":getNameOrg1())  + " " + (getNameOrg2()==null?"":getNameOrg2());
		}
		setName(name.trim());
	}

	public static Partners getById(ObjectContext ctxt, String id) {		
		Expression expression = IEntity.ID.eq(id);
		SelectQuery<Partners> query = new SelectQuery<Partners>(Partners.class, expression);
		return (Partners) Cayenne.objectForQuery(ctxt, query);
	}
	
	@Override
	protected void onPreUpdate() {
	
	}

}

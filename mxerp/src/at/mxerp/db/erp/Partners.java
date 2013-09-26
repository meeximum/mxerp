package at.mxerp.db.erp;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;
import org.apache.commons.collections.CollectionUtils;

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

	@SuppressWarnings("unchecked")
	public static Partners getById(ObjectContext ctxt, String id) {		
		Expression expression = IEntity.ID.eq(id);
		SelectQuery<Partners> query = new SelectQuery<Partners>(Partners.class, expression);
		List<Partners> result = ctxt.performQuery(query);
		if(CollectionUtils.isEmpty(result)) return null;
		return result.get(0);
	}
	
	@Override
	protected void onPreUpdate() {
	
	}

}

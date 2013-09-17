package db.erp;

import services.entities.IEntity;



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

}

package db.erp;

import services.entities.IEntity;
import db.enums.PartnerType;



@SuppressWarnings("serial")
public class Partners extends _Partners implements IEntity {

	@Override
	public String toString() {
		return getPartnerNo() + " " + getName();
	}
	
	public void generateName() {
		String name;
		if(PartnerType.ORGANISATION.equals(getType())) {
			name = (getNameOrg1()==null?"":getNameOrg1())  + " " + (getNameOrg2()==null?"":getNameOrg2());
		} else {
			name = (getNameFirst()==null?"":getNameFirst()) + " " + (getNameLast()==null?"":getNameLast()); 
		}
		setName(name.trim());
	}

}

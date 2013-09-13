package db.erp;

import services.entities.IEntity;



@SuppressWarnings("serial")
public class Partners extends _Partners implements IEntity {

	@Override
	public String toString() {
		return getPartnerNo() + " " + getName();
	}

}

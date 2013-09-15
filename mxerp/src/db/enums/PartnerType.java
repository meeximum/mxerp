package db.enums;

import org.apache.cayenne.ExtendedEnumeration;

public enum PartnerType implements ExtendedEnumeration {
	ORGANISATION(1), PERSON(2);

	private Integer type;
	
	private PartnerType(Integer type) {
		this.type = type;
	}
	
	@Override
	public Object getDatabaseValue() {
		return type;
	}

}

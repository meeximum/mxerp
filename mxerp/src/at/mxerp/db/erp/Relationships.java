package at.mxerp.db.erp;

import java.util.Date;

import at.mxerp.utils.Helper;



@SuppressWarnings("serial")
public class Relationships extends _Relationships {

	public Relationships() {
		super();
		setValidFrom(Helper.getMinDate());	
		setValidTo(Helper.getMaxDate());		
		setCreatedAt(new Date());
		setDeleted(false);
	}

}

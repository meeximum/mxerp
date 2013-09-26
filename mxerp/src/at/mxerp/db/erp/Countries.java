package at.mxerp.db.erp;

import at.mxerp.services.auditing.Auditing;


@Auditing
@SuppressWarnings("serial")
public class Countries extends _Countries implements ICustomizing {

	@Auditing
	@Override
	public String getCurrency() {
		// TODO Auto-generated method stub
		return super.getCurrency();
	}

	public Countries() {
		super();
		setLocked(false);
	}
	
	

}

package at.mxerp.db.erp;

import at.mxerp.services.vvb.IVvb;
import at.mxerp.utils.Helper;



@SuppressWarnings("serial")
public class RelationsT extends _RelationsT implements IVvb {

	public RelationsT() {
		super();
		setLanguage(Helper.getLanguageServer());
	}

}

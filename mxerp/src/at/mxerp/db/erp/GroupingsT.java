package at.mxerp.db.erp;

import at.mxerp.services.vvb.IVvb;
import at.mxerp.utils.Helper;



@SuppressWarnings("serial")
public class GroupingsT extends _GroupingsT implements IVvb {
	public GroupingsT() {
		super();
		setLanguage(Helper.getLanguageServer());
	}
}

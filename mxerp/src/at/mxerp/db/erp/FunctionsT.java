package at.mxerp.db.erp;

import at.mxerp.services.vvb.IVvb;
import at.mxerp.utils.Helper;



@SuppressWarnings("serial")
public class FunctionsT extends _FunctionsT implements IVvb {
	public FunctionsT() {
		super();
		setLanguage(Helper.getLanguageServer());
	}
}

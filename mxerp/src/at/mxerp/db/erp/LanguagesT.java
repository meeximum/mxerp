package at.mxerp.db.erp;

import at.mxerp.services.vvb.IVvb;
import at.mxerp.utils.Helper;



@SuppressWarnings("serial")
public class LanguagesT extends _LanguagesT implements IVvb {
	public LanguagesT() {
		super();
		setLanguage(Helper.getLanguageServer());
	}
}

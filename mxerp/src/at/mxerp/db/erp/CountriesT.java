package at.mxerp.db.erp;

import at.mxerp.services.vvb.IVvb;
import at.mxerp.utils.Helper;


@SuppressWarnings("serial")
public class CountriesT extends _CountriesT implements IVvb {
	public CountriesT() {
		super();
		setLanguage(Helper.getLanguageServer());
	}
}

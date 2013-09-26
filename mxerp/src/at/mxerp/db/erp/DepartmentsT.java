package at.mxerp.db.erp;

import at.mxerp.services.vvb.IVvb;
import at.mxerp.utils.Helper;



@SuppressWarnings("serial")
public class DepartmentsT extends _DepartmentsT implements IVvb {
	public DepartmentsT() {
		super();
		setLanguage(Helper.getLanguageServer());
	}
}

package db.erp;




@SuppressWarnings("serial")
public class Groupings extends _Groupings implements ICustomizing {

	public Groupings() {
		super();
		setLocked(false);
		setPersons(true);
		setOrganizations(true);
	}

}

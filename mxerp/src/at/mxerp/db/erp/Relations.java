package at.mxerp.db.erp;



@SuppressWarnings("serial")
public class Relations extends _Relations implements ICustomizing {

	public Relations() {
		super();
		setDirected(false);
		setLocked(false);
		setType(1);
	}

}

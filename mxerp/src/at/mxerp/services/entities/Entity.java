package at.mxerp.services.entities;

public enum Entity {
	PARTNER("Partners");
	
	private String objName;
	public String getObjName() {
		return objName;
	}
	
	private Entity(String objName) {
		this.objName = objName;
	}
}

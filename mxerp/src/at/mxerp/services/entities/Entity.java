package at.mxerp.services.entities;

import org.apache.cayenne.CayenneDataObject;

import at.mxerp.db.erp.Partners;

public enum Entity {
	PARTNER("Partners","PartnerPB", Partners.class);
	
	private String objName;
	public String getObjName() {
		return objName;
	}
	
	private String detailPB;	
	public String getDetailPB() {
		return detailPB;
	}
	
	private Class<? extends CayenneDataObject> cdo;
	public Class<? extends CayenneDataObject> getCdo() {
		return cdo;
	}

	private Entity(String objName, String detailPB, Class<? extends CayenneDataObject> cdo) {
		this.objName = objName;
		this.detailPB = detailPB;
		this.cdo = cdo;
	}
}

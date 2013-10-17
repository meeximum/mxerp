package at.mxerp.services.entities;

import java.util.Date;

import org.apache.cayenne.exp.Property;

public interface IEntity {
	public static final Property<String> ID = new Property<String>("id");
	public static final Property<Boolean> DELETED = new Property<Boolean>("deleted");

	public String getId();	
	public void setId(String id);
	
	public void setChangedAt(Date changedAt);
    public Date getChangedAt();

    public void setChangedBy(String changedBy);
    public String getChangedBy();

    public void setCreatedAt(Date createdAt);
    public Date getCreatedAt();

    public void setCreatedBy(String createdBy);
    public String getCreatedBy();

    public void setDeleted(Boolean deleted);
    public Boolean getDeleted();	
    
    public String getEntityDescription();
   
}

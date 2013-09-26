package at.mxerp.services.vvb;

import org.apache.cayenne.exp.Property;

public interface IVvb {
	public static final Property<String> ID = new Property<String>("id");

	public static final Property<String> LANGUAGE = new Property<String>("language");

	public String getId();

	public String getDescription();

	public String getLanguage();

	public void setId(String id);

	public void setDescription(String description);

	public void setLanguage(String language);
}

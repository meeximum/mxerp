package services.vvb;

import org.apache.cayenne.exp.Property;


public interface IVvb {
	
	public static final Property<String> LANGUAGE = new Property<String>("language");
	
	public String getId();
	
	public String getDescription();
	
	public String getLanguage();
}

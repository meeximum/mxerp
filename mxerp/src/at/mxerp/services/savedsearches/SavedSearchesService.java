package at.mxerp.services.savedsearches;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.PersistenceState;

import at.mxerp.db.erp.SavedSearches;


public class SavedSearchesService {
//	public static String marshall(SavedSearch savedSearch) throws Exception {
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();    
//	    JAXBContext context = JAXBContext.newInstance(SavedSearch.class);
//	    Marshaller m = context.createMarshaller();
//	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
//	    m.setProperty(Marshaller.JAXB_ENCODING, Constants.UTF8);
//	    m.marshal(savedSearch, baos);   
//	    return baos==null?StringUtils.EMPTY:baos.toString(Constants.UTF8);	    
//	}
//	
//	public static SavedSearch unmarshall(String xml) throws Exception {
//		Unmarshaller um = JAXBService.createUnmarshaller(SavedSearch.class);
//	    return (SavedSearch) um.unmarshal(new ByteArrayInputStream(xml.getBytes(Constants.UTF8)));
//	}
	public static void save(SavedSearches savedSearches, ObjectContext context) {		
		if(savedSearches.getPersistenceState()==PersistenceState.TRANSIENT) {
			context.registerNewObject(savedSearches);	
		}					
		context.commitChanges();
	}
}

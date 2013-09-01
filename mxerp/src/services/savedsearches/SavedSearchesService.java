package services.savedsearches;

import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang3.StringUtils;

import utils.Constants;

public class SavedSearchesService {
	public void save(SavedSearch savedSearch) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();    
	    JAXBContext context = JAXBContext.newInstance(SavedSearch.class);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	    m.setProperty(Marshaller.JAXB_ENCODING, Constants.UTF8);
	    m.marshal(savedSearch, baos);   
	    String content = baos==null?null:baos.toString(Constants.UTF8);
	    if(StringUtils.isEmpty(content)) return;
	}
}

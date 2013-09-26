package at.mxerp.services.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;

import at.mxerp.utils.Constants;

public class JAXBService {
	private static Marshaller createMarshaller(Class<?> clazz) throws Exception{
		 JAXBContext context = JAXBContext.newInstance(clazz);
		 Marshaller m = context.createMarshaller();
		 m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		 m.setProperty(Marshaller.JAXB_ENCODING, Constants.UTF8);
		 return m;
	}
	
	private static Unmarshaller createUnmarshaller(Class<?> clazz) throws Exception{
		 JAXBContext context = JAXBContext.newInstance(clazz);
		 Unmarshaller um = context.createUnmarshaller();
		 return um;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T unmarshall(String xml, Class<T> clazz) throws Exception {
		Unmarshaller um = createUnmarshaller(clazz);
		return ((T)um.unmarshal(new ByteArrayInputStream(xml.getBytes(Constants.UTF8))));
	}
	
	public static <T> String marshall(T entity, Class<T> clazz) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		Marshaller m = createMarshaller(clazz);
	    m.marshal(entity, baos);   
	    return baos==null?StringUtils.EMPTY:baos.toString(Constants.UTF8);
	}
}

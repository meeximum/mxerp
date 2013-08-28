package services.variants;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.streamstore.StreamStore;


public class VariantsService {
  protected final static Log logger = LogFactory.getLog(VariantsService.class);
  private final static String VARIANTS_DIR = "variants";
  private final static String DIR_SEPARATOR = "/";
  private final static String FILE_ENDING = ".xml";
  private final static String UTF8 = "UTF-8";
  
  public static List<Variant> getVariants(String user, String object) {
    List<Variant> variants = new ArrayList<Variant>();
    String path = buildPath(user, object);
    List<String> streams = StreamStore.getInstance().getContainedStreams(path, false);
    for(String stream : streams) {
      Variant variant;
      try {
        variant = loadVariant(user, object, stream);
        variants.add(variant);
      } catch (Exception e) {
        logger.error("Problem loading Variant: " + stream , e);
      }      
    }
    return variants;
  }
  
  public static void saveVariant(String user, String object, Variant variant) throws Exception {
    String path = buildPathAndCheckFileEnding(user, object, variant.getName());
    ByteArrayOutputStream baos = new ByteArrayOutputStream();    
    JAXBContext context = JAXBContext.newInstance(Variant.class);
    Marshaller m = context.createMarshaller();
    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    m.setProperty(Marshaller.JAXB_ENCODING, UTF8);
    m.marshal(variant, baos);   
    String content = baos==null?null:baos.toString(UTF8);
    if(StringUtils.isEmpty(content)) return;
    //if(StreamStore.getInstance().checkIfStreamExists(path, false)) StreamStore.getInstance().removeStream(path, false);    
    StreamStore.getInstance().writeUTF8(path, content, true);
  }
  
  public static void deleteVariant(String user, String object, Variant variant) throws Exception {
    String path = buildPathAndCheckFileEnding(user, object, variant.getName());
    if(StreamStore.getInstance().checkIfStreamExists(path, false)) StreamStore.getInstance().removeStream(path, false);  
  }

  private static String buildPathAndCheckFileEnding(String user, String object, String name) throws Exception {
    StringBuilder sb = new StringBuilder(buildPath(user, object) + DIR_SEPARATOR + name);
    if(!name.toLowerCase().endsWith(FILE_ENDING)) sb.append(FILE_ENDING);
    return sb.toString().toLowerCase();
  }
  
  private static String buildPath(String user, String object) {
    String path = VARIANTS_DIR + DIR_SEPARATOR + user + DIR_SEPARATOR + object;
    return path.toLowerCase();
  }
  
  public static Variant loadVariant(String user, String object, String name) throws Exception {
    String path = buildPathAndCheckFileEnding(user, object, name);
    if(!StreamStore.getInstance().checkIfStreamExists(path, false)) return null;
    String content = StreamStore.getInstance().readUTF8(path, false);
    JAXBContext context = JAXBContext.newInstance(Variant.class);
    Unmarshaller um = context.createUnmarshaller();
    Variant variant = (Variant) um.unmarshal(new ByteArrayInputStream(content.getBytes(UTF8)));
    return variant;
  }
  
  public static boolean checkIfVariantExists(String user, String object, String name) throws Exception {
    String path = buildPathAndCheckFileEnding(user, object, name);
    return StreamStore.getInstance().checkIfStreamExists(path, false);
  }
}

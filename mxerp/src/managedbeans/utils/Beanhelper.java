package managedbeans.utils;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.eclnt.jsfserver.util.StyleManager;

import utils.ConfigXML;
import utils.Helper;

/**
 * Helper class e.g. for general VVB bindings
 * 
 * @author reich
 * 
 */
public class Beanhelper implements Serializable {

  private static final long serialVersionUID = 1L;

  public String getVersion() {
    return "Version " + ConfigXML.getVersion();
  }
  
  public String getUser() {
	  return Helper.getUserName();
  }

  public String getLanuageServer() {
    return Helper.getLanguageServer();
  }

  // -----------------------------------------------
  // ValidValuesBindings
  // -----------------------------------------------


  // -----------------------------------------------
  // Styles Manager
  // -----------------------------------------------
  private StylesMap stylesMap = new StylesMap();

  public StylesMap getStylesMap() {
    return stylesMap;
  }

  private static class StylesMap extends HashMap<String, String> {
    private static final long serialVersionUID = 1L;

    @Override
    public String get(Object key) {
      if (key == null || (key instanceof String) == false)
        return StringUtils.EMPTY;
      String keyAsString = (String) key;
      String[] variantTagAttribute = keyAsString.split("#");
      if (variantTagAttribute.length != 3)
        return StringUtils.EMPTY;
      String variantName = variantTagAttribute[0];
      String tagNameWithPrefix = variantTagAttribute[1];
      String attributeName = variantTagAttribute[2];
      String value = StyleManager.getTagAttributeValue(variantName, tagNameWithPrefix, attributeName);
      return value;
    }
  }
}

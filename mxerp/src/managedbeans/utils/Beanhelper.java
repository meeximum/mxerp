package managedbeans.utils;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.cayenne.BaseContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.jsfserver.util.StyleManager;

import services.vvb.VvbService;
import utils.ConfigXML;
import utils.Helper;

/**
 * Helper class e.g. for general VVB bindings
 * 
 * @author reich
 * 
 */
@SuppressWarnings("serial")
public class Beanhelper implements Serializable {

	private static final Log logger = LogFactory.getLog(Beanhelper.class);

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

	// -----------------------------------------------
	// VVB Manager
	// -----------------------------------------------
	private VvbsMap vvb = new VvbsMap();

	public VvbsMap getVvb() {
		return vvb;
	}


	private static class VvbsMap extends HashMap<String, ValidValuesBinding> {

		@Override
		public ValidValuesBinding get(Object key) {
			ValidValuesBinding vvb = new ValidValuesBinding();
			if (key == null || (key instanceof String) == false)
				return vvb;

			try {
				vvb = VvbService.getVvb((String) key, BaseContext.getThreadObjectContext(), Helper.getLanguageServer());
			} catch (Exception ex) {
				logger.error(ex, ex);
			}

			return vvb;
		}
	}
}

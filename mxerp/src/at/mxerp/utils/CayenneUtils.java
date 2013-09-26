package at.mxerp.utils;

import org.apache.cayenne.DataChannel;
import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.configuration.web.WebUtil;
import org.eclnt.jsfserver.util.HttpSessionAccess;

public class CayenneUtils {
	public static ObjectContext createNewContext() {
	    return WebUtil.getCayenneRuntime(HttpSessionAccess.getServletContext()).newContext();
	  }
	  
	  public static ObjectContext createChildContext(ObjectContext context) {
	    return WebUtil.getCayenneRuntime(HttpSessionAccess.getServletContext()).newContext((DataChannel)context);
	  }
}

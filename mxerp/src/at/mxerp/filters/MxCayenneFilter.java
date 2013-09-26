package at.mxerp.filters;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

import org.apache.cayenne.configuration.CayenneRuntime;
import org.apache.cayenne.configuration.web.CayenneFilter;
import org.apache.cayenne.configuration.web.WebUtil;

import at.mxerp.services.auditing.AuditListener;

public class MxCayenneFilter extends CayenneFilter {
	 @Override
	  public void init(FilterConfig config) throws ServletException {
	    super.init(config);
	    CayenneRuntime runtime = WebUtil.getCayenneRuntime(config.getServletContext());
	    AuditListener listener = new AuditListener(runtime);
	    runtime.getChannel().getEntityResolver().getCallbackRegistry().addListener(listener);
	  }
}

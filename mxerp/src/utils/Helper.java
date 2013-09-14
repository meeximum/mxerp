package utils;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.eclnt.jsfserver.resources.ResourceManager;
import org.eclnt.jsfserver.util.HttpSessionAccess;
import org.eclnt.jsfserver.util.useraccess.UserAccessMgr;

public class Helper {

	public static <T> T getValueFromExpression(String expression, Class<T> clazz) {
		return FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{data}", clazz);
	}

	public static String getLiteral(String property) {
		String value = ResourceManager.getRuntimeInstance().readProperty(Constants.LITERALS, property);
		return StringUtils.isEmpty(value) ? property : value;
	}

	public static String getColumnNameForEntity(String entity, String column) {
		String value = ResourceManager.getRuntimeInstance().readProperty("_" + entity.toLowerCase(), column.toLowerCase());
		return StringUtils.isEmpty(value) ? column : value;
	}

	public static String getMessage(String property) {
		String value = ResourceManager.getRuntimeInstance().readProperty(Constants.MESSAGES, property);
		return StringUtils.isEmpty(value) ? property : value;
	}

	public static boolean isFileInWebRoot(String filename) {
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext sc = (ServletContext) context.getExternalContext().getContext();
		String path = sc.getRealPath(filename);
		File file = new File(path);
		return file.exists();
	}

	public static String getRealPath(String fileName) throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		ServletContext sc = (ServletContext) context.getExternalContext().getContext();
		return sc.getRealPath(fileName);
	}

	public static String getStackTraceAsString(Throwable throwable) {
		Writer writer = new StringWriter();
		PrintWriter printWriter = new PrintWriter(writer);
		throwable.printStackTrace(printWriter);
		return writer.toString();
	}

	public static String getLanguageServer() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		return locale.getLanguage();
	}

	public static void setLanguageServer(Locale locale) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	public static String getUserNameOS() {
		return HttpSessionAccess.getCurrentRequest().getHeader(Constants.ECLNT_USERNAME);
	}

	public static String getUserName() {
		return UserAccessMgr.getCurrentUser();
	}

	public static String getContextParameter(String parameter) {
		try {
			return FacesContext.getCurrentInstance().getExternalContext().getInitParameter(parameter);
		} catch (Exception ex) {
			return null;
		}
	}

	public static InputStream getResourceAsStream(String resource) {
		String stripped = resource.startsWith("/") ? resource.substring(1) : resource;

		InputStream stream = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader != null) {
			stream = classLoader.getResourceAsStream(stripped);
		}
		return stream;
	}
	
	public static String getClientIP() {
		return HttpSessionAccess.getCurrentRequest().getHeader(
				Constants.ECLNT_IP);
	}

	public static HttpSession getSession() {
		return HttpSessionAccess.getCurrentRequest().getSession();
	}
}

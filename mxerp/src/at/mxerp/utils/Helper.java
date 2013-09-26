package at.mxerp.utils;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
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

	public static String getColumnNameForObject(String object, String column) {
		String value = ResourceManager.getRuntimeInstance().readProperty("_" + object.toLowerCase(), column.toLowerCase());
		return StringUtils.isEmpty(value) ? column : value;
	}

	public static String getMessage(String property) {
		String value = ResourceManager.getRuntimeInstance().readProperty(Constants.MESSAGES, property);
		return StringUtils.isEmpty(value) ? property : value;
	}

	public static String getDomain(String property) {
		String value = ResourceManager.getRuntimeInstance().readProperty(Constants.DOMAINS, property);
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
		return HttpSessionAccess.getCurrentRequest().getHeader(Constants.ECLNT_IP);
	}

	public static HttpSession getSession() {
		return HttpSessionAccess.getCurrentRequest().getSession();
	}

	public static boolean isEmpty(Integer integer) {
		return integer == null || integer == 0;
	}

	public static ArrayList<String> compareBeans(Object newBean, Object oldBean) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if (newBean.getClass() != oldBean.getClass()) {
			throw new IllegalArgumentException("The beans must be from the same Class!");
		}
		ArrayList<String> changesArr = new ArrayList<String>();
		for (Object keyObj : BeanUtils.describe(newBean).keySet()) {
			String key = (String) keyObj;
			Object oldValue = PropertyUtils.getProperty(oldBean, key);
			Object newValue = PropertyUtils.getProperty(newBean, key);
			if (oldValue != newValue) {// Ignores when both are null
				if (((oldValue != null) && !oldValue.equals(newValue)) || ((newValue != null) && !newValue.equals(oldValue))) {
					changesArr.add(key);
				}
			}
		}
		return changesArr;
	}
	
	public static Date getMinDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1900,1,1,0,0,0);
		return calendar.getTime();
	}
	
	public static Date getMaxDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(9999,12,31,23,59,59);	
		return calendar.getTime();
	}
	
}

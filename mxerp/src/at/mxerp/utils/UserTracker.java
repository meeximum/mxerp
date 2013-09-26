package at.mxerp.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.util.HttpSessionAccess;

import at.mxerp.managedbeans.utils.UserAccess;
import at.mxerp.services.entities.LockManager;
import at.mxerp.structures.SUserInfo;

public class UserTracker {
	public static final Log logger = LogFactory.getLog(UserTracker.class);
	private static ConcurrentHashMap<String, SUserInfo> loggedInUserMap;
	
	static {
		loggedInUserMap = new ConcurrentHashMap<String, SUserInfo>();
	}

	public static void addUser(String user) {	
		HttpSession session = HttpSessionAccess.getCurrentHttpSession();
		session.setAttribute(UserAccess.USER_KEY, user);	
		
		if(loggedInUserMap.containsKey(session.getId())) {
			logger.error(session.getId() + " : This session is already in use!");
			return;
		} 
		loggedInUserMap.putIfAbsent(session.getId(), new SUserInfo(user, session, Helper.getClientIP()));
	}

	public static void deleteUser(String sessionId) {
		if(loggedInUserMap.containsKey(sessionId)) {
			loggedInUserMap.remove(sessionId);
			// remove locked entities by sessionId
			LockManager.removeBySession(sessionId);
		}		
	}

	public static Integer getLoggedInUsers() {
		return loggedInUserMap.size();
	}
	
	public static Map<String, SUserInfo> getLoggedInUserMap() {
		return loggedInUserMap;
	}
	
	public static boolean containsSession(String sessionId) {
		return loggedInUserMap.containsKey(sessionId);
	}
	
	public static String getUser(String sessionId) {
		if(!loggedInUserMap.containsKey(sessionId)) return null;
		return (loggedInUserMap.get(sessionId)).user;
	}
	
	public static SUserInfo getUserInfo(String sessionId) {
		return loggedInUserMap.get(sessionId);
	}
	
}

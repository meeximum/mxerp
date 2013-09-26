package at.mxerp.services.entities;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import at.mxerp.exceptions.EntityLockedException;
import at.mxerp.utils.Helper;
import at.mxerp.utils.UserTracker;


public class LockManager {
	private static ConcurrentMap<String, String> entityLockMap;

	static {
		entityLockMap = new ConcurrentHashMap<String, String>();
	}
	
	public static void lockEntity(String entityId) throws EntityLockedException{
		String sessionId = entityLockMap.get(entityId);
		if(sessionId!=null) {
			if(UserTracker.containsSession(sessionId)) {				
				throw new EntityLockedException(UserTracker.getUser(sessionId));
			} else {
				// remove lock
				UserTracker.deleteUser(sessionId);
			}
		}
		// set lock
		entityLockMap.putIfAbsent(entityId, Helper.getSession().getId());
	}
	
	public static void unlockEntity(String entityId) {
		entityLockMap.remove(entityId);
	}
	
	public static Map<String, String> getEntityLockMap() {
		return entityLockMap;
	}
	
	public static void removeBySession(String sessionId) {
		ConcurrentHashMap<String, String> copy = new ConcurrentHashMap<String, String>(entityLockMap.size());
		for(Entry<String, String> entry : entityLockMap.entrySet()) {
			if(!entry.getValue().equals(sessionId)) copy.put(entry.getKey(), entry.getValue());			
		}
		// switch maps
		entityLockMap = copy;
	}
}
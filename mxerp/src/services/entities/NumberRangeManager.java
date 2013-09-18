package services.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cayenne.ObjectContext;
import org.apache.commons.collections.CollectionUtils;

import utils.CayenneUtils;
import db.erp.NumberRanges;


public class NumberRangeManager {
	private static Map<Entity, NumberRangeManager> instancesMap;
	
	private Map<String, NumberRanges> keyMap;
		
	private NumberRangeManager(Entity entity) {
		keyMap = new HashMap<String, NumberRanges>();
		ObjectContext ctxt = CayenneUtils.createNewContext();
		
		List<NumberRanges> numberRanges = NumberRanges.getByEntity(ctxt, entity);
		if(CollectionUtils.isNotEmpty(numberRanges)) {
			for(NumberRanges numberRange : numberRanges) {
				keyMap.put(numberRange.getKey(), numberRange);
			}			
		};		
	}
	
	static {
		init();
	}
	
	public static synchronized void init() {		
		instancesMap = new HashMap<Entity, NumberRangeManager>();
		for(Entity entity : Entity.values()) {
			instancesMap.put(entity, new NumberRangeManager(entity));
		}
	}
	
	public static NumberRanges get(Entity entity, String key) throws Exception{
		NumberRangeManager manager = instancesMap.get(entity);
		if(manager==null) throw new Exception(String.format("No numberrange manager found for the entity %s!", entity.name()));
		NumberRanges numberRange = manager.keyMap.get(key);
		if(key==null) throw new Exception(String.format("No numberrange found for the entity %s and key %s!",entity.name(), key));
		return numberRange;
	}
	
	
}

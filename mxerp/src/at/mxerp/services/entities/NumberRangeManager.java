package at.mxerp.services.entities;

import java.util.HashMap;
import java.util.Map;

import org.apache.cayenne.ObjectContext;

import at.mxerp.db.erp.NumberRanges;
import at.mxerp.utils.CayenneUtils;


public class NumberRangeManager {
	private static Map<String, NumberRangeManager> instancesMap;
	
	private NumberRanges numberRange;
	
	private NumberRangeManager(NumberRanges numberRange) {
		this.numberRange = numberRange;
	}
	
	static {
		init();
	}
	
	public static synchronized void init() {
		ObjectContext ctxt = CayenneUtils.createNewContext();
		instancesMap = new HashMap<String, NumberRangeManager>();
		for(NumberRanges numberRange : NumberRanges.getAllActive(ctxt)) {
			instancesMap.put(numberRange.getId(), new NumberRangeManager(numberRange));
		}
	}
	
	public static NumberRanges get(String id) throws Exception{
		NumberRangeManager manager = instancesMap.get(id);
		if(manager==null) throw new Exception(String.format("No numberrange manager found for ID %s!", id));
		return manager.numberRange;
	}	
	
}

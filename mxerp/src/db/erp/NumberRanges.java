package db.erp;

import java.util.Calendar;
import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.query.SelectQuery;
import org.apache.commons.lang3.StringUtils;

import services.entities.Entity;
import services.entities.NumberRangeManager;
import utils.Helper;



@SuppressWarnings("serial")
public class NumberRanges extends _NumberRanges implements ICustomizing {

	public NumberRanges() {
		super();
		setLocked(false);
	}
	
	@SuppressWarnings("unchecked")
	public static List<NumberRanges> getByEntity(ObjectContext ctxt, Entity entity) {
		Expression expression = NumberRanges.ENTITY.eq(entity.name());
		SelectQuery<NumberRanges> query = new SelectQuery<NumberRanges>(NumberRanges.class, expression);
		List<NumberRanges> result = ctxt.performQuery(query);
		return result;
	}
	
	private String getFormatted(Integer value) {
		String actualAsString = null;
		if(getDigits()>0) {
			actualAsString = String.format("%0" + getDigits()  + "d", value);
		} else {
			actualAsString = String.valueOf(value);
		}

		if(StringUtils.isEmpty(getPrefix())) {
			return actualAsString;
		} else {
			int year = Calendar.getInstance().get(Calendar.YEAR);
			return getPrefix().replace("@year@", String.valueOf(year)) + actualAsString;
		}
	}

	public boolean isWarnLevelReached() {
		if(Helper.isEmpty(getActual())) return false;
		return (getHigh() - getActual()) <= getWarnLevel();
	}

	public synchronized String increment() throws Exception {
		Integer actual = Helper.isEmpty(getActual())?getLow():getActual();
		if(actual.equals(getHigh())) {
			if(getRolling()) {
				actual = getLow();
			} else {
				throw new Exception("Limit reached!!");
			}
		} else {
			actual++;			
		}
		setActual(actual);
		getObjectContext().commitChanges();
		return getFormatted(actual);
	}

	@Override
	protected void onPostPersist() {
		NumberRangeManager.init();		
	}

	@Override
	protected void onPostUpdate() {
		NumberRangeManager.init();			
	}

}

package at.mxerp.services.savedsearches;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "savedsearch")
@XmlType(name = "savedsearch")
public class SavedSearch {

	@XmlElementWrapper(name = "values")
	private HashMap<Integer, SavedSearchValues> savedSearchMap;

	public HashMap<Integer, SavedSearchValues> getSavedSearchMap() {
		return savedSearchMap;
	}

	public void setGridsMap(HashMap<Integer, SavedSearchValues> savedSearchMap) {
		this.savedSearchMap = savedSearchMap;
	}
	
	

	public SavedSearch() {
		super();
		savedSearchMap = new HashMap<Integer, SavedSearchValues>();
	}
	
	public void addSavedSearchValue(SavedSearchValues savedSearchValue) {
		int index = savedSearchMap.size();
		savedSearchMap.put(index, savedSearchValue);
	}


	@XmlAccessorType(XmlAccessType.FIELD)
	public static class SavedSearchValues {
		private String field;
		private String operator;
		private Object valueLow;
		private Object valueHigh;

		public SavedSearchValues(String field, String operator, Object valueLow, Object valueHigh) {
			super();
			this.field = field;
			this.operator = operator;
			this.valueLow = valueLow;
			this.valueHigh = valueHigh;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getOperator() {
			return operator;
		}

		public void setOperator(String operator) {
			this.operator = operator;
		}

		public Object getValueLow() {
			return valueLow;
		}

		public void setValueLow(Object valueLow) {
			this.valueLow = valueLow;
		}

		public Object getValueHigh() {
			return valueHigh;
		}

		public void setValueHigh(Object valueHigh) {
			this.valueHigh = valueHigh;
		}

		public SavedSearchValues() {

		}

	}

}

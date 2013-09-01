package services.savedsearches;

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

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class SavedSearchValues {
		private String field;
		private String operator;
		private String valueLow;
		private String valueHigh;

		public SavedSearchValues(String field, String operator, String valueLow, String valueHigh) {
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

		public String getValueLow() {
			return valueLow;
		}

		public void setValueLow(String valueLow) {
			this.valueLow = valueLow;
		}

		public String getValueHigh() {
			return valueHigh;
		}

		public void setValueHigh(String valueHigh) {
			this.valueHigh = valueHigh;
		}

		public SavedSearchValues() {

		}

	}

}

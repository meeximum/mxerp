package at.mxerp.services.auditing;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.map.ObjAttribute;
import org.apache.commons.lang3.StringUtils;
import org.joor.Reflect;

public class AuditHelper {
	public static List<FieldCompare> getDifferences(CayenneDataObject obj1, CayenneDataObject obj2) throws IllegalArgumentException {
		ArrayList<FieldCompare> diffs;
		
		if (obj1 == null || obj2 == null)
			throw new IllegalArgumentException("Can't compare null objects!");
		
		if(!obj1.getClass().equals(obj2.getClass())) 
			throw new IllegalArgumentException("Objects must be same type!");

		diffs = new ArrayList<FieldCompare>();

		Collection<ObjAttribute> attributes = obj1.getObjectContext().getEntityResolver().getObjEntity(obj1.getClass()).getAttributes();
		
		for(ObjAttribute attribute : attributes) {
			String methodName = "get" + StringUtils.capitalize(attribute.getName());

			try {
				Method m = obj1.getClass().getMethod(methodName);

				if (m.isAnnotationPresent(Auditing.class)) {
					String v1 = "" + Reflect.on(obj1).call(methodName).get();
					String v2 = "" + Reflect.on(obj2).call(methodName).get();

					if (!v1.equals(v2))
						diffs.add(0, new FieldCompare(attribute.getName(), v1, v2));
				}
			} catch (Exception ex) {}
		}

		return diffs;
	}

	public static class FieldCompare {
		private String field;
		private String val1;
		private String val2;

		public FieldCompare(String field, String val1, String val2) {
			super();
			this.field = field;
			this.val1 = val1;
			this.val2 = val2;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getVal1() {
			return val1;
		}

		public void setVal1(String val1) {
			this.val1 = val1;
		}

		public String getVal2() {
			return val2;
		}

		public void setVal2(String val2) {
			this.val2 = val2;
		}

		public String toString() {
			return "Field: " + field + ", Val1: " + val1 + ", Val2: " + val2;
		}
	}
}

package services.auditing;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.joor.Reflect;

public class AuditHelper {
  public static <T> List<FieldCompare> getDifferences(T obj1, T obj2) throws IllegalArgumentException {
    ArrayList<FieldCompare> diffs;
    String fName;
    boolean toCheck;

    if (obj1 == null || obj2 == null)
      throw new IllegalArgumentException("Can't compare null objects");

    diffs = new ArrayList<FieldCompare>();

    for (Field f : obj1.getClass().getFields()) {
      fName = f.getName();

      if (fName.endsWith("_PROPERTY") && !fName.startsWith("GUID") && !fName.startsWith("CHANGED") && !fName.startsWith("TO_")) {
        fName = Reflect.on(obj1).field(fName).get();
        fName = "get" + fName.substring(0, 1).toUpperCase() + fName.substring(1);

        toCheck = true;
        try {
          Method m = obj1.getClass().getMethod(fName);

          if (m.isAnnotationPresent(Auditing.class))
            toCheck = m.getAnnotation(Auditing.class).toCheck();

          if (toCheck) {
            String v1 = "" + Reflect.on(obj1).call(fName).get();
            String v2 = "" + Reflect.on(obj2).call(fName).get();

            if (!v1.equals(v2))
              diffs.add(0, new FieldCompare(fName.substring(3), v1, v2));
          }
        } catch (SecurityException e) {
          e.printStackTrace();
        } catch (NoSuchMethodException e) {
          e.printStackTrace();
        }
      }
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

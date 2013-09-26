package at.mxerp.managedbeans.pojo.form;

import java.lang.reflect.Field;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding.ComponentNode;

public class FormGenerator {

  public static ROWDYNAMICCONTENTBinding generateForm(String expressionBase, Object pojo) {
    ROWDYNAMICCONTENTBinding content = new ROWDYNAMICCONTENTBinding();

    try {
      SortedMap<Integer, Field> ccFormFields = new TreeMap<Integer, Field>();
      CCForm ccForm = checkAnnotations(pojo, ccFormFields);
      int columns = ccForm.columns();
      ComponentNode csp = buildCSP(ccForm);
      {
        int index = 0;
        ComponentNode csr = new ComponentNode("t:colsynchedrow");
        for (Entry<Integer, Field> ccFormfield : ccFormFields.entrySet()) {
          if (index > 0 && index % columns == 0) {
            csp.addSubNode(csr);
            csr = new ComponentNode("t:colsynchedrow");
          }
          buildFormField(csr, ccFormfield.getValue(), expressionBase);
          index++;
        }
        csp.addSubNode(csr);
      }
      content.setContentNode(csp);
    } catch (Exception ex) {
      content.setContentNode(FormUtils.renderError(ex.getMessage()));
    }
    return content;
  }

  private static CCForm checkAnnotations(Object pojo, SortedMap<Integer, Field> ccFormFields) throws Exception {
    CCForm ccForm = null;
    Class<?> clazz = pojo.getClass();
    if (clazz.isAnnotationPresent(CCForm.class)) {
      ccForm = clazz.getAnnotation(CCForm.class);
      Field[] fields = clazz.getDeclaredFields();
      for (Field field : fields) {
        if (field.isAnnotationPresent(CCFormField.class)) {
          ccFormFields.put(field.getAnnotation(CCFormField.class).order(), field);
        }
      }
    }
    return ccForm;
  }

  

  private static ComponentNode buildCSP(CCForm ccForm) {
    ComponentNode csp = new ComponentNode("t:colsynchedpane");
    for (String kvPair : ccForm.value().split(";")) {
      String[] keyValue = kvPair.split("=");
      if (keyValue.length == 2) {
        csp.addAttribute(keyValue[0].trim(), keyValue[1].trim());
      }
    }
    return csp;
  }

  private static void buildFormField(ComponentNode formRow, Field field, String expressionBase) {
    CCFormField annotation = field.getAnnotation(CCFormField.class);

    ComponentNode labelNode = new ComponentNode("t:label");
    labelNode.addAttribute("text", annotation.title());
    formRow.addSubNode(labelNode);

    ComponentNode fieldNode = new ComponentNode(annotation.component());
    String expression = expressionBase.replace("}", "." + field.getName() + "}");
    String value = annotation.value().replaceAll("#value#", expression);

    for (String kvPair : value.split(";")) {
      String[] keyValue = kvPair.split("=");
      if (keyValue.length == 2) {
        fieldNode.addAttribute(keyValue[0].trim(), keyValue[1].trim());
      }
    }
    formRow.addSubNode(fieldNode);

  }
}
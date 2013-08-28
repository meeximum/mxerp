package managedbeans.pojo.form;

import org.eclnt.jsfserver.elements.componentnodes.LABELNode;
import org.eclnt.jsfserver.elements.impl.ROWDYNAMICCONTENTBinding.ComponentNode;

public class FormUtils {
  public static ComponentNode renderError(String error) {
    LABELNode label = new LABELNode();
    label.setText("<html><b><font color='#ff0000'>" + error + "</font></b></html>");
    label.setWidth("100%");
    return label;
  }
}

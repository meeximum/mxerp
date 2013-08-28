package managedbeans.commons;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.event.ActionEvent;

import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding;
import org.eclnt.jsfserver.elements.util.ValidValuesBinding.ValidValue;
import org.eclnt.jsfserver.pagebean.PageBean;

import utils.Constants;

@CCGenClass(expressionBase = "#{d.CombofieldPB}")
public class CombofieldPB extends PageBean implements Serializable {
  public void onSelectNone(ActionEvent event) {
    for (int i = 0; i < getGridValues().getItems().size(); i++) {
      getGridValues().deselectItem(i);
    }
  }

  public void onSelectAll(ActionEvent event) {
    for (int i = 0; i < getGridValues().getItems().size(); i++) {
      getGridValues().selectItem(i);
    }
  }
  
  private boolean singleSelect;

  public boolean isSingleSelect() {
    return singleSelect;
  }

  public void setSingleSelect(boolean singleSelect) {
    this.singleSelect = singleSelect;
  }
  
  public boolean isMultiSelect() {
    return !singleSelect;
  }

  public void setMultiSelect(boolean multiSelect) {
    this.singleSelect = !multiSelect;
  }

  private String image;

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  private int amount;

  public int getAmount() {
    return amount;
  }

  public void setAmount(int value) {
    this.amount = value;
  }

  private int width;

  public String getWidth() {
    return String.valueOf(width);
  }

  private static final long serialVersionUID = 1L;

  public CombofieldPB(ValidValuesBinding vvb, List<String> markedValues, int width, int amount, boolean singleSelect) {
    this.width = width;
    this.amount = amount;
    this.singleSelect = singleSelect;
    getGridValues().getItems().clear();
    Iterator<ValidValue> validValues = vvb.getValidValues();
    while (validValues.hasNext()) {  
      ValidValue validValue = validValues.next();
      if(Constants.ASTERIX.equals(validValue.getValue())) continue;
      GridValuesItem item = new GridValuesItem(validValue);
      if (markedValues.contains(validValue.getValue()))
        item.setSelected(true);
      getGridValues().getItems().add(item);
    }
  }
  
  public CombofieldPB(ValidValuesBinding vvb, List<String> markedValues, int width, int amount) {
    this(vvb, markedValues, width, amount, false);
  }

  //  public CombofieldPB(ValidValuesBinding vvb, int width, int amount) {
  //    this.width = width;
  //    this.amount = amount;
  //    getGridValues().getItems().clear();
  //    Iterator<ValidValue> validValues = vvb.getValidValues();
  //    while (validValues.hasNext()) {
  //      ValidValue validValue = validValues.next();
  //      getGridValues().getItems().add(new GridValuesItem(validValue.getValue(), validValue.getText()));
  //    }
  //  }

  public String getPageName() {
    return "/ui/commons/combofield.jsp";
  }

  public String getRootExpressionUsedInPage() {
    return "#{d.CombofieldPB}";
  }

  // ------------------------------------------------------------------------

  FIXGRIDListBinding<GridValuesItem> m_gridValues = new FIXGRIDListBinding<GridValuesItem>();

  public FIXGRIDListBinding<GridValuesItem> getGridValues() {
    return m_gridValues;
  }

  public void setGridValues(FIXGRIDListBinding<GridValuesItem> value) {
    this.m_gridValues = value;
  }

  public class GridValuesItem extends FIXGRIDItem implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    ValidValue validValue;
    
    public String getId() {
      return validValue.getValue();
    }

    public String getValue() {
      return validValue.getText();
    }
    
    public String getImage() {
      return validValue.getImage();
    }
    
    public String getComment() {
      return validValue.getValuecomment();
    }

    public GridValuesItem(ValidValue validValue) {
      super();
      this.validValue = validValue;
    }

  }

  public Map<String, String> getSelectedValues() {
    Map<String, String> valuesMap = new HashMap<String, String>();
    for (GridValuesItem item : getGridValues().getSelectedItems()) {
      valuesMap.put(item.getId(), item.getValue());
    }
    return valuesMap;
  }
}

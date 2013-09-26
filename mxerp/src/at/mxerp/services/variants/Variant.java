package at.mxerp.services.variants;

import java.util.HashMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "variant")
@XmlType(name = "variant")
public class Variant {
  private String name;  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  private String description;  
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  @XmlElementWrapper(name="grids")
  private HashMap<String, GridValues> gridsMap;  
  public HashMap<String, GridValues> getGridsMap() {
    return gridsMap;
  }
  public void setGridsMap(HashMap<String, GridValues> gridsMap) {
    this.gridsMap = gridsMap;
  }

  @XmlElementWrapper(name="properties")
  private HashMap<String, Object> propertyMap;  
  public HashMap<String, Object> getPropertyMap() {
    return propertyMap;
  }
  public void setPropertyMap(HashMap<String, Object> propertyMap) {
    this.propertyMap = propertyMap;    
  }
   
  public void addProperty(String property, Object value) {
    propertyMap.put(property, value);
  }
  
  public void addGrid(String grid, GridValues value) {
    gridsMap.put(grid, value);
  }
  
  @SuppressWarnings("unused")
  private Variant() {
    propertyMap = new HashMap<String, Object>();
    gridsMap = new HashMap<String, GridValues>();
  }
  
  public Variant(String name, String description) {
    this.name = name;
    this.description = description;
    propertyMap = new HashMap<String, Object>();
    gridsMap = new HashMap<String, GridValues>();
  }
  
  @XmlAccessorType(XmlAccessType.FIELD)
  public static class GridValues {    
    private String columnSequence;    
    private String columnWidths;
    public String getColumnSequence() {
      return columnSequence;
    }
    public void setColumnSequence(String columnSequence) {
      this.columnSequence = columnSequence;
    }
    public String getColumnWidths() {
      return columnWidths;
    }
    public void setColumnWidths(String columnWidths) {
      this.columnWidths = columnWidths;
    }
    public GridValues(String columnSequence, String columnWidths) {
      super();
      this.columnSequence = columnSequence;
      this.columnWidths = columnWidths;
    }
    
    public GridValues() {
      
    }
     
  }
}

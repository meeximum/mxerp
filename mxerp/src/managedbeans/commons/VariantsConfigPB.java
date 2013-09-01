package managedbeans.commons;

import java.io.Serializable;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.apache.commons.lang3.StringUtils;
import org.eclnt.editor.annotations.CCGenClass;
import org.eclnt.jsfserver.defaultscreens.Statusbar;
import org.eclnt.jsfserver.elements.impl.FIXGRIDItem;
import org.eclnt.jsfserver.elements.impl.FIXGRIDListBinding;
import org.eclnt.jsfserver.pagebean.PageBean;

import services.variants.Variant;
import services.variants.VariantsService;
import utils.Helper;

@CCGenClass(expressionBase = "#{d.VariantsConfigPB}")
public class VariantsConfigPB extends PageBean implements Serializable {
  private static final long serialVersionUID = 1L;

  private ICallback callback;
  public void setCallback(ICallback callback) {
    this.callback = callback;
  }

  String m_description;

  public String getDescription() {
    return m_description;
  }

  public void setDescription(String value) {
    this.m_description = value;
  }

  String m_name;

  public String getName() {
    return m_name;
  }

  public void setName(String value) {
    this.m_name = value;
  }

  public void onSave(ActionEvent event) {
    if (StringUtils.isEmpty(m_name)) {
      Statusbar.outputAlert(String.format(Helper.getMessage("mand_fields"), Helper.getLiteral("Name")));
      return;
    } 
    m_name = m_name.toLowerCase();
    callback.save(user, object, m_name, StringUtils.isEmpty(m_description) ? m_name : m_description);
 }

  FIXGRIDListBinding<GridVariantsItem> m_gridVariants = new FIXGRIDListBinding<GridVariantsItem>();

  public FIXGRIDListBinding<GridVariantsItem> getGridVariants() {
    return m_gridVariants;
  }

  public void setGridVariants(FIXGRIDListBinding<GridVariantsItem> value) {
    this.m_gridVariants = value;
  }

  public class GridVariantsItem extends FIXGRIDItem implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    public void onDelete(ActionEvent event) {
      try {
        VariantsService.delete(user, object, variant);
        Statusbar.outputSuccess(Helper.getMessage("del_variant_succ"));
      } catch(Exception ex) {
        Statusbar.outputAlert(Helper.getMessage("del_variant_err"), ex.toString());
      }      
      buildVariantsGrid();      
    }

    Variant variant;
    public Variant getVariant() {
      return variant;
    }

    public GridVariantsItem(Variant variant) {
      this.variant = variant;
    }

    @Override
    public void onRowSelect() {      
      super.onRowSelect();
      m_name = variant.getName();
      m_description = variant.getDescription();
    }

  }
  
  private void buildVariantsGrid() {
    getGridVariants().getItems().clear();
    List<Variant> variants = VariantsService.get(user, object);
    for(Variant variant : variants) {
      getGridVariants().getItems().add(new GridVariantsItem(variant));
    }
  }

  // ------------------------------------------------------------------------
  // constructors & initialization
  // ------------------------------------------------------------------------

  String object;
  String user;

  public VariantsConfigPB(String object) {
    this.object = object;
    this.user = Helper.getUserName();
    buildVariantsGrid();
  }

  public String getPageName() {
    return "/ui/commons/variantsconfig.jsp";
  }

  public String getRootExpressionUsedInPage() {
    return "#{d.VariantsConfigPB}";
  }

  // ------------------------------------------------------------------------

  public interface ICallback {
    public void save(String user, String object, String name, String description);
  }
}

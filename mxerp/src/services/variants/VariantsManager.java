package services.variants;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclnt.jsfserver.elements.componentnodes.BUTTONMENUNode;
import org.eclnt.jsfserver.elements.componentnodes.MENUITEMNode;
import org.eclnt.jsfserver.elements.componentnodes.MENUSEPARATORNode;
import org.eclnt.jsfserver.elements.impl.BUTTONComponentTag;
import org.eclnt.jsfserver.elements.impl.DYNAMICCONTENTBinding;
import org.eclnt.jsfserver.elements.impl.MENUITEMComponentTag;

public class VariantsManager {
  protected final static Log logger = LogFactory.getLog(VariantsManager.class);
  private final static String DEFAULT_VARIANT = "_default_";

  public static enum VariantType {
    NONE, USER, GLOBAL
  }
  
  IVariants bean;
  String user;
  String object;
  List<Variant> variants; 
  
  @SuppressWarnings("unused")
  private VariantsManager() {
    
  }
  
  public VariantsManager(IVariants bean, String user, String object) {
    this.bean = bean;
    this.object = object;
    this.user = user;
  }
   
  public DYNAMICCONTENTBinding getVariantsBinding(String selectedExp, String selectExp, String configExp) {
    if(variants==null) fillVariantsForMenu();
    DYNAMICCONTENTBinding binding = new DYNAMICCONTENTBinding();
    BUTTONMENUNode buttonMenu = new BUTTONMENUNode();
    buttonMenu.addAttribute(BUTTONComponentTag.ATT_buttonmenumode, "buttonandmenu");
    buttonMenu.addAttribute(BUTTONComponentTag.ATT_contentareafilled, "false");
    buttonMenu.addAttribute(BUTTONComponentTag.ATT_text, selectedExp);

    MENUITEMNode menuItem = new MENUITEMNode();
    menuItem.addAttribute(MENUITEMComponentTag.ATT_text, "Default");
    menuItem.addAttribute(MENUITEMComponentTag.ATT_ACTIONLISTENER, selectExp);
    menuItem.addAttribute(MENUITEMComponentTag.ATT_COMMENT, DEFAULT_VARIANT);
    buttonMenu.addSubNode(menuItem);

    buttonMenu.addSubNode(new MENUSEPARATORNode());

    for (Variant variant : variants) {
      menuItem = new MENUITEMNode();
      menuItem.addAttribute(MENUITEMComponentTag.ATT_text, variant.getDescription());
      menuItem.addAttribute(MENUITEMComponentTag.ATT_ACTIONLISTENER, selectExp);
      menuItem.addAttribute(MENUITEMComponentTag.ATT_COMMENT, variant.getName());
      buttonMenu.addSubNode(menuItem);
    }

    buttonMenu.addSubNode(new MENUSEPARATORNode());

    menuItem = new MENUITEMNode();
    menuItem.addAttribute(MENUITEMComponentTag.ATT_text, "#{rr.literals['manage_variants']}");
    menuItem.addAttribute(MENUITEMComponentTag.ATT_ACTIONLISTENER, configExp);
    buttonMenu.addSubNode(menuItem);

    binding.setContentNode(buttonMenu);
    //buttonMenu.setText(value)
    return binding;
  }

  public VariantType loadUsersOrGlobalDefaultVariant() {
    VariantType variantType = VariantType.NONE;
    try {
      Variant variant = VariantsService.loadVariant(user, object, user);
      if(variant!=null) {
        variantType = VariantType.USER;
      } else {        
        variant = VariantsService.loadVariant(DEFAULT_VARIANT, object, DEFAULT_VARIANT);
        if(variant!=null) {
          variantType = VariantType.GLOBAL;
        }
      }
      if(variant!=null) bean.loadVariant(variant);
    } catch (Exception ex) {
      logger.error("Error loading default variant!", ex);
    }
    return variantType;
  }

  public String onVariantSelected(String name) throws Exception {
    Variant variant;
    if (DEFAULT_VARIANT.equals(name)) {
      variant = VariantsService.loadVariant(DEFAULT_VARIANT, object, name);
    } else {
      variant = VariantsService.loadVariant(user, object, name);
    }
    if(variant==null) return StringUtils.EMPTY;
    bean.loadVariant(variant);
    return variant.getDescription();
  }
  
  public void fillVariantsForMenu() {
    variants = VariantsService.getVariants(user, object);
  }

}

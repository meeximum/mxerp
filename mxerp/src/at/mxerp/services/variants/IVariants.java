package at.mxerp.services.variants;

public interface IVariants {
  public Variant buildActualVariant(String name, String description);
  public void loadVariant(Variant variant);
}

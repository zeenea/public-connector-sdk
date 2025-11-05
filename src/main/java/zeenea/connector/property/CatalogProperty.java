package zeenea.connector.property;

import static zeenea.common.properties.CommonProperties.catalogCommonProperty;

import java.util.List;

public final class CatalogProperty implements CommonProperty {
  private static final TagPropertyDefinition CATALOG =
      new TagPropertyDefinition(catalogCommonProperty.defaultName(), catalogCommonProperty.uuid());
  private final TagPropertyValue tagPropertyValue;

  public CatalogProperty(TagPropertyValue value) {
    this.tagPropertyValue = value;
  }

  public CatalogProperty(List<String> value) {
    this.tagPropertyValue = new TagPropertyValue(value);
  }

  public CatalogProperty(String... value) {
    tagPropertyValue = new TagPropertyValue(List.of(value));
  }

  public static CatalogProperty of(TagPropertyValue value) {
    return new CatalogProperty(value);
  }

  public static CatalogProperty of(List<String> value) {
    return new CatalogProperty(value);
  }

  public static CatalogProperty of(String... value) {
    return new CatalogProperty(value);
  }

  @Override
  public PropertyDefinition getPropertyDefinition() {
    return CATALOG;
  }

  @Override
  public PropertyValue getValue() {
    return tagPropertyValue;
  }
}

package zeenea.connector.property;

public sealed interface CommonProperty permits CatalogProperty {

  PropertyDefinition getPropertyDefinition();

  PropertyValue getValue();
}

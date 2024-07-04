package zeenea.connector.property;

/**
 * Represents a property definition for a URL type. This class extends the PropertyDefinition class.
 */
public final class UrlPropertyDefinition extends PropertyDefinition {

  /**
   * Constructs a UrlPropertyDefinition instance with the specified code.
   *
   * @param code the code for the property definition
   */
  public UrlPropertyDefinition(String code) {
    super(code, PropertyType.URL);
  }
}

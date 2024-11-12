package zeenea.connector.property;

/**
 * Represents a property definition for a tag type. This class extends the PropertyDefinition class.
 */
public final class TagPropertyDefinition extends PropertyDefinition {

  /**
   * Constructs a TagPropertyDefinition instance with the specified code.
   *
   * @param code the code for the property definition
   */
  public TagPropertyDefinition(String code) {
    super(code, PropertyType.TAG, null);
  }

  /**
   * Constructs a TagPropertyDefinition instance with the specified code and label.
   *
   * @param code the code for the property definition
   * @param label the label for the property definition
   */
  public TagPropertyDefinition(String code, String label) {
    super(code, PropertyType.TAG, label);
  }
}

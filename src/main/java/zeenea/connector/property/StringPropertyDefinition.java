package zeenea.connector.property;

/**
 * Represents a property definition for a string type. This class extends the PropertyDefinition
 * class.
 */
public final class StringPropertyDefinition extends PropertyDefinition {

  /**
   * Constructs a StringPropertyDefinition instance with the specified code.
   *
   * @param code the code for the property definition
   */
  public StringPropertyDefinition(String code) {
    super(code, PropertyType.STRING, null);
  }

  /**
   * Constructs a StringPropertyDefinition instance with the specified code and label.
   *
   * @param code the code for the property definition
   * @param label the label for the property definition
   */
  public StringPropertyDefinition(String code, String label) {
    super(code, PropertyType.STRING, label);
  }
}

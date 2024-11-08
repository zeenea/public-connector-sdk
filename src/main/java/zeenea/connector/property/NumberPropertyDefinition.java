package zeenea.connector.property;

/**
 * Represents a property definition for a number type. This class extends the PropertyDefinition
 * class.
 */
public final class NumberPropertyDefinition extends PropertyDefinition {

  /**
   * Constructs a NumberPropertyDefinition instance with the specified code.
   *
   * @param code the code for the property definition
   */
  public NumberPropertyDefinition(String code) {
    super(code, PropertyType.NUMBER, null);
  }

  /**
   * Constructs a NumberPropertyDefinition instance with the specified code and label.
   *
   * @param code the code for the property definition
   * @param label the label for the property definition
   */
  public NumberPropertyDefinition(String code, String label) {
    super(code, PropertyType.NUMBER, label);
  }
}

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
    super(code, PropertyType.NUMBER);
  }
}

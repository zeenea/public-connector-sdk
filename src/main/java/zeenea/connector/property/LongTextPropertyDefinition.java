package zeenea.connector.property;

/**
 * Represents a property definition for a long text type. This class extends the PropertyDefinition
 * class.
 */
public final class LongTextPropertyDefinition extends PropertyDefinition {

  /**
   * Constructs a LongTextPropertyDefinition instance with the specified code.
   *
   * @param code the code for the property definition
   */
  public LongTextPropertyDefinition(String code) {
    super(code, PropertyType.LONG_TEXT);
  }
}

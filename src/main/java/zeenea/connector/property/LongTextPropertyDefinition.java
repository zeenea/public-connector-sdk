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
    super(code, PropertyType.LONG_TEXT, null);
  }

  /**
   * Constructs a LongTextPropertyDefinition instance with the specified code and label.
   *
   * @param code the code for the property definition
   * @param label the label for the property definition
   */
  public LongTextPropertyDefinition(String code, String label) {
    super(code, PropertyType.LONG_TEXT, label);
  }
}

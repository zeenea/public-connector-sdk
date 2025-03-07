package zeenea.connector.property;

/**
 * Represents a property definition for an instant type. This class extends the PropertyDefinition
 * class.
 */
public final class InstantPropertyDefinition extends PropertyDefinition {

  /**
   * Constructs an InstantPropertyDefinition instance with the specified code.
   *
   * @param code the code for the property definition
   */
  public InstantPropertyDefinition(String code) {
    super(code, PropertyType.INSTANT, null);
  }

  /**
   * Constructs an InstantPropertyDefinition instance with the specified code and label.
   *
   * @param code the code for the property definition
   * @param label the label for the property definition
   */
  public InstantPropertyDefinition(String code, String label) {
    super(code, PropertyType.INSTANT, label);
  }
}

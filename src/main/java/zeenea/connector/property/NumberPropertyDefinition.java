package zeenea.connector.property;

/**
 * Represents a property definition for a number type. This class extends the PropertyDefinition
 * class.
 */
public final class NumberPropertyDefinition extends PropertyDefinition {

  /**
   * Constructs a NumberPropertyDefinition instance with the specified name.
   *
   * @param name the name for the property definition
   */
  public NumberPropertyDefinition(String name) {
    super(name, PropertyType.NUMBER);
  }

  /**
   * Constructs a NumberPropertyDefinition instance with the specified code and label.
   *
   * @param code the code for the property definition
   * @param label the label for the property definition
   */
  @Deprecated(
      since =
          "Deprecated since version 2.3.0, use NumberPropertyDefinition(String name) instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public NumberPropertyDefinition(String code, String label) {
    super(code, PropertyType.NUMBER, label);
  }
}

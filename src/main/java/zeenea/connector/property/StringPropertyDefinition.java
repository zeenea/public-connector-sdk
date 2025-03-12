package zeenea.connector.property;

/**
 * Represents a property definition for a string type. This class extends the PropertyDefinition
 * class.
 */
public final class StringPropertyDefinition extends PropertyDefinition {

  /**
   * Constructs a StringPropertyDefinition instance with the specified code.
   *
   * @param name the code for the property definition
   */
  public StringPropertyDefinition(String name) {
    super(name, PropertyType.STRING);
  }

  /**
   * Constructs a StringPropertyDefinition instance with the specified code and label.
   *
   * @param code the code for the property definition
   * @param label the label for the property definition
   */
  @Deprecated(
      since =
          "Deprecated since version 2.3.3, use StringPropertyDefinition(String name) instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public StringPropertyDefinition(String code, String label) {
    super(code, PropertyType.STRING, label);
  }
}

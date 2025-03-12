package zeenea.connector.property;

/**
 * Represents a property definition for an instant type. This class extends the PropertyDefinition
 * class.
 */
public final class InstantPropertyDefinition extends PropertyDefinition {

  /**
   * Constructs an InstantPropertyDefinition instance with the specified name.
   *
   * @param name the name for the property definition
   */
  public InstantPropertyDefinition(String name) {
    super(name, PropertyType.INSTANT);
  }

  /**
   * Constructs an InstantPropertyDefinition instance with the specified code and label.
   *
   * @param code the code for the property definition
   * @param label the label for the property definition
   */
  @Deprecated(
      since =
          "Deprecated since version 2.3.3, use InstantPropertyDefinition(String name) instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public InstantPropertyDefinition(String code, String label) {
    super(code, PropertyType.INSTANT, label);
  }
}

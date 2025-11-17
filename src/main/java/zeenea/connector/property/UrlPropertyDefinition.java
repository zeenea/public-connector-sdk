package zeenea.connector.property;

import java.util.UUID;

/**
 * Represents a property definition for a URL type. This class extends the PropertyDefinition class.
 */
public final class UrlPropertyDefinition extends PropertyDefinition {

  /**
   * Constructs a UrlPropertyDefinition instance with the specified name.
   *
   * @param name the name for the property definition
   */
  public UrlPropertyDefinition(String name) {
    super(name, PropertyType.URL);
  }

  /**
   * Constructs a UrlPropertyDefinition instance with the specified name and UUID. Used only
   * internally for common properties
   *
   * @param name the name for the property definition
   * @param uuid the UUID for the property definition
   */
  UrlPropertyDefinition(String name, UUID uuid) {
    super(name, PropertyType.URL, uuid);
  }

  /**
   * Constructs a UrlPropertyDefinition instance with the specified code and label.
   *
   * @param code the code for the property definition
   * @param label the label for the property definition
   */
  @Deprecated(
      since =
          "Deprecated since version 2.3.3, use UrlPropertyDefinition(String name) instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public UrlPropertyDefinition(String code, String label) {
    super(code, PropertyType.URL, label);
  }
}

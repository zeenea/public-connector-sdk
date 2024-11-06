package zeenea.connector.property;

import java.util.UUID;

/**
 * Represents a property definition for a URL type. This class extends the PropertyDefinition class.
 */
public final class UrlPropertyDefinition extends PropertyDefinition {

  /**
   * Constructs a UrlPropertyDefinition instance with the specified code.
   *
   * @param code the code for the property definition
   */
  public UrlPropertyDefinition(String code) {
    super(code, PropertyType.URL, null);
  }

  /**
   * Constructs a UrlPropertyDefinition instance with the specified code and UUID.
   *
   * @param code the code for the property definition
   * @param uuid the UUID for the property definition
   */
  public UrlPropertyDefinition(String code, UUID uuid) {
    super(code, PropertyType.URL, uuid);
  }
}

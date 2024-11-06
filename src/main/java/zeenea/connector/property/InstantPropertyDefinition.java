package zeenea.connector.property;

import java.util.UUID;

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
   * Constructs an InstantPropertyDefinition instance with the specified code and UUID.
   *
   * @param code the code for the property definition
   * @param uuid the UUID for the property definition
   */
  public InstantPropertyDefinition(String code, UUID uuid) {
    super(code, PropertyType.INSTANT, uuid);
  }
}

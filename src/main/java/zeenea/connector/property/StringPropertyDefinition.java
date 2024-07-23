package zeenea.connector.property;

/**
 * Sub-class of {@link PropertyDefinition} associated to a value of type {@code String}.
 *
 * @see PropertyDefinition
 * @see StringPropertyValue
 * @since 1.0.0
 */
public class StringPropertyDefinition extends PropertyDefinition {

  /**
   * Create a new instance of StringMetadata, a metadata of type {@code MetadataType.STRING}.
   *
   * @param code The metadata code
   */
  public StringPropertyDefinition(String code) {
    super(code, PropertyType.STRING);
  }
}

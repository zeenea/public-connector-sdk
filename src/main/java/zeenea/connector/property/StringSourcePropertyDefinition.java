package zeenea.connector.property;

/**
 * Sub-class of {@link SourcePropertyDefinition} associated to a value of type {@code String}.
 *
 * @see SourcePropertyDefinition
 * @see StringSourcePropertyValue
 * @since 1.0.0
 */
public class StringSourcePropertyDefinition extends SourcePropertyDefinition {

  /**
   * Create a new instance of StringMetadata, a metadata of type {@code MetadataType.STRING}.
   *
   * @param code The metadata code
   */
  public StringSourcePropertyDefinition(String code) {
    super(code, SourcePropertyType.STRING);
  }
}

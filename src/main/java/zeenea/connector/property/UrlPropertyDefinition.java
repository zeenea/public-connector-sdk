package zeenea.connector.property;

/**
 * Sub-class of {@link PropertyDefinition} associated to a value of type {@code URI}.
 *
 * @see PropertyDefinition
 * @see UrlPropertyValue
 * @since 1.0.0
 */
public class UrlPropertyDefinition extends PropertyDefinition {

  /**
   * Create a new instance of UrlMetadata, a metadata of type {@code MetadataType.URL}.
   *
   * @param code The metadata code
   */
  public UrlPropertyDefinition(String code) {
    super(code, PropertyType.URL);
  }
}

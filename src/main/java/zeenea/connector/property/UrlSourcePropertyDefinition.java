package zeenea.connector.property;

/**
 * Sub-class of {@link SourcePropertyDefinition} associated to a value of type {@code URI}.
 *
 * @see SourcePropertyDefinition
 * @see UrlSourcePropertyValue
 * @since 1.0.0
 */
public class UrlSourcePropertyDefinition extends SourcePropertyDefinition {

  /**
   * Create a new instance of UrlMetadata, a metadata of type {@code MetadataType.URL}.
   *
   * @param code The metadata code
   */
  public UrlSourcePropertyDefinition(String code) {
    super(code, SourcePropertyType.URL);
  }
}

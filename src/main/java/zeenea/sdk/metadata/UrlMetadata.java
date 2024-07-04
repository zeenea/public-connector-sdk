package zeenea.sdk.metadata;

/**
 * Sub-class of {@link Metadata} associated to a value of type {@code URI}.
 *
 * @see Metadata
 * @see UrlMetadataValue
 * @since 1.0.0
 * @deprecated since 2.0.0, see 'zeenea.connector' package
 */
@Deprecated
public class UrlMetadata extends Metadata {

  /**
   * Create a new instance of UrlMetadata, a metadata of type {@code MetadataType.URL}.
   *
   * @param code The metadata code
   */
  public UrlMetadata(String code) {
    super(code, MetadataType.URL);
  }
}

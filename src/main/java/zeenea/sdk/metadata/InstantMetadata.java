package zeenea.sdk.metadata;

/**
 * Sub-class of {@link Metadata} associated to a value of type {@code Instant}.
 *
 * @see Metadata
 * @see InstantMetadataValue
 * @since 1.0.0
 * @deprecated since 2.0.0, see 'zeenea.connector' package
 */
@Deprecated
public class InstantMetadata extends Metadata {

  /**
   * Create a new instance of InstantMetadata, a metadata of type {@code MetadataType.INSTANT}.
   *
   * @param code The metadata code
   */
  public InstantMetadata(String code) {
    super(code, MetadataType.INSTANT);
  }
}

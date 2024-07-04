package zeenea.sdk.metadata;

/**
 * The type of the underlying value of a {@link Metadata}.
 *
 * @see Metadata
 * @see StringMetadataValue
 * @see NumberMetadataValue
 * @see UrlMetadataValue
 * @see InstantMetadataValue
 * @since 1.0.0
 * @deprecated since 2.0.0, see 'zeenea.connector' package
 */
@Deprecated
public enum MetadataType {
  STRING,
  NUMBER,
  URL,
  INSTANT
}

package zeenea.connector.property;

/**
 * The type of the underlying value of a {@link SourcePropertyDefinition}.
 *
 * @see SourcePropertyDefinition
 * @see StringSourcePropertyValue
 * @see NumberSourcePropertyValue
 * @see UrlSourcePropertyValue
 * @see InstantSourcePropertyValue
 * @since 1.0.0
 */
public enum SourcePropertyType {
  STRING,
  NUMBER,
  URL,
  INSTANT
}

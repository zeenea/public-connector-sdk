package zeenea.connector.property;

/** Enum representing different types of properties. */
public enum PropertyType {
  /**
   * Represents a string property type.
   *
   * <p>String property value cannot exceed 255 characters.
   */
  STRING,

  /**
   * Represents a long text property type.
   *
   * <p>Long text property value is not indexed and searchable in data catalog.
   */
  LONG_TEXT,

  /** Represents a number property type. */
  NUMBER,

  /** Represents a URL property type. */
  URL,

  /** Represents an instant property type. */
  INSTANT,

  /** Represents a tag property type. */
  TAG
}

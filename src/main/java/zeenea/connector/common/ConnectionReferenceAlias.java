package zeenea.connector.common;

import java.util.Objects;
import java.util.StringJoiner;
import org.jetbrains.annotations.NotNull;

/** Represents an alias for a connection reference in the Zeenea Data Catalog. */
public final class ConnectionReferenceAlias implements ConnectionReference {
  /** The value of the connection reference alias. */
  @NotNull private final String value;

  /**
   * Constructs a ConnectionReferenceAlias instance using the provided builder.
   *
   * @param builder the builder used to create the ConnectionReferenceAlias instance
   */
  private ConnectionReferenceAlias(Builder builder) {
    this.value = Objects.requireNonNull(builder.value);
  }

  /**
   * Creates a new ConnectionReferenceAlias instance with the specified value.
   *
   * @param value the value of the connection reference alias
   * @return a new ConnectionReferenceAlias instance
   */
  public ConnectionReferenceAlias of(@NotNull String value) {
    return new ConnectionReferenceAlias.Builder(value).build();
  }

  /**
   * Gets the value of the connection reference alias.
   *
   * @return the value of the connection reference alias
   */
  @Override
  public @NotNull String getValue() {
    return this.value;
  }

  /**
   * Checks if this connection reference alias is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this connection reference alias is equal to the specified object, otherwise
   *     false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ConnectionReferenceAlias)) return false;
    ConnectionReferenceAlias that = (ConnectionReferenceAlias) o;
    return Objects.equals(getValue(), that.getValue());
  }

  /**
   * Computes the hash code for this connection reference alias.
   *
   * @return the hash code of this connection reference alias
   */
  @Override
  public int hashCode() {
    return Objects.hash(getValue());
  }

  /**
   * Returns a string representation of this connection reference alias.
   *
   * @return a string representation of this connection reference alias
   */
  @Override
  public String toString() {
    return new StringJoiner(", ", ConnectionReferenceAlias.class.getSimpleName() + "[", "]")
        .add("value='" + value + "'")
        .toString();
  }

  /**
   * Creates a new builder for a ConnectionReferenceAlias instance.
   *
   * @param value the value of the connection reference alias
   * @return a new Builder instance
   */
  public static Builder builder(@NotNull String value) {
    return new Builder(value);
  }

  /** Builder class for creating instances of ConnectionReferenceAlias. */
  public static class Builder {

    /** The value of the connection reference alias. */
    private final String value;

    /**
     * Constructs a Builder instance with the specified value.
     *
     * @param value the value of the connection reference alias
     */
    private Builder(String value) {
      this.value = value;
    }

    /**
     * Builds and returns the ConnectionReferenceAlias instance.
     *
     * @return the created ConnectionReferenceAlias instance
     */
    public ConnectionReferenceAlias build() {
      return new ConnectionReferenceAlias(this);
    }
  }
}

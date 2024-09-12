package zeenea.connector.common;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/** Represents a code for a connection reference in the Zeenea Data Catalog. */
public final class ConnectionReferenceCode implements ConnectionReference {
  /** The value of the connection reference code. */
  @NotNull private final String value;

  /**
   * Constructs a ConnectionReferenceCode instance using the provided builder.
   *
   * @param builder the builder used to create the ConnectionReferenceCode instance
   */
  private ConnectionReferenceCode(Builder builder) {
    this.value = Objects.requireNonNull(builder.value);
  }

  /**
   * Creates a new ConnectionReferenceCode instance with the specified value.
   *
   * @param value the value of the connection reference code
   * @return a new ConnectionReferenceCode instance
   */
  public static ConnectionReferenceCode of(@NotNull String value) {
    return new Builder().value(value).build();
  }

  /**
   * Gets the value of the connection reference code.
   *
   * @return the value of the connection reference code
   */
  @Override
  public @NotNull String getValue() {
    return this.value;
  }

  /**
   * Checks if this connection reference code is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this connection reference code is equal to the specified object, otherwise
   *     false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof ConnectionReferenceCode)) return false;
    ConnectionReferenceCode that = (ConnectionReferenceCode) o;
    return Objects.equals(getValue(), that.getValue());
  }

  /**
   * Computes the hash code for this connection reference code.
   *
   * @return the hash code of this connection reference code
   */
  @Override
  public int hashCode() {
    return Objects.hash(getValue());
  }

  /**
   * Returns a string representation of this connection reference code.
   *
   * @return a string representation of this connection reference code
   */
  @Override
  public String toString() {
    return "ConnectionReferenceCode{" + "value='" + value + "'}";
  }

  /**
   * Creates a new builder for a ConnectionReferenceCode instance.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder class for creating instances of ConnectionReferenceCode. */
  public static class Builder {

    /** The value of the connection reference alias. */
    private String value;

    /**
     * Sets the value of the connection reference alias.
     *
     * @param value the value of the connection reference alias
     * @return the Builder instance
     */
    public Builder value(@NotNull String value) {
      this.value = value;
      return this;
    }

    /**
     * Builds and returns the ConnectionReferenceCode instance.
     *
     * @return the created ConnectionReferenceCode instance
     */
    public ConnectionReferenceCode build() {
      return new ConnectionReferenceCode(this);
    }
  }
}

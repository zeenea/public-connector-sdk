package zeenea.connector.common;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/** Represents an identification property in the Zeenea Data Catalog. */
public class IdentificationProperty {
  /** The key of the identification property. */
  @NotNull private final String key;

  /** The value of the identification property. */
  @NotNull private final String value;

  /**
   * Constructs an IdentificationProperty instance using the provided builder.
   *
   * @param builder the builder used to create the IdentificationProperty instance
   */
  private IdentificationProperty(Builder builder) {
    this.key = Objects.requireNonNull(builder.key, "key");
    this.value = Objects.requireNonNull(builder.value, "value");
  }

  /**
   * Creates a new IdentificationProperty instance with the specified key and value.
   *
   * @param key the key of the identification property
   * @param value the value of the identification property
   * @return a new IdentificationProperty instance
   */
  public static IdentificationProperty of(@NotNull String key, @NotNull String value) {
    return builder().key(key).value(value).build();
  }

  /**
   * Gets the key of the identification property.
   *
   * @return the key of the identification property
   */
  public @NotNull String getKey() {
    return key;
  }

  /**
   * Gets the value of the identification property.
   *
   * @return the value of the identification property
   */
  public @NotNull String getValue() {
    return value;
  }

  /**
   * Creates a new builder for an IdentificationProperty instance.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Checks if this identification property is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this identification property is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IdentificationProperty that = (IdentificationProperty) o;
    return Objects.equals(key, that.key) && Objects.equals(value, that.value);
  }

  /**
   * Computes the hash code for this identification property.
   *
   * @return the hash code of this identification property
   */
  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }

  /**
   * Returns a string representation of this identification property.
   *
   * @return a string representation of this identification property
   */
  @Override
  public String toString() {
    return "IdentificationProperty{" + "key='" + key + "', value='" + value + "'}";
  }

  /** Builder class for creating instances of IdentificationProperty. */
  public static class Builder {

    /** The key of the identification property. */
    private String key;

    /** The value of the identification property. */
    private String value;

    /**
     * Sets the key of the identification property.
     *
     * @param key the key of the identification property
     * @return the Builder instance
     */
    public Builder key(@NotNull String key) {
      this.key = key;
      return this;
    }

    /**
     * Sets the value of the identification property.
     *
     * @param value the value of the identification property
     * @return the Builder instance
     */
    public Builder value(@NotNull String value) {
      this.value = value;
      return this;
    }

    /**
     * Builds and returns the IdentificationProperty instance.
     *
     * @return the created IdentificationProperty instance
     */
    public IdentificationProperty build() {
      return new IdentificationProperty(this);
    }
  }
}

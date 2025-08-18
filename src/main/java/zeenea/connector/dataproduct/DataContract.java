package zeenea.connector.dataproduct;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Represents a data contract with a specific type and source. */
public final class DataContract {

  /**
   * Enum representing the type of the data contract.
   *
   * @deprecated since 2.9.0
   */
  public enum Type {
    Custom,
    DataContractDotCom
  }

  /** The type of the data contract. */
  @Deprecated(
      since = "Deprecated since version 2.9.0. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  @Nullable
  private final Type type;

  /** The source of the data contract. */
  @NotNull private final String source;

  /**
   * Constructs a DataContract instance using the provided builder.
   *
   * @param builder the builder used to create the DataContract instance
   */
  private DataContract(Builder builder) {
    this.type = Objects.requireNonNullElse(builder.type, Type.Custom);
    this.source = Objects.requireNonNull(builder.source, "source");
  }

  /**
   * Creates a DataContract instance with the specified type and source.
   *
   * @param type the type of the data contract
   * @param source the source of the data contract
   * @return a new DataContract instance
   */
  public static DataContract of(@Nullable Type type, @NotNull String source) {
    return builder().type(type).source(source).build();
  }

  /**
   * Gets the type of the data contract.
   *
   * @return the type of the data contract
   */
  @Deprecated(
      since = "Deprecated since 2.9.0. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public @Nullable Type getType() {
    return type;
  }

  /**
   * Gets the source of the data contract.
   *
   * @return the source of the data contract
   */
  public @NotNull String getSource() {
    return source;
  }

  /**
   * Checks if this DataContract is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this DataContract is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DataContract that = (DataContract) o;
    return type == that.type && Objects.equals(source, that.source);
  }

  /**
   * Computes the hash code for this DataContract.
   *
   * @return the hash code of this DataContract
   */
  @Override
  public int hashCode() {
    return Objects.hash(type, source);
  }

  /**
   * Returns a string representation of this DataContract.
   *
   * @return a string representation of this DataContract
   */
  @Override
  public String toString() {
    return "DataContract{" + "source='" + source + "', type=" + type + "}";
  }

  /**
   * Creates a new builder for the DataContract class.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder class for creating instances of DataContract. */
  public static class Builder {

    /** The type of the data contract. */
    @Deprecated(
        since = "Deprecated since 2.9.0. Scheduled for removal in version 3.0.0.",
        forRemoval = true)
    private Type type;

    /** The source of the data contract. */
    private String source;

    /**
     * Sets the type of the data contract.
     *
     * @param type the type of the data contract
     * @return the builder instance
     */
    @Deprecated(
        since = "Deprecated since 2.9.0. Scheduled for removal in version 3.0.0.",
        forRemoval = true)
    public Builder type(@Nullable Type type) {
      this.type = Objects.requireNonNullElse(type, Type.Custom);
      return this;
    }

    /**
     * Sets the source of the data contract.
     *
     * @param source the source of the data contract
     * @return the builder instance
     */
    public Builder source(@NotNull String source) {
      this.source = source;
      return this;
    }

    /**
     * Builds and returns a DataContract instance.
     *
     * @return the created DataContract instance
     */
    public DataContract build() {
      return new DataContract(this);
    }
  }
}

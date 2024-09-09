package zeenea.connector.dataproduct;

import java.util.Objects;
import java.util.StringJoiner;
import org.jetbrains.annotations.NotNull;

/** Represents a data contract with a specific type and source. */
public final class DataContract {

  /** Enum representing the type of the data contract. */
  public enum Type {
    Custom,
    DataContractDotCom
  }

  /** The type of the data contract. */
  @NotNull private final Type type;

  /** The source of the data contract. */
  @NotNull private final String source;

  /**
   * Constructs a DataContract instance using the provided builder.
   *
   * @param builder the builder used to create the DataContract instance
   */
  private DataContract(Builder builder) {
    this.type = Objects.requireNonNull(builder.type, "type");
    this.source = Objects.requireNonNull(builder.source, "source");
  }

  /**
   * Creates a DataContract instance with the specified type and source.
   *
   * @param type the type of the data contract
   * @param source the source of the data contract
   * @return a new DataContract instance
   */
  public static DataContract of(@NotNull Type type, @NotNull String source) {
    return builder().type(type).source(source).build();
  }

  /**
   * Gets the type of the data contract.
   *
   * @return the type of the data contract
   */
  public @NotNull Type getType() {
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
    return new StringJoiner(", ", DataContract.class.getSimpleName() + "[", "]")
        .add("type=" + type)
        .add("source='" + source + "'")
        .toString();
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
    private Type type;

    /** The source of the data contract. */
    private String source;

    /**
     * Sets the type of the data contract.
     *
     * @param type the type of the data contract
     * @return the builder instance
     */
    public Builder type(@NotNull Type type) {
      this.type = type;
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

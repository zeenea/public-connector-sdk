package zeenea.connector.dataset;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.exception.ExceptionUtils;

/** Represents the partitioning information of a dataset. */
public final class Partitioning {

  /** The column used for partitioning. */
  @Deprecated(
      since =
          "Deprecated since version 2.1.0, use targetDatasetIdentifier instead. Will be removed in SDK version 3.0.0",
      forRemoval = true)
  @Nullable
  private final String column;

  /** The identifier of the column used for partitioning. */
  @Nullable private final ItemIdentifier columnIdentifier;

  /** The type of partitioning. */
  @NotNull private final String partitionType;

  /**
   * Constructs a Partitioning instance using the provided builder.
   *
   * @param builder the builder used to create the Partitioning instance
   */
  private Partitioning(Builder builder) {
    ExceptionUtils.requireNonNullOrEmpty("partitionType", builder.partitionType);
    this.column = builder.column;
    this.columnIdentifier = builder.columnIdentifier;
    this.partitionType = builder.partitionType;
  }

  /**
   * Gets the column used for partitioning.
   *
   * @return the column used for partitioning
   */
  @Deprecated(
      since =
          "Deprecated since version 2.1.0, use targetDatasetIdentifier instead. Will be removed in SDK version 3.0.0",
      forRemoval = true)
  public @Nullable String getColumn() {
    return column;
  }

  /**
   * Gets the identifier of the column used for partitioning.
   *
   * @return the identifier of the column used for partitioning
   */
  public @Nullable ItemIdentifier getColumnIdentifier() {
    return columnIdentifier;
  }

  /**
   * Gets the type of partitioning.
   *
   * @return the type of partitioning
   */
  public @NotNull String getPartitionType() {
    return partitionType;
  }

  /**
   * Checks if this Partitioning is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this Partitioning is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Partitioning that = (Partitioning) o;
    return Objects.equals(column, that.column)
        && Objects.equals(columnIdentifier, that.columnIdentifier)
        && Objects.equals(partitionType, that.partitionType);
  }

  /**
   * Computes the hash code for this Partitioning.
   *
   * @return the hash code of this Partitioning
   */
  @Override
  public int hashCode() {
    return Objects.hash(column, columnIdentifier, partitionType);
  }

  /**
   * Returns a string representation of this Partitioning.
   *
   * @return a string representation of this Partitioning
   */
  @Override
  public String toString() {
    return "Partitioning{"
        + "column='"
        + column
        + "', columnIdentifier="
        + columnIdentifier
        + ", partitionType='"
        + partitionType
        + "'}";
  }

  /**
   * Creates a new builder for the Partitioning class.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder class for creating instances of Partitioning. */
  public static class Builder {

    /** The column used for partitioning. */
    private String column;

    /** The identifier of the column used for partitioning. */
    private ItemIdentifier columnIdentifier;

    /** The type of partitioning. */
    private String partitionType;

    /** Protected constructor for the Builder class. */
    private Builder() {}

    /**
     * Sets the column used for partitioning.
     *
     * @param column the column used for partitioning
     * @return the builder instance
     */
    @Deprecated(
        since =
            "Deprecated since version 2.1.0, use targetDatasetIdentifier instead. Will be removed in SDK version 3.0.0",
        forRemoval = true)
    public Builder column(@NotNull String column) {
      this.column = column;
      return this;
    }

    /**
     * Sets the identifier of the column used for partitioning.
     *
     * @param columnIdentifier the identifier of the column used for partitioning
     * @return the builder instance
     */
    public Builder columnIdentifier(@NotNull ItemIdentifier columnIdentifier) {
      this.columnIdentifier = columnIdentifier;
      return this;
    }

    /**
     * Sets the type of partitioning.
     *
     * @param partitionType the type of partitioning
     * @return the builder instance
     */
    public Builder partitionType(@NotNull String partitionType) {
      this.partitionType = partitionType;
      return this;
    }

    /**
     * Builds and returns a Partitioning instance.
     *
     * @return the created Partitioning instance
     */
    public Partitioning build() {
      return new Partitioning(this);
    }
  }
}

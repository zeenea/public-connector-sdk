package zeenea.connector.dataset;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.Item;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.common.ItemReference;
import zeenea.connector.exception.ExceptionUtils;

/** Represents a dataset which is a specialized type of Item. */
public final class Dataset extends Item {

  /** The list of fields in the dataset. */
  @NotNull private final List<Field> fields;

  /** The list of primary keys in the dataset. */
  @NotNull private final List<String> primaryKeys;

  /** The list of foreign keys in the dataset. */
  @NotNull private final List<ForeignKey> foreignKeys;

  /** The list of partitions in the dataset. */
  @NotNull private final List<Partitioning> partitions;

  /** The list of source datasets for the dataset. */
  @NotNull private final List<ItemReference> sourceDatasets;

  /**
   * Constructs a Dataset instance using the provided builder.
   *
   * @param builder the builder used to create the Dataset instance
   */
  private Dataset(Builder builder) {
    super(builder);
    ExceptionUtils.requireNonNull("fields", builder.fields);
    this.fields = List.copyOf(builder.fields);
    this.primaryKeys = List.copyOf(builder.primaryKeys);
    this.foreignKeys = List.copyOf(builder.foreignKeys);
    this.partitions = List.copyOf(builder.partitions);
    this.sourceDatasets = List.copyOf(builder.sourceDatasets);
  }

  /**
   * Gets the list of fields in the dataset.
   *
   * @return the list of fields
   */
  public @NotNull List<Field> getFields() {
    return fields;
  }

  /**
   * Gets the list of foreign keys in the dataset.
   *
   * @return the list of foreign keys
   */
  public @NotNull List<ForeignKey> getForeignKeys() {
    return foreignKeys;
  }

  /**
   * Gets the list of partitions in the dataset.
   *
   * @return the list of partitions
   */
  public @NotNull List<Partitioning> getPartitions() {
    return partitions;
  }

  /**
   * Gets the list of primary keys in the dataset.
   *
   * @return the list of primary keys
   */
  public @NotNull List<String> getPrimaryKeys() {
    return primaryKeys;
  }

  /**
   * Gets the list of source datasets for the dataset.
   *
   * @return the list of source datasets
   */
  public @NotNull List<ItemReference> getSourceDatasets() {
    return sourceDatasets;
  }

  /**
   * Checks if this Dataset is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this Dataset is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Dataset dataset = (Dataset) o;
    return Objects.equals(fields, dataset.fields)
        && Objects.equals(primaryKeys, dataset.primaryKeys)
        && Objects.equals(foreignKeys, dataset.foreignKeys)
        && Objects.equals(partitions, dataset.partitions)
        && Objects.equals(sourceDatasets, dataset.sourceDatasets);
  }

  /**
   * Computes the hash code for this Dataset.
   *
   * @return the hash code of this Dataset
   */
  @Override
  public int hashCode() {
    return Objects.hash(fields, primaryKeys, foreignKeys, partitions, sourceDatasets);
  }

  /**
   * Returns a string representation of this Dataset.
   *
   * @return a string representation of this Dataset
   */
  @Override
  public String toString() {
    return new StringJoiner(", ", Dataset.class.getSimpleName() + "[", "]")
        .add("fields=" + fields)
        .add("primaryKeys=" + primaryKeys)
        .add("foreignKeys=" + foreignKeys)
        .add("partitions=" + partitions)
        .add("sourceDatasets=" + sourceDatasets)
        .toString();
  }

  /**
   * Creates a new builder for the Dataset class.
   *
   * @param id the identifier of the dataset
   * @param name the name of the dataset
   * @return a new Builder instance
   */
  public static Builder builder(@NotNull ItemIdentifier id, @NotNull String name) {
    return new Builder(id, name);
  }

  /** Builder class for creating instances of Dataset. */
  public static class Builder extends Item.Builder<Dataset, Builder> {

    /** The list of fields in the dataset. */
    private List<Field> fields = new ArrayList<>();

    /** The list of primary keys in the dataset. */
    private List<String> primaryKeys = new ArrayList<>();

    /** The list of foreign keys in the dataset. */
    private List<ForeignKey> foreignKeys = new ArrayList<>();

    /** The list of partitions in the dataset. */
    private List<Partitioning> partitions = new ArrayList<>();

    /** The list of source datasets for the dataset. */
    private List<ItemReference> sourceDatasets = new ArrayList<>();

    /**
     * Constructs a Builder instance with the specified identifier and name.
     *
     * @param id the identifier of the dataset
     * @param name the name of the dataset
     */
    private Builder(@NotNull ItemIdentifier id, @NotNull String name) {
      super(id, name);
    }

    /**
     * Sets the list of fields in the dataset.
     *
     * @param fields the list of fields
     * @return the builder instance
     */
    public Builder fields(@NotNull List<Field> fields) {
      this.fields = fields;
      return this;
    }

    /**
     * Sets the list of primary keys in the dataset.
     *
     * @param primaryKeys the list of primary keys
     * @return the builder instance
     */
    public Builder primaryKeys(@NotNull List<String> primaryKeys) {
      this.primaryKeys = primaryKeys;
      return this;
    }

    /**
     * Sets the list of foreign keys in the dataset.
     *
     * @param foreignKeys the list of foreign keys
     * @return the builder instance
     */
    public Builder foreignKeys(@NotNull List<ForeignKey> foreignKeys) {
      this.foreignKeys = foreignKeys;
      return this;
    }

    /**
     * Sets the list of partitions in the dataset.
     *
     * @param partitions the list of partitions
     * @return the builder instance
     */
    public Builder partitions(@NotNull List<Partitioning> partitions) {
      this.partitions = partitions;
      return this;
    }

    /**
     * Sets the list of source datasets for the dataset.
     *
     * @param sourceDatasets the list of source datasets
     * @return the builder instance
     */
    public Builder sourceDatasets(@NotNull List<ItemReference> sourceDatasets) {
      this.sourceDatasets = sourceDatasets;
      return this;
    }

    /**
     * Builds and returns a Dataset instance.
     *
     * @return the created Dataset instance
     */
    @Override
    protected Dataset performBuild() {
      return new Dataset(this);
    }
  }
}

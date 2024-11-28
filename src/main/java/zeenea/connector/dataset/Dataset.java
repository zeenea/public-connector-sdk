package zeenea.connector.dataset;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.Item;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.common.ItemReference;
import zeenea.connector.exception.ExceptionUtils;
import zeenea.connector.field.Field;

/** Represents a dataset which is a specialized type of Item. */
public final class Dataset extends Item {

  /** The list of fields in the dataset. */
  @NotNull private final List<Field> fields;

  /** The list of primary keys in the dataset. */
  @Deprecated(
      since =
          "Deprecated since version 2.1.0, use primaryKeyIdentifiers instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  @NotNull
  private final List<String> primaryKeys;

  /** The list of identifiers for primary keys in the dataset. */
  @NotNull private final List<ItemIdentifier> primaryKeyIdentifiers;

  /** The list of foreign keys in the dataset. */
  @NotNull private final List<ForeignKey> foreignKeys;

  /**
   * The list of source datasets associated with the dataset.
   *
   * <p>Used to declare downstream dataset lineage by referencing ItemIdentifier of source datasets.
   */
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
    this.primaryKeyIdentifiers = List.copyOf(builder.primaryKeyIdentifiers);
    this.foreignKeys = List.copyOf(builder.foreignKeys);
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
   * Gets the list of primary keys in the dataset.
   *
   * @return the list of primary keys
   */
  @Deprecated(
      since =
          "Deprecated since version 2.1.0, use getPrimaryKeyIdentifiers instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public @NotNull List<String> getPrimaryKeys() {
    return primaryKeys;
  }

  /**
   * Gets the list of identifiers for primary keys in the dataset.
   *
   * @return the list of identifiers for primary keys
   */
  public @NotNull List<ItemIdentifier> getPrimaryKeyIdentifiers() {
    return primaryKeyIdentifiers;
  }

  /**
   * Gets the list of source datasets references.
   *
   * @return the list of source datasets references
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
    return Objects.equals(getId(), dataset.getId())
        && Objects.equals(getName(), dataset.getName())
        && Objects.equals(getDescription(), dataset.getDescription())
        && Objects.equals(getContacts(), dataset.getContacts())
        && Objects.equals(getProperties(), dataset.getProperties())
        && Objects.equals(fields, dataset.fields)
        && Objects.equals(primaryKeys, dataset.primaryKeys)
        && Objects.equals(primaryKeyIdentifiers, dataset.primaryKeyIdentifiers)
        && Objects.equals(foreignKeys, dataset.foreignKeys)
        && Objects.equals(sourceDatasets, dataset.sourceDatasets);
  }

  /**
   * Computes the hash code for this Dataset.
   *
   * @return the hash code of this Dataset
   */
  @Override
  public int hashCode() {
    return Objects.hash(
        getId(),
        getName(),
        getDescription(),
        getContacts(),
        getProperties(),
        fields,
        primaryKeys,
        primaryKeyIdentifiers,
        foreignKeys,
        sourceDatasets);
  }

  /**
   * Returns a string representation of this Dataset.
   *
   * @return a string representation of this Dataset
   */
  @Override
  public String toString() {
    return "Dataset{"
        + "id="
        + getId()
        + ", name='"
        + getName()
        + "', description="
        + getDescription()
        + ", contactRelations="
        + getContacts()
        + ", properties="
        + getProperties()
        + ", fields="
        + fields
        + ", primaryKeys="
        + primaryKeys
        + ", primaryKeyIdentifiers="
        + primaryKeyIdentifiers
        + ", foreignKeys="
        + foreignKeys
        + ", sourceDatasets="
        + sourceDatasets
        + '}';
  }

  /**
   * Creates a new builder for the Dataset class.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder class for creating instances of Dataset. */
  public static class Builder extends Item.Builder<Dataset, Builder> {

    /** The list of fields in the dataset. */
    private List<Field> fields = new ArrayList<>();

    /** The list of primary keys in the dataset. */
    @Deprecated(
        since =
            "Deprecated since version 2.1.0, use primaryKeyIdentifiers instead. Scheduled for removal in version 3.0.0.",
        forRemoval = true)
    private List<String> primaryKeys = new ArrayList<>();

    /** The list of identifiers for primary keys in the dataset. */
    private List<ItemIdentifier> primaryKeyIdentifiers = new ArrayList<>();

    /** The list of foreign keys in the dataset. */
    private List<ForeignKey> foreignKeys = new ArrayList<>();

    /** The list of source datasets for the dataset. */
    private List<ItemReference> sourceDatasets = new ArrayList<>();

    /**
     * Sets the list of fields in the dataset.
     *
     * @param fields the list of fields
     * @return the builder instance
     */
    public Builder fields(@NotNull List<Field> fields) {
      this.fields = List.copyOf(fields);
      return this;
    }

    /**
     * Sets the list of fields in the dataset.
     *
     * @param fields the list of fields
     * @return the builder instance
     */
    public Builder fields(Field... fields) {
      this.fields = List.of(fields);
      return this;
    }

    /**
     * Sets the list of primary keys in the dataset.
     *
     * @param primaryKeys the list of primary keys
     * @return the builder instance
     */
    @Deprecated(
        since =
            "Deprecated since version 2.1.0, use primaryKeyIdentifiers instead. Scheduled for removal in version 3.0.0.",
        forRemoval = true)
    public Builder primaryKeys(@NotNull List<String> primaryKeys) {
      this.primaryKeys = List.copyOf(primaryKeys);
      return this;
    }

    /**
     * Sets the list of primary keys in the dataset.
     *
     * @param primaryKeys the list of primary keys
     * @return the builder instance
     */
    @Deprecated(
        since =
            "Deprecated since version 2.1.0, use primaryKeyIdentifiers instead. Scheduled for removal in version 3.0.0.",
        forRemoval = true)
    public Builder primaryKeys(String... primaryKeys) {
      this.primaryKeys = List.of(primaryKeys);
      return this;
    }

    /**
     * Sets the list of identifiers for primary keys in the dataset.
     *
     * @param primaryKeyIdentifiers the list of identifiers for primary keys
     * @return the builder instance
     */
    public Builder primaryKeyIdentifiers(@NotNull List<ItemIdentifier> primaryKeyIdentifiers) {
      this.primaryKeyIdentifiers = List.copyOf(primaryKeyIdentifiers);
      return this;
    }

    /**
     * Sets the list of identifiers for primary keys in the dataset.
     *
     * @param primaryKeyIdentifiers the list of identifiers for primary keys
     * @return the builder instance
     */
    public Builder primaryKeyIdentifiers(ItemIdentifier... primaryKeyIdentifiers) {
      this.primaryKeyIdentifiers = List.of(primaryKeyIdentifiers);
      return this;
    }

    /**
     * Sets the list of foreign keys in the dataset.
     *
     * @param foreignKeys the list of foreign keys
     * @return the builder instance
     */
    public Builder foreignKeys(@NotNull List<ForeignKey> foreignKeys) {
      this.foreignKeys = List.copyOf(foreignKeys);
      return this;
    }

    /**
     * Sets the list of foreign keys in the dataset.
     *
     * @param foreignKeys the list of foreign keys
     * @return the builder instance
     */
    public Builder foreignKeys(ForeignKey... foreignKeys) {
      this.foreignKeys = List.of(foreignKeys);
      return this;
    }

    /**
     * Sets the list of source datasets for the dataset.
     *
     * @param sourceDatasets the list of source datasets
     * @return the builder instance
     */
    public Builder sourceDatasets(@NotNull List<ItemReference> sourceDatasets) {
      this.sourceDatasets = List.copyOf(sourceDatasets);
      return this;
    }

    /**
     * Sets the target item references for the DataProcess.
     *
     * @param sourceDatasets the target item references
     * @return this Builder instance
     */
    public Builder sourceDatasets(ItemReference... sourceDatasets) {
      this.sourceDatasets = List.of(sourceDatasets);
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

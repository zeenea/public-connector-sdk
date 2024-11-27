package zeenea.connector.dataset;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zeenea.connector.common.ItemIdentifier;

/**
 * Represents a foreign key in a dataset. This class is immutable and provides methods to access the
 * foreign key details.
 */
public final class ForeignKey {

  /** The target dataset of the foreign key. */
  @Deprecated(
      since =
          "Deprecated since version 2.1.0, use targetDatasetIdentifier instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  @Nullable
  private final String targetDataset;

  /** The identifier for the targeted dataset of the foreign key. */
  @Nullable private final ItemIdentifier targetDatasetIdentifier;

  /** The source fields of the foreign key. */
  @Deprecated(
      since =
          "Deprecated since version 2.1.0, use sourceFieldIdentifiers instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  @Nullable
  private final List<String> sourceFields;

  /** The list of identifiers for source fields of the foreign key. */
  @Nullable private final List<ItemIdentifier> sourceFieldIdentifiers;

  /** The target fields of the foreign key. */
  @Deprecated(
      since =
          "Deprecated since version 2.1.0, use targetFieldIdentifiers instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  @Nullable
  private final List<String> targetFields;

  /** The list of identifiers for target fields of the foreign key. */
  @Nullable private final List<ItemIdentifier> targetFieldIdentifiers;

  /** The name of the foreign key. */
  @NotNull private final String name;

  /**
   * Constructs a ForeignKey instance using the provided builder.
   *
   * @param builder the builder used to create the ForeignKey instance
   */
  private ForeignKey(Builder builder) {
    this.targetDataset = builder.targetDataset;
    this.targetDatasetIdentifier = builder.targetDatasetIdentifier;
    this.sourceFields = List.copyOf(builder.sourceFields);
    this.sourceFieldIdentifiers = List.copyOf(builder.sourceFieldIdentifiers);
    this.targetFields = List.copyOf(builder.targetFields);
    this.targetFieldIdentifiers = List.copyOf(builder.targetFieldIdentifiers);
    this.name = Objects.requireNonNull(builder.name);
  }

  /**
   * Gets the target dataset of the foreign key.
   *
   * @return the target dataset of the foreign key
   */
  @Deprecated(
      since =
          "Deprecated since version 2.1.0, use getTargetDatasetIdentifier instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public @Nullable String getTargetDataset() {
    return targetDataset;
  }

  /**
   * Gets the identifier for the targeted dataset of the foreign key.
   *
   * @return the identifier for the targeted dataset of the foreign key
   */
  public @Nullable ItemIdentifier getTargetDatasetIdentifier() {
    return targetDatasetIdentifier;
  }

  /**
   * Gets the source fields of the foreign key.
   *
   * @return the source fields of the foreign key
   */
  @Deprecated(
      since =
          "Deprecated since version 2.1.0, use getSourceFieldIdentifiers instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public @Nullable List<String> getSourceFields() {
    return sourceFields;
  }

  /**
   * Gets the list of identifiers for source fields of the foreign key.
   *
   * @return the list of identifiers for source fields of the foreign key
   */
  public @Nullable List<ItemIdentifier> getSourceFieldIdentifiers() {
    return sourceFieldIdentifiers;
  }

  /**
   * Gets the target fields of the foreign key.
   *
   * @return the target fields of the foreign key
   */
  @Deprecated(
      since =
          "Deprecated since version 2.1.0, use getTargetFieldIdentifiers instead. Scheduled for removal in version 3.0.0.",
      forRemoval = true)
  public @Nullable List<String> getTargetFields() {
    return targetFields;
  }

  /**
   * Gets the list of identifiers for target fields of the foreign key.
   *
   * @return the list of identifiers for target fields of the foreign key
   */
  public @Nullable List<ItemIdentifier> getTargetFieldIdentifiers() {
    return targetFieldIdentifiers;
  }

  /**
   * Gets the name of the foreign key.
   *
   * @return the name of the foreign key, or null if not present
   */
  public @NotNull String getName() {
    return name;
  }

  /**
   * Checks if this ForeignKey is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this ForeignKey is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ForeignKey that = (ForeignKey) o;
    return Objects.equals(targetDataset, that.targetDataset)
        && Objects.equals(sourceFields, that.sourceFields)
        && Objects.equals(targetFields, that.targetFields)
        && Objects.equals(name, that.name);
  }

  /**
   * Computes the hash code for this ForeignKey.
   *
   * @return the hash code of this ForeignKey
   */
  @Override
  public int hashCode() {
    return Objects.hash(targetDataset, sourceFields, targetFields, name);
  }

  /**
   * Returns a string representation of this ForeignKey.
   *
   * @return a string representation of this ForeignKey
   */
  @Override
  public String toString() {
    return "ForeignKey{"
        + "name='"
        + name
        + "', targetDataset='"
        + targetDataset
        + "', sourceFields="
        + sourceFields
        + ", targetFields="
        + targetFields
        + "}";
  }

  /**
   * Creates a new builder for the ForeignKey class.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder class for creating instances of ForeignKey. */
  public static class Builder {

    /** The targeted dataset of the foreign key. */
    @Deprecated(
        since =
            "Deprecated since version 2.1.0, use targetDatasetIdentifier instead. Scheduled for removal in version 3.0.0.",
        forRemoval = true)
    private String targetDataset;

    /** The identifier for the targeted dataset of the foreign key. */
    private ItemIdentifier targetDatasetIdentifier;

    /** The source fields of the foreign key. */
    @Deprecated(
        since =
            "Deprecated since version 2.1.0, use sourceFieldIdentifiers instead. Scheduled for removal in version 3.0.0.",
        forRemoval = true)
    private List<String> sourceFields = new ArrayList<>();

    /** The list of identifiers for source fields of the foreign key. */
    private List<ItemIdentifier> sourceFieldIdentifiers = new ArrayList<>();

    /** The target fields of the foreign key. */
    @Deprecated(
        since =
            "Deprecated since version 2.1.0, use targetFieldIdentifiers instead. Scheduled for removal in version 3.0.0.",
        forRemoval = true)
    @NotNull
    private List<String> targetFields = new ArrayList<>();

    /** The list of identifiers for target fields of the foreign key. */
    private List<ItemIdentifier> targetFieldIdentifiers = new ArrayList<>();

    /** The name of the foreign key. */
    private String name;

    /** Protected constructor for the Builder class. */
    private Builder() {}

    /**
     * Sets the target dataset of the foreign key.
     *
     * @param targetDataset the target dataset of the foreign key
     * @return the builder instance
     */
    @Deprecated(
        since =
            "Deprecated since version 2.1.0, use targetDatasetIdentifier instead. Scheduled for removal in version 3.0.0.",
        forRemoval = true)
    public Builder targetDataset(@NotNull String targetDataset) {
      this.targetDataset = targetDataset;
      return this;
    }

    /**
     * Sets the identifier for the targeted dataset of the foreign key
     *
     * @param targetDatasetIdentifier the identifier for the targeted dataset
     * @return the builder instance
     */
    public Builder targetDatasetIdentifier(@NotNull ItemIdentifier targetDatasetIdentifier) {
      this.targetDatasetIdentifier = targetDatasetIdentifier;
      return this;
    }

    /**
     * Sets the source fields of the foreign key.
     *
     * @param sourceFields the source fields of the foreign key
     * @return the builder instance
     */
    @Deprecated(
        since =
            "Deprecated since version 2.1.0, use sourceFieldIdentifiers instead. Scheduled for removal in version 3.0.0.",
        forRemoval = true)
    public Builder sourceFields(@NotNull List<String> sourceFields) {
      this.sourceFields = List.copyOf(sourceFields);
      return this;
    }

    /**
     * Sets the source fields of the foreign key.
     *
     * @param sourceFields the source fields of the foreign key
     * @return the builder instance
     */
    @Deprecated(
        since =
            "Deprecated since version 2.1.0, use sourceFieldIdentifiers instead. Scheduled for removal in version 3.0.0.",
        forRemoval = true)
    public Builder sourceFields(String... sourceFields) {
      this.sourceFields = List.of(sourceFields);
      return this;
    }

    /**
     * Sets the list of identifiers for source fields of the foreign key.
     *
     * @param sourceFieldIdentifiers the list of identifiers for source fields
     * @return the builder instance
     */
    public Builder sourceFieldIdentifiers(@NotNull List<ItemIdentifier> sourceFieldIdentifiers) {
      this.sourceFieldIdentifiers = List.copyOf(sourceFieldIdentifiers);
      return this;
    }

    /**
     * Sets the list of identifiers for source fields of the foreign key.
     *
     * @param sourceFieldIdentifiers the list of identifiers for source fields
     * @return the builder instance
     */
    public Builder sourceFieldIdentifiers(ItemIdentifier... sourceFieldIdentifiers) {
      this.sourceFieldIdentifiers = List.of(sourceFieldIdentifiers);
      return this;
    }

    /**
     * Sets the target fields of the foreign key.
     *
     * @param targetFields the target fields of the foreign key
     * @return the builder instance
     */
    @Deprecated(
        since =
            "Deprecated since version 2.1.0, use targetFieldIdentifiers instead. Scheduled for removal in version 3.0.0.",
        forRemoval = true)
    public Builder targetFields(@NotNull List<String> targetFields) {
      this.targetFields = List.copyOf(targetFields);
      return this;
    }

    /**
     * Sets the target fields of the foreign key.
     *
     * @param targetFields the target fields of the foreign key
     * @return the builder instance
     */
    @Deprecated(
        since =
            "Deprecated since version 2.1.0, use targetFieldIdentifiers instead. Scheduled for removal in version 3.0.0.",
        forRemoval = true)
    public Builder targetFields(String... targetFields) {
      this.targetFields = List.of(targetFields);
      return this;
    }

    /**
     * Sets the list of identifiers for the target fields of the foreign key.
     *
     * @param targetFieldIdentifiers the list of identifiers for the target fields
     * @return the builder instance
     */
    public Builder targetFieldIdentifiers(@NotNull List<ItemIdentifier> targetFieldIdentifiers) {
      this.targetFieldIdentifiers = List.copyOf(targetFieldIdentifiers);
      return this;
    }

    /**
     * Sets the list of identifiers for the target fields of the foreign key.
     *
     * @param targetFieldIdentifiers the list of identifiers for the target fields
     * @return the builder instance
     */
    public Builder targetFieldIdentifiers(ItemIdentifier... targetFieldIdentifiers) {
      this.targetFieldIdentifiers = List.of(targetFieldIdentifiers);
      return this;
    }

    /**
     * Sets the name of the foreign key.
     *
     * @param name the name of the foreign key
     * @return the builder instance
     */
    public Builder name(@NotNull String name) {
      this.name = name;
      return this;
    }

    /**
     * Builds and returns a ForeignKey instance.
     *
     * @return the created ForeignKey instance
     */
    public ForeignKey build() {
      return new ForeignKey(this);
    }
  }
}

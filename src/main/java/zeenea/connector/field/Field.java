package zeenea.connector.field;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.common.ItemReference;
import zeenea.connector.dataset.DataType;
import zeenea.connector.property.PropertyValue;

/** Represents a field in a dataset. */
public class Field {

  /** The identifier of the field. */
  @NotNull private final ItemIdentifier id;

  /** The name of the field. */
  @NotNull private final String name;

  /** The data type of the field. */
  @NotNull private final DataType dataType;

  /** The native type of the field. */
  @NotNull private final String nativeType;

  /** The native index of the field. */
  private final int nativeIndex;

  /** Indicates if the field is nullable. */
  private final boolean nullable;

  /** Indicates if the field is multivalued. */
  private final boolean multivalued;

  /** The description of the field. */
  @Nullable private final String description;

  /** The properties of the field. */
  @NotNull private final Map<String, PropertyValue> properties;

  /**
   * The list of source fields associated with the field.
   *
   * <p>Used to declare downstream field to field lineage by referencing ItemIdentifier of source
   * fields.
   *
   * <p>Only works if parent object (Dataset or Visualization) reference source datasets through
   * sourceDatasets attribute.
   */
  @NotNull private final List<ItemReference> sourceFields;

  /** The glossaryRefs of the item. */
  private final List<String> glossaryRefs;

  /** The customItemRefs of the item. */
  private final List<String> customItemRefs;

  /**
   * Constructs a Field instance using the provided builder.
   *
   * @param builder the builder used to create the Field instance
   */
  public Field(Field.Builder<?, ?> builder) {
    this.id = Objects.requireNonNull(builder.id, "id");
    this.name = Objects.requireNonNull(builder.name, "name");
    this.dataType = builder.dataType;
    this.nativeType = builder.nativeType;
    this.nativeIndex = builder.nativeIndex;
    this.nullable = builder.nullable;
    this.multivalued = builder.multivalued;
    this.description = builder.description;
    this.properties = new HashMap<>(builder.properties);
    this.sourceFields = builder.sourceFields;
    this.glossaryRefs = List.copyOf(builder.glossaryRefs);
    this.customItemRefs = List.copyOf(builder.customItemRefs);
  }

  /**
   * Gets the data type of the field.
   *
   * @return the data type of the field
   */
  public @NotNull DataType getDataType() {
    return dataType;
  }

  /**
   * Gets the description of the field.
   *
   * @return the description of the field, or null if not present
   */
  public @NotNull Optional<String> getDescription() {
    return Optional.ofNullable(description);
  }

  /**
   * Gets the item identifier associated with the field.
   *
   * @return the field item identifier
   */
  public @NotNull ItemIdentifier getId() {
    return id;
  }

  /**
   * Gets the properties of the field.
   *
   * @return the properties of the field
   */
  public @NotNull Map<String, PropertyValue> getProperties() {
    return properties;
  }

  /**
   * Checks if the field is multivalued.
   *
   * @return true if the field is multivalued, otherwise false
   */
  public boolean isMultivalued() {
    return multivalued;
  }

  /**
   * Gets the name of the field.
   *
   * @return the name of the field
   */
  public @NotNull String getName() {
    return name;
  }

  /**
   * Gets the native index of the field.
   *
   * @return the native index of the field
   */
  public int getNativeIndex() {
    return nativeIndex;
  }

  /**
   * Gets the native type of the field.
   *
   * @return the native type of the field
   */
  public @NotNull String getNativeType() {
    return nativeType;
  }

  /**
   * Checks if the field is nullable.
   *
   * @return true if the field is nullable, otherwise false
   */
  public boolean isNullable() {
    return nullable;
  }

  /**
   * Gets the list of source field references.
   *
   * @return the list of source field references
   */
  public List<ItemReference> getSourceField() {
    return sourceFields;
  }

  /**
   * Gets the list of glossary refs.
   *
   * @return the list of glossary refs
   */
  public Collection<String> getGlossaryRefs() {
    return glossaryRefs;
  }

  /**
   * Gets the list of custom item refs.
   *
   * @return the list of custom item refs
   */
  public Collection<String> getCustomItemRefs() {
    return customItemRefs;
  }

  /**
   * Checks if this Field is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this Field is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Field that = (Field) o;
    return Objects.equals(id, that.id)
        && nativeIndex == that.nativeIndex
        && nullable == that.nullable
        && multivalued == that.multivalued
        && Objects.equals(name, that.name)
        && dataType == that.dataType
        && Objects.equals(nativeType, that.nativeType)
        && Objects.equals(description, that.description)
        && Objects.equals(properties, that.properties)
        && Objects.equals(sourceFields, that.sourceFields);
  }

  /**
   * Computes the hash code for this Field.
   *
   * @return the hash code of this Field
   */
  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        name,
        dataType,
        nativeType,
        nativeIndex,
        nullable,
        multivalued,
        description,
        properties,
        sourceFields);
  }

  /**
   * Returns a string representation of this Field.
   *
   * @return a string representation of this Field
   */
  @Override
  public String toString() {
    return "Field{"
        + "id="
        + id
        + ",name='"
        + name
        + "', dataType="
        + dataType
        + ", nativeType='"
        + nativeType
        + "', nativeIndex="
        + nativeIndex
        + ", nullable="
        + nullable
        + ", multivalued="
        + multivalued
        + ", description='"
        + description
        + "', properties="
        + properties
        + ", sourceFields="
        + sourceFields
        + "}";
  }

  /**
   * Creates a new builder for the Field class.
   *
   * @param <T> the type of the builder
   * @return a new Builder instance
   */
  public static <T> Builder<T, ? extends Builder<T, ?>> builder() {
    return new Builder<>();
  }

  /**
   * Builder class for creating instances of Field.
   *
   * @param <T> the type of the field
   * @param <THIS> the type of the builder itself
   */
  public static class Builder<T, THIS extends Builder<T, ?>> {

    /** The item identifier associated with the field. */
    private ItemIdentifier id;

    /** The name of the field. */
    private String name;

    /** The data type of the field. */
    private DataType dataType;

    /** The native type of the field. */
    private String nativeType;

    /** The native index of the field. */
    private int nativeIndex;

    /** Indicates if the field is nullable. */
    private boolean nullable;

    /** Indicates if the field is multivalued. */
    private boolean multivalued;

    /** The description of the field. */
    private String description;

    /** The properties of the field. */
    private Map<String, PropertyValue> properties = Map.of();

    /** The list of source field associated with the field. */
    private List<ItemReference> sourceFields = new ArrayList<>();

    /** The list of glossary refs associated with the field. */
    private List<String> glossaryRefs = List.of();

    /** The list of custom item refs associated with the field. */
    private List<String> customItemRefs = List.of();

    /** Protected constructor for the Builder class. */
    protected Builder() {}

    /**
     * Sets the name of the field.
     *
     * @param name the name of the field
     * @return the builder instance
     */
    public THIS name(@NotNull String name) {
      this.name = name;
      return self();
    }

    /**
     * Sets the data type of the field.
     *
     * @param dataType the data type of the field
     * @return the builder instance
     */
    public THIS dataType(@NotNull DataType dataType) {
      this.dataType = dataType;
      return self();
    }

    /**
     * Sets the native type of the field.
     *
     * @param nativeType the native type of the field
     * @return the builder instance
     */
    public THIS nativeType(@NotNull String nativeType) {
      this.nativeType = nativeType;
      return self();
    }

    /**
     * Sets the native index of the field.
     *
     * @param nativeIndex the native index of the field
     * @return the builder instance
     */
    public THIS nativeIndex(int nativeIndex) {
      this.nativeIndex = nativeIndex;
      return self();
    }

    /**
     * Sets the identifier associated with the field.
     *
     * @param id the item identifier
     * @return the builder instance
     */
    public THIS id(@NotNull ItemIdentifier id) {
      this.id = id;
      return self();
    }

    /**
     * Sets whether the field is nullable.
     *
     * @param nullable true if the field is nullable, otherwise false
     * @return the builder instance
     */
    public THIS nullable(boolean nullable) {
      this.nullable = nullable;
      return self();
    }

    /**
     * Sets whether the field is multivalued.
     *
     * @param multivalued true if the field is multivalued, otherwise false
     * @return the builder instance
     */
    public THIS multivalued(boolean multivalued) {
      this.multivalued = multivalued;
      return self();
    }

    /**
     * Sets the description of the field.
     *
     * @param description the description of the field
     * @return the builder instance
     */
    public THIS description(@Nullable String description) {
      this.description = description;
      return self();
    }

    /**
     * Sets the properties of the field.
     *
     * @param metadata the properties of the field
     * @return the builder instance
     */
    public THIS properties(@NotNull Map<String, PropertyValue> metadata) {
      this.properties = Map.copyOf(metadata);
      return self();
    }

    /**
     * Sets the list of source fields associated with the field.
     *
     * @param sourceFields the list of source fields associated with the field
     * @return the Builder instance
     */
    public THIS sourceFields(@NotNull List<ItemReference> sourceFields) {
      this.sourceFields = List.copyOf(sourceFields);
      return self();
    }

    /**
     * Sets the list of source fields associated with the field.
     *
     * @param sourceFields the list of source fields associated with the field
     * @return the Builder instance
     */
    public THIS sourceFields(ItemReference... sourceFields) {
      this.sourceFields = List.of(sourceFields);
      return self();
    }

    /**
     * Sets the list of glossary refs associated with the field.
     *
     * @param glossaryRefs the list of glossary refs associated with the field
     * @return the Builder instance
     */
    public THIS glossaryRefs(@NotNull Collection<String> glossaryRefs) {
      this.glossaryRefs = List.copyOf(glossaryRefs);
      return self();
    }

    /**
     * Sets the list of glossary refs associated with the field.
     *
     * @param glossaryRefs the list of glossary refs associated with the field
     * @return the Builder instance
     */
    public THIS glossaryRefs(String... glossaryRefs) {
      this.glossaryRefs = List.of(glossaryRefs);
      return self();
    }

    /**
     * Sets the list of custom item refs associated with the field.
     *
     * @param customItemRefs the list of custom item refs associated with the field
     * @return the Builder instance
     */
    public THIS customItemRefs(@NotNull Collection<String> customItemRefs) {
      this.customItemRefs = List.copyOf(customItemRefs);
      return self();
    }

    /**
     * Sets the list of custom item refs associated with the field.
     *
     * @param customItemRefs the list of custom item refs associated with the field
     * @return the Builder instance
     */
    public THIS customItemRefs(String... customItemRefs) {
      this.customItemRefs = List.of(customItemRefs);
      return self();
    }

    /**
     * Builds and returns a Field instance.
     *
     * @return the created Field instance
     */
    public Field build() {
      return new Field(this);
    }

    /**
     * Returns the builder instance.
     *
     * @return the builder instance
     */
    @SuppressWarnings("unchecked")
    private THIS self() {
      return (THIS) this;
    }
  }
}

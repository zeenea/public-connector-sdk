package zeenea.connector.dataset;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zeenea.connector.exception.ExceptionUtils;
import zeenea.connector.property.PropertyValue;

/** Represents a field in a dataset. */
public class Field {

  /** The name of the field. */
  @NotNull private final String name;

  /** The data type of the field. */
  @NotNull private final DataType dataType;

  /** The native type of the field. */
  @NotNull private final String nativeType;

  /** The native index of the field. */
  private final int nativeIndex;

  /** The list of keys associated with the field. */
  @NotNull private final List<String> keys;

  /** Indicates if the field is nullable. */
  private final boolean nullable;

  /** Indicates if the field is multivalued. */
  private final boolean multivalued;

  /** The description of the field. */
  @Nullable private final String description;

  /** The properties of the field. */
  @NotNull private final Map<String, PropertyValue> properties;

  /**
   * Constructs a Field instance using the provided builder.
   *
   * @param builder the builder used to create the Field instance
   */
  public Field(Field.Builder<?, ?> builder) {
    ExceptionUtils.requireNonNullOrEmpty("keys", builder.keys);
    this.name = Objects.requireNonNull(builder.name, "name");
    this.dataType = builder.dataType;
    this.nativeType = builder.nativeType;
    this.nativeIndex = builder.nativeIndex;
    this.keys = List.copyOf(builder.keys);
    this.nullable = builder.nullable;
    this.multivalued = builder.multivalued;
    this.description = builder.description;
    this.properties = new HashMap<>(builder.properties);
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
   * Gets the list of keys associated with the field.
   *
   * @return the list of keys
   */
  public @NotNull List<String> getKeys() {
    return keys;
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
    return nativeIndex == that.nativeIndex
        && nullable == that.nullable
        && multivalued == that.multivalued
        && Objects.equals(name, that.name)
        && dataType == that.dataType
        && Objects.equals(nativeType, that.nativeType)
        && Objects.equals(keys, that.keys)
        && Objects.equals(description, that.description)
        && Objects.equals(properties, that.properties);
  }

  /**
   * Computes the hash code for this Field.
   *
   * @return the hash code of this Field
   */
  @Override
  public int hashCode() {
    return Objects.hash(
        name,
        dataType,
        nativeType,
        nativeIndex,
        keys,
        nullable,
        multivalued,
        description,
        properties);
  }

  /**
   * Returns a string representation of this Field.
   *
   * @return a string representation of this Field
   */
  @Override
  public String toString() {
    return new StringJoiner(", ", Field.class.getSimpleName() + "[", "]")
        .add("dataType=" + dataType)
        .add("name='" + name + "'")
        .add("nativeType='" + nativeType + "'")
        .add("nativeIndex=" + nativeIndex)
        .add("keys=" + keys)
        .add("nullable=" + nullable)
        .add("multivalued=" + multivalued)
        .add("description='" + description + "'")
        .add("properties=" + properties)
        .toString();
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

    /** The name of the field. */
    private String name;

    /** The data type of the field. */
    private DataType dataType;

    /** The native type of the field. */
    private String nativeType;

    /** The native index of the field. */
    private int nativeIndex;

    /** The list of keys associated with the field. */
    private List<String> keys;

    /** Indicates if the field is nullable. */
    private boolean nullable;

    /** Indicates if the field is multivalued. */
    private boolean multivalued;

    /** The description of the field. */
    private String description;

    /** The properties of the field. */
    private Map<String, PropertyValue> properties = Map.of();

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
     * Sets the list of keys associated with the field.
     *
     * @param keys the list of keys
     * @return the builder instance
     */
    public THIS keys(@NotNull List<String> keys) {
      this.keys = keys;
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

package zeenea.connector.visualization;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.common.ItemReference;
import zeenea.connector.dataset.Field;

/** Represents a field in a visualization. */
public final class VisualizationField extends Field {

  /** The type of the field. */
  @NotNull private final VisualizationFieldType fieldType;

  /** The list of item references associated with the field. */
  @NotNull private final List<ItemReference> itemReferences;

  /**
   * Constructs a VisualizationField instance using the builder.
   *
   * @param builder the builder to construct the VisualizationField instance
   */
  private VisualizationField(Builder builder) {
    super(builder);
    this.fieldType = Objects.requireNonNull(builder.fieldType, "fieldType");
    this.itemReferences = builder.itemReferences;
  }

  /**
   * Gets the type of the field.
   *
   * @return the type of the field
   */
  public @NotNull VisualizationFieldType getFieldType() {
    return fieldType;
  }

  /**
   * Gets the list of item references associated with the field.
   *
   * @return the list of item references associated with the field
   */
  public List<ItemReference> getSourceItemReference() {
    return itemReferences;
  }

  /**
   * Checks if this VisualizationField is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this VisualizationField is equal to the specified object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    VisualizationField visualizationField = (VisualizationField) o;
    return getNativeIndex() == visualizationField.getNativeIndex()
        && isNullable() == visualizationField.isNullable()
        && isMultivalued() == visualizationField.isMultivalued()
        && Objects.equals(getName(), visualizationField.getName())
        && getDataType() == visualizationField.getDataType()
        && Objects.equals(getNativeType(), visualizationField.getNativeType())
        && Objects.equals(getKeys(), visualizationField.getKeys())
        && Objects.equals(getDescription(), visualizationField.getDescription())
        && Objects.equals(getProperties(), visualizationField.getProperties())
        && fieldType == visualizationField.fieldType
        && Objects.equals(itemReferences, visualizationField.itemReferences);
  }

  /**
   * Returns the hash code of this VisualizationField.
   *
   * @return the hash code of this VisualizationField
   */
  @Override
  public int hashCode() {
    return Objects.hash(
        getName(),
        getDataType(),
        getNativeType(),
        getNativeIndex(),
        getKeys(),
        isNullable(),
        isMultivalued(),
        getDescription(),
        getProperties(),
        fieldType,
        itemReferences);
  }

  /**
   * Returns the string representation of this VisualizationField.
   *
   * @return the string representation of this VisualizationField
   */
  @Override
  public String toString() {
    return "VisualizationField{"
        + "name='"
        + getName()
        + "', keys="
        + getKeys()
        + ", dataType="
        + getDataType()
        + ", nativeType='"
        + getNativeType()
        + "', nativeIndex="
        + getNativeIndex()
        + ", nullable="
        + isNullable()
        + ", multivalued="
        + isMultivalued()
        + ", description='"
        + getDescription()
        + "', properties="
        + getProperties()
        + "fieldType="
        + fieldType
        + ", itemReferences="
        + itemReferences
        + '}';
  }

  /**
   * Creates a builder for VisualizationField.
   *
   * @return a new Builder instance
   */
  public static @NotNull Builder builder() {
    return new Builder();
  }

  /** Builder class for constructing VisualizationField instances. */
  public static class Builder extends Field.Builder<VisualizationField, Builder> {

    /** The type of the field. */
    private VisualizationFieldType fieldType;

    /** The list of item references associated with the field. */
    private List<ItemReference> itemReferences = new ArrayList<>();

    /** Constructs a Builder instance. */
    private Builder() {}

    /**
     * Sets the type of the field.
     *
     * @param fieldType the type of the field
     * @return the Builder instance
     */
    public Builder fieldType(VisualizationFieldType fieldType) {
      this.fieldType = fieldType;
      return this;
    }

    /**
     * Sets the list of item references associated with the field.
     *
     * @param itemReferences the list of item references associated with the field
     * @return the Builder instance
     */
    public Builder itemReferences(@NotNull List<ItemReference> itemReferences) {
      this.itemReferences = List.copyOf(itemReferences);
      return this;
    }

    /**
     * Sets the list of item references associated with the field.
     *
     * @param itemReferences the list of item references associated with the field
     * @return the Builder instance
     */
    public Builder itemReferences(ItemReference... itemReferences) {
      this.itemReferences = List.of(itemReferences);
      return this;
    }

    /**
     * Builds and returns a VisualizationField instance.
     *
     * @return a new VisualizationField instance
     */
    @Override
    public VisualizationField build() {
      return new VisualizationField(this);
    }
  }
}

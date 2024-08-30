package zeenea.connector.dataproduct;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zeenea.connector.dataset.Dataset;
import zeenea.connector.exception.ExceptionUtils;
import zeenea.connector.property.*;

/** Represents an output port in a data product. */
public final class OutputPort {

  /** The name of the output port. */
  @NotNull private final String name;

  /** The description of the output port. */
  @Nullable private final String description;

  /** The data contract associated with the output port. */
  @NotNull private final DataContract dataContract;

  /** The list of datasets associated with the output port. */
  @NotNull private final List<Dataset> datasets;

  /** The properties of the output port. */
  @NotNull private final Map<String, PropertyValue> properties;

  /**
   * Constructs an OutputPort instance using the provided builder.
   *
   * @param builder the builder used to create the OutputPort instance
   */
  private OutputPort(Builder builder) {
    ExceptionUtils.requireNonNullOrEmpty("name", builder.name);
    Objects.requireNonNull(builder.dataContract, "dataContract");
    ExceptionUtils.requireNonNull("dataset", builder.datasets);
    ExceptionUtils.requireNonNull("properties", builder.properties);
    this.name = builder.name;
    this.description = builder.description;
    this.dataContract = builder.dataContract;
    this.datasets = builder.datasets;
    this.properties = builder.properties;
  }

  /**
   * Gets the name of the output port.
   *
   * @return the name of the output port
   */
  public @NotNull String getName() {
    return name;
  }

  /**
   * Gets the description of the output port.
   *
   * @return an Optional containing the description of the output port if present, otherwise an
   *     empty Optional
   */
  public Optional<String> getDescription() {
    return Optional.ofNullable(description);
  }

  /**
   * Gets the data contract associated with the output port.
   *
   * @return the data contract associated with the output port
   */
  public @NotNull DataContract getDataContract() {
    return dataContract;
  }

  /**
   * Gets the list of datasets associated with the output port.
   *
   * @return the list of datasets associated with the output port
   */
  public @NotNull List<Dataset> getDatasets() {
    return datasets;
  }

  /**
   * Gets the properties of the output port.
   *
   * @return the properties of the output port
   */
  public @NotNull Map<String, PropertyValue> getProperties() {
    return properties;
  }

  /**
   * Checks if this OutputPort is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this OutputPort is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OutputPort)) return false;
    OutputPort that = (OutputPort) o;
    return Objects.equals(getName(), that.getName())
        && Objects.equals(getDescription(), that.getDescription())
        && Objects.equals(getDataContract(), that.getDataContract())
        && Objects.equals(getDatasets(), that.getDatasets())
        && Objects.equals(getProperties(), that.getProperties());
  }

  /**
   * Computes the hash code for this OutputPort.
   *
   * @return the hash code of this OutputPort
   */
  @Override
  public int hashCode() {
    return Objects.hash(
        getName(), getDescription(), getDataContract(), getDatasets(), getProperties());
  }

  /**
   * Returns a string representation of this OutputPort.
   *
   * @return a string representation of this OutputPort
   */
  @Override
  public String toString() {
    return new StringJoiner(", ", OutputPort.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("description='" + description + "'")
        .add("dataContract=" + dataContract)
        .add("datasets=" + datasets)
        .add("properties=" + properties)
        .toString();
  }

  /**
   * Creates a new builder for the OutputPort class.
   *
   * @param name the name of the output port
   * @param dataContract the data contract associated with the output port
   * @return a new Builder instance
   */
  public static Builder builder(@NotNull String name, @NotNull DataContract dataContract) {
    return new Builder(name, dataContract);
  }

  /** Builder class for creating instances of OutputPort. */
  public static class Builder {

    /** The name of the output port. */
    private final String name;

    /** The description of the output port. */
    private String description = null;

    /** The data contract associated with the output port. */
    private final DataContract dataContract;

    /** The list of datasets associated with the output port. */
    private final List<Dataset> datasets = new ArrayList<>();

    /** The properties of the output port. */
    private final Map<String, PropertyValue> properties = new HashMap<>();

    /**
     * Constructs a Builder instance with the specified name and data contract.
     *
     * @param name the name of the output port
     * @param dataContract the data contract associated with the output port
     */
    private Builder(String name, DataContract dataContract) {
      this.name = name;
      this.dataContract = dataContract;
    }

    /**
     * Sets the description of the output port.
     *
     * @param description the description of the output port
     * @return the builder instance
     */
    public Builder description(@Nullable String description) {
      this.description = description;
      return this;
    }

    /**
     * Adds a dataset to the output port.
     *
     * @param dataset the dataset to add
     * @return the builder instance
     */
    public Builder addDataset(@NotNull Dataset dataset) {
      this.datasets.add(dataset);
      return this;
    }

    /**
     * Adds a collection of datasets to the output port.
     *
     * @param datasets the collection of datasets to add
     * @return the builder instance
     */
    public Builder addDatasets(@NotNull Collection<Dataset> datasets) {
      this.datasets.addAll(datasets);
      return this;
    }

    /**
     * Adds a string property to the output port.
     *
     * @param propertyDefinition the property definition
     * @param value the property value
     * @return the builder instance
     */
    public Builder addProperty(
        @NotNull StringPropertyDefinition propertyDefinition, @NotNull String value) {
      return addProperty(propertyDefinition, new StringPropertyValue(value));
    }

    /**
     * Adds a string property value to the output port.
     *
     * @param propertyDefinition the property definition
     * @param value the property value
     * @return the builder instance
     */
    public Builder addProperty(
        @NotNull StringPropertyDefinition propertyDefinition, @NotNull StringPropertyValue value) {
      return putProperty(propertyDefinition, value);
    }

    /**
     * Adds a long number property to the output port.
     *
     * @param propertyDefinition the property definition
     * @param value the property value
     * @return the builder instance
     */
    public Builder addProperty(@NotNull NumberPropertyDefinition propertyDefinition, long value) {
      return addProperty(propertyDefinition, BigDecimal.valueOf(value));
    }

    /**
     * Adds a double number property to the output port.
     *
     * @param propertyDefinition the property definition
     * @param value the property value
     * @return the builder instance
     */
    public Builder addProperty(@NotNull NumberPropertyDefinition propertyDefinition, double value) {
      return addProperty(propertyDefinition, BigDecimal.valueOf(value));
    }

    /**
     * Adds a BigDecimal number property to the output port.
     *
     * @param propertyDefinition the property definition
     * @param value the property value
     * @return the builder instance
     */
    public Builder addProperty(
        @NotNull NumberPropertyDefinition propertyDefinition, @NotNull BigDecimal value) {
      return addProperty(propertyDefinition, new NumberPropertyValue(value));
    }

    /**
     * Adds a number property value to the output port.
     *
     * @param propertyDefinition the property definition
     * @param value the property value
     * @return the builder instance
     */
    public Builder addProperty(
        @NotNull NumberPropertyDefinition propertyDefinition, @NotNull NumberPropertyValue value) {
      return putProperty(propertyDefinition, value);
    }

    /**
     * Adds a URL property to the output port.
     *
     * @param propertyDefinition the property definition
     * @param value the property value
     * @return the builder instance
     */
    public Builder addProperty(
        @NotNull UrlPropertyDefinition propertyDefinition, @NotNull URI value) {
      return addProperty(propertyDefinition, new UrlPropertyValue(value));
    }

    /**
     * Adds a URL property with a label to the output port.
     *
     * @param propertyDefinition the property definition
     * @param uri the URI value
     * @param label the label for the URI
     * @return the builder instance
     */
    public Builder addProperty(
        @NotNull UrlPropertyDefinition propertyDefinition,
        @NotNull URI uri,
        @NotNull String label) {
      return addProperty(propertyDefinition, new UrlPropertyValue(uri, label));
    }

    /**
     * Adds a URL property value to the output port.
     *
     * @param propertyDefinition the property definition
     * @param value the property value
     * @return the builder instance
     */
    public Builder addProperty(
        @NotNull UrlPropertyDefinition propertyDefinition, @NotNull UrlPropertyValue value) {
      return putProperty(propertyDefinition, value);
    }

    /**
     * Adds an instant property to the output port.
     *
     * @param propertyDefinition the property definition
     * @param value the property value
     * @return the builder instance
     */
    public Builder addProperty(
        @NotNull InstantPropertyDefinition propertyDefinition, @NotNull Instant value) {
      return addProperty(propertyDefinition, new InstantPropertyValue(value));
    }

    /**
     * Adds an instant property value to the output port.
     *
     * @param propertyDefinition the property definition
     * @param value the property value
     * @return the builder instance
     */
    public Builder addProperty(
        @NotNull InstantPropertyDefinition propertyDefinition,
        @NotNull InstantPropertyValue value) {
      return putProperty(propertyDefinition, value);
    }

    /**
     * Adds a tag property value to the output port.
     *
     * @param propertyDefinition the property definition
     * @param value the property value
     * @return the builder instance
     */
    public Builder addProperty(
        @NotNull TagPropertyDefinition propertyDefinition, @NotNull TagPropertyValue value) {
      return putProperty(propertyDefinition, value);
    }

    /**
     * Puts a property value into the properties map.
     *
     * @param propertyDefinition the property definition
     * @param value the property value
     * @return the builder instance
     */
    private Builder putProperty(PropertyDefinition propertyDefinition, PropertyValue value) {
      this.properties.put(propertyDefinition.getCode(), value);
      return this;
    }

    /**
     * Builds and returns an OutputPort instance.
     *
     * @return the created OutputPort instance
     */
    public OutputPort build() {
      return new OutputPort(this);
    }
  }
}
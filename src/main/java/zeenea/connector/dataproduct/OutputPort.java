package zeenea.connector.dataproduct;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.dataset.Dataset;
import zeenea.connector.exception.ExceptionUtils;
import zeenea.connector.property.*;

/** Represents an output port in a data product. */
public final class OutputPort {

  /** The identifier of the output port. */
  @NotNull private final ItemIdentifier id;

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
    Objects.requireNonNull(builder.id, "id");
    ExceptionUtils.requireNonNullOrEmpty("name", builder.name);
    Objects.requireNonNull(builder.dataContract, "dataContract");
    ExceptionUtils.requireNonNull("dataset", builder.datasets);
    ExceptionUtils.requireNonNull("properties", builder.properties);
    this.id = builder.id;
    this.name = builder.name;
    this.description = builder.description;
    this.dataContract = builder.dataContract;
    this.datasets = builder.datasets;
    this.properties = builder.properties;
  }

  /**
   * Gets the identifier of the output port.
   *
   * @return the identifier of the output port
   */
  public @NotNull ItemIdentifier getId() {
    return id;
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
    return Objects.equals(getId(), that.getId())
        && Objects.equals(getName(), that.getName())
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
        getId(), getName(), getDescription(), getDataContract(), getDatasets(), getProperties());
  }

  /**
   * Returns a string representation of this OutputPort.
   *
   * @return a string representation of this OutputPort
   */
  @Override
  public String toString() {
    return "OutputPort{"
        + "id="
        + id
        + ", dataContract="
        + dataContract
        + ", name='"
        + name
        + "', description='"
        + description
        + "', datasets="
        + datasets
        + ", properties="
        + properties
        + "}";
  }

  /**
   * Creates a new builder for the OutputPort class.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder class for creating instances of OutputPort. */
  public static class Builder {

    private ItemIdentifier id;

    private String name;

    private String description = null;

    private DataContract dataContract;

    /** The list of datasets associated with the output port. */
    private List<Dataset> datasets = Collections.emptyList();

    /** The properties of the output port. */
    private Map<String, PropertyValue> properties = Collections.emptyMap();

    /**
     * Sets the identifier of the output port.
     *
     * @param id the identifier of the output port
     * @return the builder instance
     */
    public Builder id(@NotNull ItemIdentifier id) {
      this.id = id;
      return this;
    }

    /**
     * Sets the name of the output port.
     *
     * @param name the name of the output port
     * @return the builder instance
     */
    public Builder name(String name) {
      this.name = name;
      return this;
    }

    /**
     * Sets the data contract associated with the output port.
     *
     * @param dataContract the data contract to set
     * @return the builder instance
     */
    public Builder dataContract(@NotNull DataContract dataContract) {
      this.dataContract = dataContract;
      return this;
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
     * Set a collection of datasets to the output port.
     *
     * @param datasets the collection of datasets to set
     * @return the builder instance
     */
    public Builder datasets(@NotNull Collection<Dataset> datasets) {
      this.datasets = List.copyOf(datasets);
      return this;
    }

    /**
     * Set a collection of datasets to the output port.
     *
     * @param datasets the collection of datasets to set
     * @return the builder instance
     */
    public Builder datasets(Dataset... datasets) {
      this.datasets = List.of(datasets);
      return this;
    }

    /**
     * Set the properties map.
     *
     * @param properties the collection of properties to set
     * @return the builder instance
     */
    public Builder properties(Map<String, PropertyValue> properties) {
      this.properties = Map.copyOf(properties);
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

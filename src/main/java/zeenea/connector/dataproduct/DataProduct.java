package zeenea.connector.dataproduct;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.Item;
import zeenea.connector.dataset.Dataset;
import zeenea.connector.exception.ExceptionUtils;
import zeenea.connector.process.DataProcess;

/** Represents a data product which is a specialized type of Item. */
public final class DataProduct extends Item {

  /** The list of input ports for the data product. */
  @NotNull private final List<InputPort> inputPorts;

  /** The list of output ports for the data product. */
  @NotNull private final List<OutputPort> outputPorts;

  /** The list of internal components for the data product. */
  @NotNull private final List<Item> internalComponents;

  /**
   * Constructs a DataProduct instance using the provided builder.
   *
   * @param builder the builder used to create the DataProduct instance
   */
  private DataProduct(Builder builder) {
    super(builder);
    ExceptionUtils.requireNonNull("inputPort", builder.inputPorts);
    ExceptionUtils.requireNonNull("outputPorts", builder.outputPorts);
    requireDatasetOrDataProcess(builder.internalComponents);
    inputPorts = List.copyOf(builder.inputPorts);
    outputPorts = List.copyOf(builder.outputPorts);
    internalComponents = List.copyOf(builder.internalComponents);
  }

  /**
   * Gets the list of input ports for the data product.
   *
   * @return the list of input ports
   */
  public @NotNull List<InputPort> getInputPorts() {
    return inputPorts;
  }

  /**
   * Gets the list of output ports for the data product.
   *
   * @return the list of output ports
   */
  public @NotNull List<OutputPort> getOutputPorts() {
    return outputPorts;
  }

  /**
   * Gets the list of internal components for the data product.
   *
   * @return the list of internal components
   */
  public @NotNull List<Item> getInternalComponents() {
    return internalComponents;
  }

  /**
   * Checks if this DataProduct is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this DataProduct is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DataProduct that = (DataProduct) o;
    return Objects.equals(inputPorts, that.inputPorts)
        && Objects.equals(outputPorts, that.outputPorts)
        && Objects.equals(internalComponents, that.internalComponents);
  }

  /**
   * Computes the hash code for this DataProduct.
   *
   * @return the hash code of this DataProduct
   */
  @Override
  public int hashCode() {
    return Objects.hash(inputPorts, outputPorts, internalComponents);
  }

  /**
   * Returns a string representation of this DataProduct.
   *
   * @return a string representation of this DataProduct
   */
  @Override
  public String toString() {
    return "DataProduct{"
        + "id="
        + getId()
        + ", name='"
        + getName()
        + "', description="
        + getDescription()
        + ", contactRelations="
        + getContactRelations()
        + ", properties="
        + getProperties()
        + ", inputPorts="
        + inputPorts
        + ", internalComponents="
        + internalComponents
        + ", outputPorts="
        + outputPorts
        + "}";
  }

  /**
   * Ensures that the internal components are Dataset or DataProcess instances.
   *
   * @param internalComponents the list of internal components to check
   * @throws IllegalArgumentException if any internal component is neither a Dataset nor a
   *     DataProcess
   */
  private static void requireDatasetOrDataProcess(@NotNull List<Item> internalComponents) {
    ExceptionUtils.requireNonNull("internalComponents", internalComponents);
    for (Item item : internalComponents) {
      if (!(item instanceof Dataset || item instanceof DataProcess)) {
        throw new IllegalArgumentException(
            String.format(
                "Internal component must be a %s or a %s and '%s' is a %s",
                Dataset.class, Process.class, item.getName(), item.getClass()));
      }
    }
  }

  /**
   * Creates a new builder for the DataProduct class.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder class for creating instances of DataProduct. */
  public static class Builder extends Item.Builder<DataProduct, Builder> {

    private List<InputPort> inputPorts = Collections.emptyList();

    private List<OutputPort> outputPorts = Collections.emptyList();

    private List<Item> internalComponents = Collections.emptyList();

    /**
     * Set a collection of input ports to the data product.
     *
     * @param inputPorts the collection of input ports to add
     * @return the builder instance
     */
    public Builder inputPorts(@NotNull Collection<InputPort> inputPorts) {
      this.inputPorts = List.copyOf(inputPorts);
      return this;
    }

    /**
     * Set a collection of input ports to the data product.
     *
     * @param inputPorts the collection of input ports to add
     * @return the builder instance
     */
    public Builder inputPorts(InputPort... inputPorts) {
      this.inputPorts = List.of(inputPorts);
      return this;
    }

    /**
     * Set a collection of output ports to the data product.
     *
     * @param outputPorts the collection of output ports to add
     * @return the builder instance
     */
    public Builder outputPorts(@NotNull Collection<OutputPort> outputPorts) {
      this.outputPorts = List.copyOf(outputPorts);
      return this;
    }

    /**
     * Set a collection of output ports to the data product.
     *
     * @param outputPorts the collection of output ports to add
     * @return the builder instance
     */
    public Builder outputPorts(OutputPort... outputPorts) {
      this.outputPorts = List.of(outputPorts);
      return this;
    }

    /**
     * Set a collection of items as internal components to the data product.
     *
     * @param items the collection of items to add
     * @return the builder instance
     */
    public Builder internalComponents(@NotNull Collection<Item> items) {
      this.internalComponents = List.copyOf(items);
      return this;
    }

    /**
     * Set a collection of items as internal components to the data product.
     *
     * @param items the collection of items to add
     * @return the builder instance
     */
    public Builder internalComponents(Item... items) {
      this.internalComponents = List.of(items);
      return this;
    }

    /**
     * Builds and returns a DataProduct instance.
     *
     * @return the created DataProduct instance
     */
    @Override
    protected DataProduct performBuild() {
      return new DataProduct(this);
    }
  }
}

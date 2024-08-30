package zeenea.connector.dataproduct;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.Item;
import zeenea.connector.common.ItemIdentifier;
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
    requireNonDataOrDataProcess(builder.internalComponents);
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
    return new StringJoiner(", ", DataProduct.class.getSimpleName() + "[", "]")
        .add("name='" + getName() + "'")
        .add("id=" + getId())
        .add("description=" + getDescription())
        .add("properties=" + getProperties())
        .add("contactRelations=" + getContactRelations())
        .add("inputPorts=" + inputPorts)
        .add("outputPorts=" + outputPorts)
        .add("internalComponents=" + internalComponents)
        .toString();
  }

  /**
   * Ensures that the internal components are not Dataset nor DataProcess instances.
   *
   * @param internalComponents the list of internal components to check
   * @throws IllegalArgumentException if any internal component is a Dataset or DataProcess
   */
  private static void requireNonDataOrDataProcess(@NotNull List<Item> internalComponents) {
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
   * @param id the identifier of the data product
   * @param name the name of the data product
   * @return a new Builder instance
   */
  public static Builder builder(@NotNull ItemIdentifier id, @NotNull String name) {
    return new Builder(id, name);
  }

  /** Builder class for creating instances of DataProduct. */
  public static class Builder extends Item.Builder<DataProduct, Builder> {

    /** The list of input ports for the data product. */
    private final List<InputPort> inputPorts = new ArrayList<>();

    /** The list of output ports for the data product. */
    private final List<OutputPort> outputPorts = new ArrayList<>();

    /** The list of internal components for the data product. */
    private final List<Item> internalComponents = new ArrayList<>();

    /**
     * Constructs a Builder instance with the specified identifier and name.
     *
     * @param id the identifier of the data product
     * @param name the name of the data product
     */
    private Builder(@NotNull ItemIdentifier id, @NotNull String name) {
      super(id, name);
    }

    /**
     * Adds an input port to the data product.
     *
     * @param inputPort the input port to add
     * @return the builder instance
     */
    public Builder addInputPort(@NotNull InputPort inputPort) {
      this.inputPorts.add(inputPort);
      return this;
    }

    /**
     * Adds a collection of input ports to the data product.
     *
     * @param inputPorts the collection of input ports to add
     * @return the builder instance
     */
    public Builder addInputPorts(@NotNull Collection<InputPort> inputPorts) {
      ExceptionUtils.requireNonNull("inputPorts", inputPorts);
      this.inputPorts.addAll(inputPorts);
      return this;
    }

    /**
     * Adds an output port to the data product.
     *
     * @param outputPort the output port to add
     * @return the builder instance
     */
    public Builder addOutputPort(@NotNull OutputPort outputPort) {
      this.outputPorts.add(outputPort);
      return this;
    }

    /**
     * Adds a collection of output ports to the data product.
     *
     * @param outputPorts the collection of output ports to add
     * @return the builder instance
     */
    public Builder addOutputPorts(@NotNull Collection<OutputPort> outputPorts) {
      this.outputPorts.addAll(outputPorts);
      return this;
    }

    /**
     * Adds a dataset as an internal component to the data product.
     *
     * @param dataset the dataset to add
     * @return the builder instance
     */
    public Builder addInternalComponent(@NotNull Dataset dataset) {
      this.internalComponents.add(dataset);
      return this;
    }

    /**
     * Adds a data process as an internal component to the data product.
     *
     * @param dataProcess the data process to add
     * @return the builder instance
     */
    public Builder addInternalComponent(@NotNull DataProcess dataProcess) {
      this.internalComponents.add(dataProcess);
      return this;
    }

    /**
     * Adds a collection of items as internal components to the data product.
     *
     * @param items the collection of items to add
     * @return the builder instance
     */
    public Builder addInternalComponents(@NotNull Collection<Item> items) {
      this.internalComponents.addAll(items);
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

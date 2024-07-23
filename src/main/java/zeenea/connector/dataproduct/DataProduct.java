package zeenea.connector.dataproduct;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.Item;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.dataset.Dataset;
import zeenea.connector.process.DataProcess;

public class DataProduct extends Item {

  private final List<InputPort> inputPorts;
  private final List<OutputPort> outputPorts;
  private final List<Item> internalComponents;

  private DataProduct(Builder builder) {
    super(builder);
    inputPorts = List.copyOf(builder.inputPorts);
    outputPorts = List.copyOf(builder.outputPorts);
    internalComponents = List.copyOf(builder.internalComponents);
  }

  public List<InputPort> getInputPorts() {
    return inputPorts;
  }

  public List<OutputPort> getOutputPorts() {
    return outputPorts;
  }

  public List<Item> getInternalComponents() {
    return internalComponents;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DataProduct that = (DataProduct) o;
    return Objects.equals(inputPorts, that.inputPorts)
        && Objects.equals(outputPorts, that.outputPorts)
        && Objects.equals(internalComponents, that.internalComponents);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inputPorts, outputPorts, internalComponents);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DataProduct.class.getSimpleName() + "[", "]")
        .add("inputPorts=" + inputPorts)
        .add("outputPorts=" + outputPorts)
        .add("internalComponents=" + internalComponents)
        .toString();
  }

  public static Builder builder(@NotNull ItemIdentifier id, @NotNull String name) {
    return new Builder(id, name);
  }

  public static class Builder extends Item.Builder<DataProduct, Builder> {

    private final List<InputPort> inputPorts = new ArrayList<>();
    private final List<OutputPort> outputPorts = new ArrayList<>();
    private final List<Item> internalComponents = new ArrayList<>();

    private Builder(@NotNull ItemIdentifier id, @NotNull String name) {
      super(id, name);
    }

    public Builder inputPort(InputPort inputPort) {
      this.inputPorts.add(inputPort);
      return this;
    }

    public Builder inputPorts(Collection<InputPort> inputPorts) {
      this.inputPorts.addAll(inputPorts);
      return this;
    }

    public Builder outputPort(OutputPort outputPort) {
      this.outputPorts.add(outputPort);
      return this;
    }

    public Builder outputPorts(Collection<OutputPort> outputPorts) {
      this.outputPorts.addAll(outputPorts);
      return this;
    }

    public Builder internalComponent(Dataset dataset) {
      this.internalComponents.add(dataset);
      return this;
    }

    public Builder internalComponent(DataProcess dataProcess) {
      this.internalComponents.add(dataProcess);
      return this;
    }

    public Builder internalComponents(Collection<Item> items) {
      for (Item item : items) {
        if (!(item instanceof Dataset || item instanceof DataProcess)) {
          throw new IllegalArgumentException(
              String.format(
                  "Internal component must be a %s or a %s and '%s' is a %s",
                  Dataset.class, Process.class, item.getName(), item.getClass()));
        }
      }
      this.internalComponents.addAll(items);
      return this;
    }

    @Override
    protected DataProduct performBuild() {
      return new DataProduct(this);
    }
  }
}

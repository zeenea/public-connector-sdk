package zeenea.connector.process;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.Item;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.common.ItemReference;

public final class DataProcess extends Item {

  private final List<ItemReference> sourceItemReference;
  private final List<ItemReference> targetItemReference;
  private final List<Operation> operations;

  private DataProcess(Builder builder) {
    super(builder);
    this.sourceItemReference = builder.sourceItemReference;
    this.targetItemReference = builder.targetItemReference;
    this.operations = builder.operations;
  }

  public List<ItemReference> getSourceItemReference() {
    return sourceItemReference;
  }

  public List<ItemReference> getTargetItemReference() {
    return targetItemReference;
  }

  public List<Operation> getOperations() {
    return operations;
  }

  public static Builder builder(@NotNull ItemIdentifier id, @NotNull String name) {
    return new Builder(id, name);
  }

  public static class Builder extends Item.Builder<DataProcess, Builder> {

    private List<ItemReference> sourceItemReference;
    private List<ItemReference> targetItemReference;
    private List<Operation> operations;

    private Builder(@NotNull ItemIdentifier id, @NotNull String name) {
      super(id, name);
    }

    public Builder sourceItemReference(List<ItemReference> sourceItemReference) {
      this.sourceItemReference = sourceItemReference;
      return this;
    }

    public Builder targetItemReference(List<ItemReference> targetItemReference) {
      this.targetItemReference = targetItemReference;
      return this;
    }

    public Builder operations(List<Operation> operations) {
      this.operations = operations;
      return this;
    }

    @Override
    protected DataProcess performBuild() {
      return new DataProcess(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DataProcess that = (DataProcess) o;
    return Objects.equals(sourceItemReference, that.sourceItemReference)
        && Objects.equals(targetItemReference, that.targetItemReference)
        && Objects.equals(operations, that.operations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sourceItemReference, targetItemReference, operations);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DataProcess.class.getSimpleName() + "[", "]")
        .add("operations=" + operations)
        .add("sourceItemReference=" + sourceItemReference)
        .add("targetItemReference=" + targetItemReference)
        .toString();
  }
}

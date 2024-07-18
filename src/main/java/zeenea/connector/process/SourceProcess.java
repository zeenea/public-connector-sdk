package zeenea.connector.process;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import zeenea.connector.SourceItem;
import zeenea.connector.common.ItemReference;

public final class SourceProcess extends SourceItem {

  private final List<ItemReference> sourceItemReference;
  private final List<ItemReference> targetItemReference;
  private final List<SourceOperation> operations;

  private SourceProcess(Builder builder) {
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

  public List<SourceOperation> getOperations() {
    return operations;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder extends SourceItem.Builder<SourceProcess, Builder> {

    private List<ItemReference> sourceItemReference;
    private List<ItemReference> targetItemReference;
    private List<SourceOperation> operations;

    private Builder() {}

    public Builder sourceItemReference(List<ItemReference> sourceItemReference) {
      this.sourceItemReference = sourceItemReference;
      return this;
    }

    public Builder targetItemReference(List<ItemReference> targetItemReference) {
      this.targetItemReference = targetItemReference;
      return this;
    }

    public Builder operations(List<SourceOperation> operations) {
      this.operations = operations;
      return this;
    }

    @Override
    protected SourceProcess performBuild() {
      return new SourceProcess(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SourceProcess that = (SourceProcess) o;
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
    return new StringJoiner(", ", SourceProcess.class.getSimpleName() + "[", "]")
        .add("operations=" + operations)
        .add("sourceItemReference=" + sourceItemReference)
        .add("targetItemReference=" + targetItemReference)
        .toString();
  }
}

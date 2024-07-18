package zeenea.connector.process;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import zeenea.connector.common.ItemReference;

public class SourceOperation {

  private final List<ItemReference> inputFields;
  private final List<ItemReference> outputFields;

  public SourceOperation(Builder builder) {
    this.inputFields = builder.inputFields;
    this.outputFields = builder.outputFields;
  }

  public List<ItemReference> getInputFields() {
    return inputFields;
  }

  public List<ItemReference> getOutputFields() {
    return outputFields;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private List<ItemReference> inputFields;
    private List<ItemReference> outputFields;

    private Builder() {}

    public Builder inputFields(List<ItemReference> inputFields) {
      this.inputFields = inputFields;
      return this;
    }

    public Builder outputFields(List<ItemReference> outputFields) {
      this.outputFields = outputFields;
      return this;
    }

    protected SourceOperation build() {
      if (this.inputFields == null || this.inputFields.isEmpty()) {
        throw new IllegalArgumentException("inputFields must not be null or empty");
      }
      if (this.outputFields == null || this.outputFields.isEmpty()) {
        throw new IllegalArgumentException("targetDataset must not be null or empty");
      }
      return new SourceOperation(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SourceOperation that = (SourceOperation) o;
    return Objects.equals(inputFields, that.inputFields)
        && Objects.equals(outputFields, that.outputFields);
  }

  @Override
  public int hashCode() {
    return Objects.hash(inputFields, outputFields);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SourceOperation.class.getSimpleName() + "[", "]")
        .add("inputFields=" + inputFields)
        .add("outputFields=" + outputFields)
        .toString();
  }
}

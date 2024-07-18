package zeenea.connector.dataset;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class SourceForeignKey {

  private final String targetDataset;
  private final List<String> sourceFields;
  private final List<String> targetFields;
  private final String name;

  public SourceForeignKey(Builder builder) {
    this.targetDataset = builder.targetDataset;
    this.sourceFields = builder.sourceFields;
    this.targetFields = builder.targetFields;
    this.name = builder.name;
  }

  public String getName() {
    return name;
  }

  public List<String> getSourceFields() {
    return sourceFields;
  }

  public String getTargetDataset() {
    return targetDataset;
  }

  public List<String> getTargetFields() {
    return targetFields;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String targetDataset;
    private List<String> sourceFields;
    private List<String> targetFields;
    private String name;

    private Builder() {}

    public Builder targetDataset(String targetDataset) {
      this.targetDataset = targetDataset;
      return this;
    }

    public Builder sourceFields(List<String> sourceFields) {
      this.sourceFields = sourceFields;
      return this;
    }

    public Builder targetFields(List<String> targetFields) {
      this.targetFields = targetFields;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    protected SourceForeignKey build() {
      if (this.targetDataset == null || this.targetDataset.isEmpty()) {
        throw new IllegalArgumentException("targetDataset must not be null or empty");
      }
      if (this.sourceFields == null || this.sourceFields.isEmpty()) {
        throw new IllegalArgumentException("sourceFields must not be null or empty");
      }
      if (this.targetFields == null || this.targetFields.isEmpty()) {
        throw new IllegalArgumentException("targetFields must not be null or empty");
      }
      return new SourceForeignKey(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SourceForeignKey that = (SourceForeignKey) o;
    return Objects.equals(targetDataset, that.targetDataset)
        && Objects.equals(sourceFields, that.sourceFields)
        && Objects.equals(targetFields, that.targetFields)
        && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(targetDataset, sourceFields, targetFields, name);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SourceForeignKey.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .add("targetDataset='" + targetDataset + "'")
        .add("sourceFields=" + sourceFields)
        .add("targetFields=" + targetFields)
        .toString();
  }
}

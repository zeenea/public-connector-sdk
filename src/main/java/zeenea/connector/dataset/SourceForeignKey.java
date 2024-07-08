package zeenea.connector.dataset;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class SourceForeignKey {

  private final String targetDataset;
  private final List<String> sourceFields;
  private final List<String> targetFields;
  private final String name;

  public SourceForeignKey(
      String targetDataset, List<String> sourceFields, List<String> targetFields, String name) {
    this.targetDataset = targetDataset;
    this.sourceFields = sourceFields;
    this.targetFields = targetFields;
    this.name = name;
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

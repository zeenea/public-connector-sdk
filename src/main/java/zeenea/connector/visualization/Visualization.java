package zeenea.connector.visualization;

import static zeenea.connector.exception.ExceptionUtils.throwIfNull;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.Item;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.common.ItemReference;

public final class Visualization extends Item {

  private final List<VisualizationField> fields;
  private final List<ItemReference> linkedDataset;

  private Visualization(Builder builder) {
    super(builder);
    this.fields = builder.fields;
    this.linkedDataset = builder.linkedDataset;
  }

  public List<VisualizationField> getFields() {
    return fields;
  }

  public List<ItemReference> getLinkedDataset() {
    return linkedDataset;
  }

  public static Builder builder(@NotNull ItemIdentifier id, @NotNull String name) {
    return new Builder(id, name);
  }

  public static class Builder extends Item.Builder<Visualization, Builder> {

    private List<VisualizationField> fields;
    private List<ItemReference> linkedDataset;

    private Builder(@NotNull ItemIdentifier id, @NotNull String name) {
      super(id, name);
    }

    public Visualization.Builder fields(List<VisualizationField> fields) {
      this.fields = fields;
      return this;
    }

    public Visualization.Builder linkedDataset(List<ItemReference> linkedDataset) {
      this.linkedDataset = linkedDataset;
      return this;
    }

    @Override
    protected Visualization performBuild() {
      throwIfNull("fields", this.fields);
      throwIfNull("linkedDataset", this.linkedDataset);
      return new Visualization(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Visualization that = (Visualization) o;
    return Objects.equals(fields, that.fields) && Objects.equals(linkedDataset, that.linkedDataset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fields, linkedDataset);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Visualization.class.getSimpleName() + "[", "]")
        .add("fields=" + fields)
        .add("linkedDataset=" + linkedDataset)
        .toString();
  }
}

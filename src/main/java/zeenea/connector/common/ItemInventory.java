package zeenea.connector.common;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class ItemInventory {
  private final ItemIdentifier itemIdentifier;
  private final List<String> labelPath;

  public ItemInventory(Builder builder) {
    this.itemIdentifier = builder.itemIdentifier;
    this.labelPath = builder.label;
  }

  public ItemIdentifier getItemIdentifier() {
    return itemIdentifier;
  }

  public List<String> getLabel() {
    return labelPath;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private ItemIdentifier itemIdentifier;
    private List<String> label;

    private Builder() {}

    public Builder itemIdentifier(ItemIdentifier identificationProperties) {
      this.itemIdentifier = itemIdentifier;
      return this;
    }

    public Builder label(List<String> label) {
      this.label = label;
      return this;
    }

    public ItemInventory build() {
      if (this.itemIdentifier == null
          || this.itemIdentifier.getIdentificationProperties().isEmpty()) {
        throw new IllegalArgumentException("identificationProperties must not be null or empty");
      }
      return new ItemInventory(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemInventory that = (ItemInventory) o;
    return Objects.equals(itemIdentifier, that.itemIdentifier)
        && Objects.equals(labelPath, that.labelPath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemIdentifier, labelPath);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ItemInventory.class.getSimpleName() + "[", "]")
        .add("itemIdentifier=" + itemIdentifier)
        .add("labelPath=" + labelPath)
        .toString();
  }
}

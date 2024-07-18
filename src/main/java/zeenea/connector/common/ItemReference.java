package zeenea.connector.common;

import java.util.Objects;
import java.util.StringJoiner;

public class ItemReference {
  private final ItemIdentifier itemIdentifier;
  private final String connectionAlias;

  public ItemReference(Builder builder) {
    this.itemIdentifier = builder.itemIdentifier;
    this.connectionAlias = builder.connectionAlias;
  }

  public ItemIdentifier getIdentificationProperties() {
    return itemIdentifier;
  }

  public String getConnectionAlias() {
    return connectionAlias;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private ItemIdentifier itemIdentifier;
    private String connectionAlias;

    private Builder() {}

    public Builder itemIdentifier(ItemIdentifier itemIdentifier) {
      this.itemIdentifier = itemIdentifier;
      return this;
    }

    public Builder connectionAlias(String connectionAlias) {
      this.connectionAlias = connectionAlias;
      return this;
    }

    public ItemReference build() {
      if (this.itemIdentifier == null
          || this.itemIdentifier.getIdentificationProperties().isEmpty()) {
        throw new IllegalArgumentException("identificationProperties must not be null or empty");
      }
      return new ItemReference(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemReference that = (ItemReference) o;
    return Objects.equals(itemIdentifier, that.itemIdentifier)
        && Objects.equals(connectionAlias, that.connectionAlias);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemIdentifier, connectionAlias);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ItemReference.class.getSimpleName() + "[", "]")
        .add("connectionAlias='" + connectionAlias + "'")
        .add("itemIdentifier=" + itemIdentifier)
        .toString();
  }
}

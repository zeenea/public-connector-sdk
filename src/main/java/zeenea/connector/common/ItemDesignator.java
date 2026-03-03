package zeenea.connector.common;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;

/**
 * Immutable value object representing an item to extract, combining its identifier and the
 * mandatory data source identifier.
 */
public final class ItemDesignator {

  // Unique identifier for the item
  @NotNull private final ItemIdentifier itemIdentifier;
  // Identifier for the data source context
  @NotNull private final DataSourceIdentifier dataSourceIdentifier;

  private ItemDesignator(ItemIdentifier itemIdentifier, DataSourceIdentifier dataSourceIdentifier) {
    this.itemIdentifier = Objects.requireNonNull(itemIdentifier, "itemIdentifier");
    this.dataSourceIdentifier =
        Objects.requireNonNull(dataSourceIdentifier, "dataSourceIdentifier must not be null");
  }

  /**
   * Static factory method to create an instance.
   *
   * @param itemIdentifier the item identifier
   * @param dataSourceIdentifier the data source identifier
   * @return a new ItemDesignator instance
   */
  public static ItemDesignator of(
      ItemIdentifier itemIdentifier, DataSourceIdentifier dataSourceIdentifier) {
    return new ItemDesignator(itemIdentifier, dataSourceIdentifier);
  }

  /**
   * Gets the item identifier.
   *
   * @return the item identifier
   */
  public @NotNull ItemIdentifier getItemIdentifier() {
    return itemIdentifier;
  }

  /**
   * Gets the data source identifier.
   *
   * @return the datasource identifier
   */
  public @NotNull DataSourceIdentifier getDataSourceIdentifier() {
    return dataSourceIdentifier;
  }

  @Override
  public String toString() {
    return "ItemDesignator{"
        + "itemIdentifier="
        + itemIdentifier
        + ", dataSourceIdentifier="
        + dataSourceIdentifier
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemDesignator that = (ItemDesignator) o;
    return itemIdentifier.equals(that.itemIdentifier)
        && dataSourceIdentifier.equals(that.dataSourceIdentifier);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemIdentifier, dataSourceIdentifier);
  }
}

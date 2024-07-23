package zeenea.connector.action;

import zeenea.connector.Item;

/**
 * An action to perform on the catalog, scoped to a single {@link Item}.
 *
 * <p>Currently available actions are Upsert and Delete.
 *
 * @see Item
 * @see UpsertAction
 * @since 1.0.0
 */
public interface ItemAction {

  /**
   * Convenient factory to upsert an item.
   *
   * @param item the item to upsert
   * @return the new instance of SourceItemAction describing an item to be upserted
   */
  static ItemAction upsert(Item item) {
    return new UpsertAction(item);
  }
}

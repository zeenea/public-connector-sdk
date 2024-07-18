package zeenea.connector.action;

import zeenea.connector.SourceItem;

/**
 * An action to perform on the catalog, scoped to a single {@link SourceItem}.
 *
 * <p>Currently available actions are Upsert and Delete.
 *
 * @see SourceItem
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
  static ItemAction upsert(SourceItem item) {
    return new UpsertAction(item);
  }
}

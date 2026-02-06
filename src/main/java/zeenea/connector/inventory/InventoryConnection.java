package zeenea.connector.inventory;

import java.util.stream.Stream;
import zeenea.connector.Connection;
import zeenea.connector.Item;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.common.ItemInventory;
import zeenea.connector.common.ItemToExtract;

/**
 * Interface representing a connection capable of managing inventory. Extends the Connection
 * interface and provides methods to retrieve inventory and extract items.
 */
public interface InventoryConnection extends Connection {

  /**
   * Retrieves the inventory as a stream of ItemInventory objects.
   *
   * <p>Sequence diagram for inventory integration: <img alt="Inventory connection sequence diagram"
   * src="/doc-files/inventory-connection-sequence-diagram.png">
   *
   * @return a Stream of ItemInventory objects representing the inventory
   */
  Stream<ItemInventory> inventory();

  /**
   * Extracts items based on the provided stream of item identifiers.
   *
   * <p>Sequence diagram for inventory integration: <img alt="Extract items connection sequence
   * diagram" src="/doc-files/extract-items-connection-sequence-diagram.png">
   *
   * @param items a Stream of ItemIdentifier objects representing the items to extract
   * @return a Stream of Item objects representing the extracted items
   * @deprecated since 2.13.0
   */
  @Deprecated(
      since =
          "Deprecated since version 2.13.0, use extractItemsWithDataSource instead. Scheduled for removal in version 3.0.0",
      forRemoval = true)
  default Stream<Item> extractItems(Stream<ItemIdentifier> items) {
    throw new UnsupportedOperationException(
        "extractItems(Stream<ItemIdentifier>) is deprecated, please use extractItemsWithDataSource(Stream<ItemToExtract>) instead.");
  }

  /**
   * Extracts items based on the provided stream of item and datasource identifiers.
   *
   * <p>Sequence diagram for inventory integration: <img alt="Extract items connection sequence
   * diagram" src="/doc-files/extract-items-connection-sequence-diagram.png">
   *
   * @param items a Stream of objects containing an ItemIdentifier and DatasSourceIdentifier
   *     representing the items to extract
   * @return a Stream of Item objects representing the extracted items
   */
  Stream<Item> extractItemsWithDataSource(Stream<ItemToExtract> items);
}

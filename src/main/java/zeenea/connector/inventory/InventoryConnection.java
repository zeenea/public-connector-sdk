package zeenea.connector.inventory;

import java.util.stream.Stream;
import zeenea.connector.Connection;
import zeenea.connector.Item;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.common.ItemInventory;

/**
 * Interface representing a connection capable of managing inventory. Extends the Connection
 * interface and provides methods to retrieve inventory and extract items.
 *
 * <p>Interface specifying that the {@link Connection} is able to perform inventory and
 * extractItems, as specified in diagram: <img alt="Inventory connection sequence diagram"
 * src="/doc-files/inventory-connection-sequence-diagram.png">
 */
public interface InventoryConnection extends Connection {

  /**
   * Retrieves the inventory as a stream of ItemInventory objects.
   *
   * @return a Stream of ItemInventory objects representing the inventory
   */
  Stream<ItemInventory> inventory();

  /**
   * Extracts items based on the provided stream of item identifiers.
   *
   * @param items a Stream of ItemIdentifier objects representing the items to extract
   * @return a Stream of Item objects representing the extracted items
   */
  Stream<Item> extractItems(Stream<ItemIdentifier> items);
}

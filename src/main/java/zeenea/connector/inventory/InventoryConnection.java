package zeenea.connector.inventory;

import java.util.stream.Stream;
import zeenea.connector.Connection;
import zeenea.connector.SourceItem;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.common.ItemInventory;

/**
 * Base interface for Inventory Connectors.
 *
 * @since 2.0.0
 */
public interface InventoryConnection extends Connection {

  Stream<ItemInventory> inventory();

  Stream<SourceItem> extractItems(Stream<ItemIdentifier> items);
}

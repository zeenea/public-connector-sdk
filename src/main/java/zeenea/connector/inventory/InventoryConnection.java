package zeenea.connector.inventory;

import java.util.stream.Stream;
import zeenea.connector.Connection;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.synchronize.SynchronizeResult;

/**
 * Base interface for Inventory Connectors.
 *
 * @since 2.0.0
 */
public interface InventoryConnection extends Connection {

  InventoryResult inventory();

  SynchronizeResult extractItems(Stream<ItemIdentifier> items);
}

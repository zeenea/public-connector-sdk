package zeenea.connector.inventory;

import java.util.stream.Stream;
import zeenea.connector.Connection;
import zeenea.connector.common.IdentificationKey;
import zeenea.connector.synchronize.SynchronizeResult;

/**
 * Base interface for Connectors.
 *
 * @since 1.0.0
 */
public interface InventoryConnection extends Connection {

  InventoryResult inventory();

  SynchronizeResult extractItems(Stream<IdentificationKey> items);
}

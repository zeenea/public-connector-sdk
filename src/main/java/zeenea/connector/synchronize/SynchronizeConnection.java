package zeenea.connector.synchronize;

import java.util.stream.Stream;
import zeenea.connector.Connection;
import zeenea.connector.Item;

/**
 * Base interface for Connectors.
 *
 * @since 1.0.0
 */
public interface SynchronizeConnection extends Connection {

  /**
   * Called by scanner after {@link SynchronizeConnection#getProperties()} to get <em>all</em>
   * available items.
   *
   * @return The result of the synchronization
   */
  Stream<Item> synchronize();
}

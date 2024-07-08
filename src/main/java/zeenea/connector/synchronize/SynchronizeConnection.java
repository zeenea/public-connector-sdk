package zeenea.connector.synchronize;

import zeenea.connector.Connection;

/**
 * Base interface for Connectors.
 *
 * @since 1.0.0
 */
public interface SynchronizeConnection extends Connection {

  /**
   * Called by scanner after {@link SynchronizeConnection#getSourceProperties()} to get <em>all</em>
   * available items.
   *
   * @return The result of the synchronization
   */
  SynchronizeResult synchronize();
}

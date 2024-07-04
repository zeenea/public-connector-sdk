package zeenea.connector.synchronize;

import zeenea.connector.Connection;

/**
 * Base interface for Connectors.
 *
 * @see SynchronizeConnectionFactory
 * @since 1.0.0
 */
public interface SynchronizeConnection extends Connection {

  /**
   * Called by scanner after {@link SynchronizeConnection#getTechnicalMetadata()} to get
   * <em>all</em> available items.
   *
   * @return The result of the synchronization
   */
  SynchronizeResult synchronize();
}

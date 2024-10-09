package zeenea.connector.synchronize;

import java.util.stream.Stream;
import zeenea.connector.Connection;
import zeenea.connector.Item;

/**
 * Interface representing a connection that can be synchronized in the Zeenea Data Catalog. Extends
 * the Connection interface to include synchronization capabilities.
 *
 * @see Connection
 * @see Item
 */
public interface SynchronizeConnection extends Connection {

  /**
   * Synchronizes the connection and returns a stream of items.
   *
   * <p>Called by scanner after {@link Connection#getProperties()} to get <em>all</em> available
   * items.
   *
   * <p>Interface specifying that the {@link Connection} is able to perform synchronizations, as
   * specified in diagram: <img alt="Connector sequence diagram"
   * src="/doc-files/synchronize-connection-sequence-diagram.png">
   *
   * @return a stream of items resulting from the synchronization
   */
  Stream<Item> synchronize();
}

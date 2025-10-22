package zeenea.connector;

import java.util.Optional;
import java.util.Set;
import zeenea.connector.datasource.DataSource;
import zeenea.connector.property.PropertyDefinition;

/**
 * Base interface for Connections.
 *
 * @see Connector
 * @see zeenea.connector.synchronize.SynchronizeConnection
 * @see zeenea.connector.inventory.InventoryConnection
 */
public interface Connection extends AutoCloseable {

  /**
   * Gets an optional DataSource associated with this connection.
   *
   * @return an Optional containing the DataSource if present, otherwise an empty Optional
   */
  Optional<DataSource> getDataSource();

  /**
   * Called before synchronization to get list of all properties that describe items this connector
   * is able to synchronize.
   *
   * <p>If some technical metadata is removed, no metadata will be removed from already imported
   * items.
   *
   * @return a set of property definitions
   */
  Set<PropertyDefinition> getProperties();
}

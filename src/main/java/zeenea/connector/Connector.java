package zeenea.connector;

import org.pf4j.ExtensionPoint;
import zeenea.connector.exception.InvalidConfigurationException;

/**
 * A utility class to create {@link zeenea.connector.Connection} instances. It extends {@link
 * ExtensionPoint} for discoverability from the Scanner.
 *
 * @see Connection
 * @see ConnectionConfiguration
 * @see ExtensionPoint
 */
public interface Connector extends ExtensionPoint {

  /**
   * Gets the unique identifier of the connector.
   *
   * @return the unique identifier of the connector
   */
  String getConnectorId();

  /**
   * Creates a new connection using the provided configuration.
   *
   * @param configuration the configuration for the connection
   * @return a new Connection instance
   * @throws InvalidConfigurationException if the configuration is invalid
   */
  Connection newConnection(ConnectionConfiguration configuration)
      throws InvalidConfigurationException;
}

package zeenea.connector.synchronize;

import org.pf4j.ExtensionPoint;
import zeenea.connector.ConnectionConfiguration;
import zeenea.connector.ConnectionFactory;
import zeenea.connector.exception.InvalidConfigurationException;

/**
 * A utility class to create {@link SynchronizeConnection} instances following the Factory design
 * pattern, doubling as an {@link ExtensionPoint} for discoverability from the Scanner.
 *
 * @see SynchronizeConnection
 * @see ConnectionConfiguration
 * @see ExtensionPoint
 * @since 1.0.0
 */
public interface SynchronizeConnectionFactory extends ConnectionFactory {

  SynchronizeConnection newConnector(ConnectionConfiguration configuration)
      throws InvalidConfigurationException;
}

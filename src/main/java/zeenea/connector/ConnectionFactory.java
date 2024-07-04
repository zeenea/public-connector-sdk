package zeenea.connector;

import org.pf4j.ExtensionPoint;
import zeenea.connector.exception.InvalidConfigurationException;

/**
 * A utility class to create {@link Connection} instances following the Factory design pattern,
 * doubling as an {@link ExtensionPoint} for discoverability from the Scanner.
 *
 * @see Connection
 * @see ConnectionConfiguration
 * @see ExtensionPoint
 * @since 1.0.0
 */
public interface ConnectionFactory extends ExtensionPoint {

  /**
   * Called from Scanner in order to list available connector factories. As there is no guarantee
   * that this method will be called only once, it is considered to always return the same value.
   *
   * @return The connector id
   */
  String getConnectorId();

  /**
   * Called from Scanner to create a new instance of Connector.
   *
   * <p>Configuration values are guaranteed to be immutable during ItemConnector lifetime.
   *
   * <p>Cases where configuration is not valid could be:
   *
   * <ul>
   *   <li>some configuration key is missing
   *   <li>some configuration value is invalid
   *   <li>a connection to some backend fails given provided configuration
   * </ul>
   *
   * @param configuration The connector configuration
   * @return The new connector instance
   * @throws InvalidConfigurationException if configuration is somehow wrong
   */
  Connection newConnector(ConnectionConfiguration configuration)
      throws InvalidConfigurationException;
}

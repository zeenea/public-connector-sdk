package zeenea.sdk;

import org.pf4j.ExtensionPoint;

import java.util.Map;

public interface ConnectorFactory extends ExtensionPoint {

    String getConnectorId();

    /**
     * Called from scanner to create a new instance of Connector.
     *
     * Configuration values are
     * guaranteed to be immutable during ItemConnector lifetime.
     * <p>
     * Cases where configuration is not valid could be:
     * <ul>
     * <li>some configuration key is missing</li>
     * <li>some configuration value is invalid</li>
     * <li>a connection to some backend fails given provided configuration</li>
     * </ul>
     *
     * @param configuration The connector configuration
     * @throws InvalidConfigurationException if configuration is somehow wrong
     * @return a new connector instance
     */
    Connector newConnector(ConnectorConfiguration configuration) throws InvalidConfigurationException;

}

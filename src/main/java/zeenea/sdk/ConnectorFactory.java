package zeenea.sdk;

import org.pf4j.ExtensionPoint;

public interface ConnectorFactory extends ExtensionPoint {

    String getConnectorId();

    Connector newConnector();

}

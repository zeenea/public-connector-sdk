package zeenea.sdk;

import org.pf4j.ExtensionPoint;

public interface ConnectorFactory<T extends SourceItem> extends ExtensionPoint {

    String getConnectorId();

    Connector<T> newConnector();

}

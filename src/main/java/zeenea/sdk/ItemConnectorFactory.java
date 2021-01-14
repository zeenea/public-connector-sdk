package zeenea.sdk;

import org.pf4j.ExtensionPoint;

public interface ItemConnectorFactory<T extends SourceItem> extends ExtensionPoint {

    ItemConnector<T> newConnector();

}

package zeenea.sdk;

import zeenea.sdk.annotations.Beta;
import zeenea.sdk.metadata.Metadata;
import zeenea.sdk.synchronization.Synchronizable;

import java.util.Set;

/**
 * Base interface for Connectors.
 *
 * @see ConnectorFactory
 * @see Synchronizable
 * @since 1.0.0
 */
@Beta
public interface Connector extends Synchronizable, AutoCloseable {

    /**
     * Called before synchronization to get list of all properties that describe items this connector is able to
     * synchronize.
     * <p>
     * If some technical metadata is removed, no metadata will be removed from already imported items.
     *
     * @return a set of Metadata
     */
    Set<Metadata> getTechnicalMetadata();

    /**
     * The main type of {@link SourceItem}s returned by this connector.
     *
     * @see SourceItem
     * @see ConnectorType
     * @since 1.0.0
     */
    ConnectorType getType();
}

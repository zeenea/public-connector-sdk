package zeenea.sdk;

import java.util.Set;
import zeenea.sdk.metadata.Metadata;
import zeenea.sdk.synchronization.Synchronizable;

/**
 * Base interface for Connectors.
 *
 * @see ConnectorFactory
 * @see Synchronizable
 * @since 1.0.0
 * @deprecated since 2.0.0, see 'zeenea.connector' package
 */
@Deprecated
public interface Connector extends Synchronizable, AutoCloseable {

  /**
   * Called before synchronization to get list of all properties that describe items this connector
   * is able to synchronize.
   *
   * <p>If some technical metadata is removed, no metadata will be removed from already imported
   * items.
   *
   * @return a set of Metadata
   */
  Set<Metadata> getTechnicalMetadata();
}

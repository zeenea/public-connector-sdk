package zeenea.sdk.synchronization;

import zeenea.sdk.Connector;
import zeenea.sdk.annotations.Beta;

/**
 * An interface specifying the {@link Connector} is able to perform synchronizations, as specified in diagram:
 * <img alt="Connector sequence diagram" src="/doc-files/connector-sequence-diagram.png">
 *
 * @see Connector
 * @see SynchronizationResult
 * @since 1.0.0
 */
@Beta
public interface Synchronizable {

    /**
     * Called by scanner after {@link Connector#getTechnicalMetadata()} to get <em>all</em> available items.
     *
     * @return A stream of items guaranteed to be consumed by scanner. This stream must terminate.
     */
    SynchronizationResult synchronize();

}

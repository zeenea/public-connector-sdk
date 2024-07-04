package zeenea.sdk.synchronization;

import zeenea.sdk.Connector;

/**
 * An interface specifying the {@link Connector} is able to perform synchronizations, as specified
 * in diagram: <img alt="Connector sequence diagram"
 * src="/doc-files/connector-sequence-diagram.png">
 *
 * @see Connector
 * @see SynchronizationResult
 * @since 1.0.0
 * @deprecated since 2.0.0, see 'zeenea.connector' package
 */
@Deprecated
public interface Synchronizable {

  /**
   * Called by scanner after {@link Connector#getTechnicalMetadata()} to get <em>all</em> available
   * items.
   *
   * @return The result of the synchronization
   */
  SynchronizationResult synchronize();
}

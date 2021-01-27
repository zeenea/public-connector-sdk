package zeenea.sdk;

import zeenea.sdk.annotations.Beta;
import zeenea.sdk.metadata.Metadata;

import java.util.Map;
import java.util.Set;

/**
 * documentation
 * <img alt="Connector sequence diagram" src="/doc-files/connector-sequence-diagram.png">
 */
@Beta
public interface Connector extends AutoCloseable {

    /**
     * Called before synchronization to get list of all properties that describes items this connector is able to
     * synchronize.
     * <p>
     * If technical metadata are different than previous execution (in case of a new connector release) then
     * synchronize(null) will be called to get all items available. Else an incremental synchronize is called. If some
     * technical metadata is removed, no metadata will be removed from already imported items.
     *
     * @return a set of Metadata
     */
    Set<Metadata> getTechnicalMetadata();

    /**
     * Called by scanner after getTechnicalMetadata to get <em>all</em> available items.
     *
     * @param lastSuccessfulVersion If already called at least once with some lastSuccessfulVersion, then it is
     *                              guaranteed that scanner send the same lastSuccessfulVersion afterwards. It enables
     *                              the incremental synchronization process.
     *                              If synchronize was never called before or technical metadata returned by
     *                              {@link #getTechnicalMetadata} have changed since last call, value is null.
     * @return a stream of items guaranteed to be consumed by scanner and some optional lastSuccessfulVersion if
     * incremental synchronization is supported by this connector. This stream must terminate.
     */
    SynchronizationResult synchronize(Long lastSuccessfulVersion);


}

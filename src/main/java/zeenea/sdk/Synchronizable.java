package zeenea.sdk;

import zeenea.sdk.annotations.Beta;

@Beta
public interface Synchronizable {

    /**
     * Called by scanner after getTechnicalMetadata to get <em>all</em> available items.
     *
     * @param lastSuccessfulVersion If already called at least once with some lastSuccessfulVersion, then it is
     *                              guaranteed that scanner send the same lastSuccessfulVersion afterwards. It enables
     *                              the incremental synchronization process.
     *                              If synchronize was never called before or technical metadata returned by
     *                              {@link Connector#getTechnicalMetadata} have changed since last call, value is null.
     * @return a stream of items guaranteed to be consumed by scanner and some optional lastSuccessfulVersion if
     * incremental synchronization is supported by this connector. This stream must terminate.
     */
    SynchronizationResult synchronize(Long lastSuccessfulVersion);


}

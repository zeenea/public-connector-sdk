package zeenea.sdk.synchronization;

import zeenea.sdk.annotations.Beta;

@Beta
public interface Synchronizable {

    /**
     * Called by scanner after getTechnicalMetadata to get <em>all</em> available items.
     *
     * @return a stream of items guaranteed to be consumed by scanner. This stream must terminate.
     */
    SynchronizationResult synchronize();


}

package zeenea.sdk.synchronization;

import zeenea.sdk.annotations.Beta;

import java.util.stream.Stream;

/**
 * The result of a synchronization operation, as performed by a {@link Synchronizable} {@link zeenea.sdk.Connector}.
 *
 * @see Synchronizable
 * @see SourceItemAction
 * @since 1.0.0
 */
@Beta
public class SynchronizationResult {

    private final Stream<SourceItemAction> items;

    /**
     * Create a new SynchronizationResult with a {@code Stream} of item actions to perform on the catalog.
     * The Stream must terminate.
     * Method {@link Stream#onClose(Runnable)} can be used to hook onto actual consumption ending.
     *
     * @param items The stream of item actions to perform
     */
    public SynchronizationResult(Stream<SourceItemAction> items) {
        this.items = items;
    }

    /**
     * Get the stream of item actions to perform on the catalog.
     * Method {@link Stream#onClose(Runnable)} can be used to hook onto actual consumption ending.
     *
     * @return The stream of item actions to perform on the catalog
     */
    public Stream<SourceItemAction> getItems() {
        return items;
    }
}

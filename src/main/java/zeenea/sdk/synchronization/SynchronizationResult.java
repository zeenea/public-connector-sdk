package zeenea.sdk.synchronization;

import zeenea.sdk.annotations.Beta;

import java.util.stream.Stream;

@Beta
public class SynchronizationResult {

    private final Stream<SourceItemAction> items;

    public SynchronizationResult(Stream<SourceItemAction> items) {
        this.items = items;
    }

    public Stream<SourceItemAction> getItems() {
        return items;
    }
}

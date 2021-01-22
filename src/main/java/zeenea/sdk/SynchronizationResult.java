package zeenea.sdk;

import zeenea.sdk.annotations.Beta;

import java.util.Optional;
import java.util.stream.Stream;

@Beta
public class SynchronizationResult {

    private final Long lastSuccessfulVersion;
    private final Stream<? extends SourceItem> items;

    public SynchronizationResult(Stream<? extends SourceItem> items) {
        this.items = items;
        this.lastSuccessfulVersion = null;
    }

    public SynchronizationResult(Stream<? extends SourceItem> items, long lastSuccessfulVersion) {
        this.items = items;
        this.lastSuccessfulVersion = lastSuccessfulVersion;
    }

    public Stream<? extends SourceItem> getItems() {
        return items;
    }

    public Optional<Long> getLastSuccessfulVersion() {
        return Optional.ofNullable(lastSuccessfulVersion);
    }
}

package zeenea.sdk;

import zeenea.sdk.annotations.Beta;

import java.util.Optional;
import java.util.stream.Stream;

@Beta
public class SynchronizationResult<T extends SourceItem> {

    private final Long lastSuccessfulVersion;
    private final Stream<T> items;

    public SynchronizationResult(Stream<T> items) {
        this.items = items;
        this.lastSuccessfulVersion = null;
    }

    public SynchronizationResult(Stream<T> items, long lastSuccessfulVersion) {
        this.items = items;
        this.lastSuccessfulVersion = lastSuccessfulVersion;
    }

    public Stream<T> getItems() {
        return items;
    }

    public Optional<Long> getLastSuccessfulVersion() {
        return Optional.ofNullable(lastSuccessfulVersion);
    }
}

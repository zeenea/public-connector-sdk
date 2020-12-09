package zeenea.sdk;

import zeenea.sdk.customitem.CustomItem;

import java.util.Optional;
import java.util.stream.Stream;

public class SynchronizationResult<T> {
    Optional<Long> lastSuccessfulVersion;
    Stream<T> items;
}

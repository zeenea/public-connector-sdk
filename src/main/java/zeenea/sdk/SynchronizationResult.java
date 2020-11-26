package zeenea.sdk;

import java.util.Optional;
import java.util.stream.Stream;

public class SynchronizationResult {
    Optional<Long> lastSuccessfulVersion;
    Stream<CustomItem> customItems;
}

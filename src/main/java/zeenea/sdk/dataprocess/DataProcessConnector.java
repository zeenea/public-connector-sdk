package zeenea.sdk.dataprocess;

import zeenea.sdk.ConfigurationValidationResult;
import zeenea.sdk.SynchronizationResult;
import zeenea.sdk.property.Metadata;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface DataProcessConnector extends AutoCloseable {

    ConfigurationValidationResult validateConfig(Map<String, String> config);

    Set<Metadata> getTechnicalMetadata();

    // scanner appelle cette méthode
    // connector send all custom items, with a long for tagging last call to synchronize
    // scanner must consume all custom item
    SynchronizationResult<DataProcess> synchronize(Optional<Long> lastSuccessfulVersion);

}


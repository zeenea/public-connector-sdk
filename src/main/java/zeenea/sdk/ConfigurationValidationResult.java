package zeenea.sdk;

import java.util.Collection;
import java.util.Collections;

enum ConfigurationValidationResultStatus {
    OK,
    KO
}

public final class ConfigurationValidationResult {

    private final ConfigurationValidationResultStatus status;
    private final Collection<String> errors;

    private ConfigurationValidationResult(ConfigurationValidationResultStatus status, Collection<String> errors) {
        this.status = status;
        this.errors = errors;
    }

    public ConfigurationValidationResultStatus getStatus() {
        return status;
    }

    public Collection<String> getErrors() {
        return Collections.unmodifiableCollection(errors);
    }

    public static ConfigurationValidationResult ok() {
        return new ConfigurationValidationResult(ConfigurationValidationResultStatus.OK, Collections.emptyList());
    }

    public static ConfigurationValidationResult ko(Collection<String> errors) {
        return new ConfigurationValidationResult(ConfigurationValidationResultStatus.KO, errors);
    }
}

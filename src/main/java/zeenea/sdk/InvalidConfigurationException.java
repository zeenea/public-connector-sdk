package zeenea.sdk;

import zeenea.sdk.annotations.Beta;

import java.util.Collection;
import java.util.Collections;

@Beta
public final class InvalidConfigurationException extends Exception {

    private final Collection<String> errors;

    private InvalidConfigurationException(Collection<String> errors) {
        super(String.join(" - ", errors));
        this.errors = errors;
    }

    public Collection<String> getErrors() {
        return Collections.unmodifiableCollection(errors);
    }
}

package zeenea.sdk;

import zeenea.sdk.annotations.Beta;

/**
 * A checked {@code Exception} expressing invalid configuration.
 * <p>
 * Such exception may be thrown when:
 * <ul>
 *     <li>A configuration value cannot be parsed (like Long, Boolean or Path values)</li>
 *     <li>A configuration value is considered invalid on connector creation from {@link ConnectorFactory#newConnector(ConnectorConfiguration)}</li>
 * </ul>
 *
 * @since 1.0.0
 */
@Beta
public final class InvalidConfigurationException extends Exception {
    /**
     * @see Exception#Exception()
     */
    public InvalidConfigurationException() {
    }

    /**
     * @see Exception#Exception(String)
     */
    public InvalidConfigurationException(String message) {
        super(message);
    }

    /**
     * @see Exception#Exception(String, Throwable)
     */
    public InvalidConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @see Exception#Exception(Throwable)
     */
    public InvalidConfigurationException(Throwable cause) {
        super(cause);
    }

    /**
     * @see Exception#Exception(String, Throwable, boolean, boolean)
     */
    public InvalidConfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

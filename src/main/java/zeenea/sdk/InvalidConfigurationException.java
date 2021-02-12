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
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public InvalidConfigurationException(String message) {
        super(message);
    }

    /**
     * @see Exception#Exception(String, Throwable)
     * @param  message the detail message (which is saved for later retrieval
     *         by the {@link #getMessage()} method).
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
    public InvalidConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @see Exception#Exception(Throwable)
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
    public InvalidConfigurationException(Throwable cause) {
        super(cause);
    }

    /**
     * @see Exception#Exception(String, Throwable, boolean, boolean)
     * @param  message the detail message.
     * @param cause the cause.  (A {@code null} value is permitted,
     * and indicates that the cause is nonexistent or unknown.)
     * @param enableSuppression whether or not suppression is enabled
     *                          or disabled
     * @param writableStackTrace whether or not the stack trace should
     *                           be writable
     */
    public InvalidConfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

package zeenea.connector.exception;

import zeenea.connector.ConnectionConfiguration;
import zeenea.connector.Connector;

/**
 * A checked {@code Exception} expressing invalid configuration.
 *
 * <p>Such exception may be thrown when:
 *
 * <ul>
 *   <li>A configuration value cannot be parsed (like Long, Boolean or Path values)
 *   <li>A configuration value is considered invalid on connector creation from {@link
 *       Connector#newConnection(ConnectionConfiguration)}
 * </ul>
 *
 * @since 1.0.0
 */
public final class InvalidConfigurationException extends Exception {
  /**
   * @see Exception#Exception()
   */
  public InvalidConfigurationException() {}

  /**
   * @param message the detail message. The detail message is saved for later retrieval by the
   *     {@link #getMessage()} method.
   * @see Exception#Exception(String)
   */
  public InvalidConfigurationException(String message) {
    super(message);
  }

  /**
   * @param message the detail message (which is saved for later retrieval by the {@link
   *     #getMessage()} method).
   * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
   *     (A {@code null} value is permitted, and indicates that the cause is nonexistent or
   *     unknown.)
   * @see Exception#Exception(String, Throwable)
   */
  public InvalidConfigurationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * @param cause the cause (which is saved for later retrieval by the {@link #getCause()} method).
   *     (A {@code null} value is permitted, and indicates that the cause is nonexistent or
   *     unknown.)
   * @see Exception#Exception(Throwable)
   */
  public InvalidConfigurationException(Throwable cause) {
    super(cause);
  }

  /**
   * @param message the detail message.
   * @param cause the cause. (A {@code null} value is permitted, and indicates that the cause is
   *     nonexistent or unknown.)
   * @param enableSuppression whether or not suppression is enabled or disabled
   * @param writableStackTrace whether or not the stack trace should be writable
   * @see Exception#Exception(String, Throwable, boolean, boolean)
   */
  public InvalidConfigurationException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

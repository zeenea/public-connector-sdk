package zeenea.connector.exception;

/**
 * Exception thrown when there is an invalid configuration. This is a runtime exception that can be
 * used to indicate issues with configuration settings.
 */
public final class InvalidConfigurationException extends RuntimeException {

  /** Constructs a new InvalidConfigurationException with {@code null} as its detail message. */
  public InvalidConfigurationException() {}

  /**
   * Constructs a new InvalidConfigurationException with the specified detail message.
   *
   * @param message the detail message
   */
  public InvalidConfigurationException(String message) {
    super(message);
  }

  /**
   * Constructs a new InvalidConfigurationException with the specified detail message and cause.
   *
   * @param message the detail message
   * @param cause the cause of the exception
   */
  public InvalidConfigurationException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new InvalidConfigurationException with the specified cause.
   *
   * @param cause the cause of the exception
   */
  public InvalidConfigurationException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new InvalidConfigurationException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message the detail message
   * @param cause the cause of the exception
   * @param enableSuppression whether suppression is enabled or disabled
   * @param writableStackTrace whether the stack trace should be writable
   */
  public InvalidConfigurationException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

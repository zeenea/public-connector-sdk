package zeenea.connector.exception;

/**
 * Exception thrown by a connector when a data observability read fails. This is a runtime exception
 * whose message names the cause of the failure (for example a missing role), so that the scanner
 * can log it and delete nothing.
 */
public final class DataObservabilityException extends RuntimeException {

  /** Constructs a new DataObservabilityException with {@code null} as its detail message. */
  public DataObservabilityException() {}

  /**
   * Constructs a new DataObservabilityException with the specified detail message.
   *
   * @param message the detail message, naming the cause of the failure
   */
  public DataObservabilityException(String message) {
    super(message);
  }

  /**
   * Constructs a new DataObservabilityException with the specified detail message and cause.
   *
   * @param message the detail message, naming the cause of the failure
   * @param cause the cause of the exception
   */
  public DataObservabilityException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Constructs a new DataObservabilityException with the specified cause.
   *
   * @param cause the cause of the exception
   */
  public DataObservabilityException(Throwable cause) {
    super(cause);
  }

  /**
   * Constructs a new DataObservabilityException with the specified detail message, cause,
   * suppression enabled or disabled, and writable stack trace enabled or disabled.
   *
   * @param message the detail message
   * @param cause the cause of the exception
   * @param enableSuppression whether suppression is enabled or disabled
   * @param writableStackTrace whether the stack trace should be writable
   */
  public DataObservabilityException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}

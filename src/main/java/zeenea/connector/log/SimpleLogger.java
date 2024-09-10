package zeenea.connector.log;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

/**
 * This class is used to create logs and exceptions with consistent messages.
 *
 * <p>Example of SimpleLogger usage :
 * log.entry("my_connector_retrieve_datasets").with("dataset_count", 10) .info();
 */
public class SimpleLogger {

  private static final Map<Class<?>, SimpleLogger> instances = new ConcurrentHashMap<>();

  private final Logger log;

  /**
   * Constructs a SimpleLogger for the specified class.
   *
   * @param klass the class for which the logger is created
   */
  private SimpleLogger(Class<?> klass) {
    this.log = LoggerFactory.getLogger(klass);
  }

  /**
   * Returns a SimpleLogger instance for the specified class.
   *
   * @param klass the class for which the logger is created
   * @return a SimpleLogger instance
   */
  public static SimpleLogger of(Class<?> klass) {
    return instances.computeIfAbsent(klass, SimpleLogger::new);
  }

  /**
   * Creates a new log entry with the specified code.
   *
   * @param code the code for the log entry
   * @return a new Entry instance
   */
  public Entry entry(String code) {
    return new Entry(code);
  }

  /**
   * Checks if TRACE level logging is enabled.
   *
   * @return true if TRACE level logging is enabled, false otherwise
   */
  public boolean isTraceEnabled() {
    return log.isTraceEnabled();
  }

  /**
   * Checks if DEBUG level logging is enabled.
   *
   * @return true if DEBUG level logging is enabled, false otherwise
   */
  public boolean isDebugEnabled() {
    return log.isDebugEnabled();
  }

  /**
   * Checks if INFO level logging is enabled.
   *
   * @return true if INFO level logging is enabled, false otherwise
   */
  public boolean isInfoEnabled() {
    return log.isInfoEnabled();
  }

  /**
   * Checks if WARN level logging is enabled.
   *
   * @return true if WARN level logging is enabled, false otherwise
   */
  public boolean isWarnEnabled() {
    return log.isWarnEnabled();
  }

  /**
   * Checks if ERROR level logging is enabled.
   *
   * @return true if ERROR level logging is enabled, false otherwise
   */
  public boolean isErrorEnabled() {
    return log.isErrorEnabled();
  }

  /** Enum representing verbosity levels for logging. */
  public enum Verbosity {
    NORMAL,
    QUIET,
    VERY_QUIET
  }

  /** Class representing a log entry. */
  public class Entry {

    private final String code;
    private final List<EntryValue> values = new ArrayList<>();

    private Supplier<?> body;
    private List<EntryValue> namedBodies;

    private TracingContext context;
    private Instant startTime;
    private Instant endTime;
    private long startTimeNanos = -1;
    private long endTimeNanos = -1;

    private Verbosity verbosity = Verbosity.NORMAL;

    /**
     * Constructs a new Entry with the specified code.
     *
     * @param code the code for the log entry
     */
    private Entry(String code) {
      this.code = code;
    }

    /**
     * Adds additional information to the entry using the specified consumer.
     *
     * @param adder the consumer to add additional information
     * @return the updated Entry instance
     */
    public Entry more(Consumer<Entry> adder) {
      adder.accept(this);
      return this;
    }

    /**
     * Adds a value to the entry with the specified name and value.
     *
     * @param name the name of the value
     * @param value the value to add
     * @return the updated Entry instance
     */
    public Entry with(String name, Object value) {
      this.values.add(new EntryValue(name, () -> value));
      return this;
    }

    /**
     * Adds a value to the entry with the specified name and value supplier.
     *
     * @param name the name of the value
     * @param value the value supplier to add
     * @return the updated Entry instance
     */
    public Entry with(String name, Supplier<?> value) {
      this.values.add(new EntryValue(name, value));
      return this;
    }

    /**
     * Sets the body of the entry.
     *
     * @param body the body to set
     * @return the updated Entry instance
     */
    public Entry body(Object body) {
      return body(() -> body);
    }

    /**
     * Sets the body of the entry using a supplier.
     *
     * @param body the body supplier to set
     * @return the updated Entry instance
     */
    public Entry body(Supplier<?> body) {
      this.body = body;
      return this;
    }

    /**
     * Sets a named body for the entry.
     *
     * @param name the name of the body
     * @param body the body to set
     * @return the updated Entry instance
     */
    public Entry body(String name, Object body) {
      return body(name, () -> body);
    }

    /**
     * Sets a named body for the entry using a supplier.
     *
     * @param name the name of the body
     * @param body the body supplier to set
     * @return the updated Entry instance
     */
    public Entry body(String name, Supplier<?> body) {
      if (namedBodies == null) namedBodies = new ArrayList<>();
      namedBodies.add(new EntryValue(name, body));
      return this;
    }

    /**
     * Sets the tracing context for the entry.
     *
     * @param ctx the tracing context to set
     * @return the updated Entry instance
     */
    public Entry context(TracingContext ctx) {
      this.context = ctx;
      return this;
    }

    /**
     * Sets the start time for the entry.
     *
     * @param startTime the start time to set
     * @return the updated Entry instance
     */
    public Entry startTime(Instant startTime) {
      this.startTime = startTime;
      if (this.endTime == null) {
        this.endTime = Instant.now();
      }
      return this;
    }

    /**
     * Sets the end time for the entry.
     *
     * @param endTime the end time to set
     * @return the updated Entry instance
     */
    public Entry endTime(Instant endTime) {
      this.endTime = endTime;
      return this;
    }

    /**
     * Sets the start time in nanoseconds for the entry.
     *
     * @param startTime the start time in nanoseconds to set
     * @return the updated Entry instance
     */
    public Entry startTimeNanos(long startTime) {
      this.startTimeNanos = startTime;
      if (this.endTimeNanos < 0) {
        this.endTimeNanos = System.nanoTime();
      }
      return this;
    }

    /**
     * Sets the end time in nanoseconds for the entry.
     *
     * @param endTime the end time in nanoseconds to set
     * @return the updated Entry instance
     */
    public Entry endTimeNanos(long endTime) {
      this.endTimeNanos = endTime;
      return this;
    }

    /**
     * Sets the verbosity level for the entry.
     *
     * @param verbosity the verbosity level to set
     * @return the updated Entry instance
     */
    public Entry verbosity(Verbosity verbosity) {
      this.verbosity = verbosity;
      return this;
    }

    /**
     * Sets the verbosity level to NORMAL.
     *
     * @return the updated Entry instance
     */
    public Entry verbose() {
      return verbosity(Verbosity.NORMAL);
    }

    /**
     * Sets the verbosity level to QUIET.
     *
     * @return the updated Entry instance
     */
    public Entry quiet() {
      return verbosity(Verbosity.QUIET);
    }

    /**
     * Sets the verbosity level to VERY_QUIET.
     *
     * @return the updated Entry instance
     */
    public Entry veryQuiet() {
      return verbosity(Verbosity.VERY_QUIET);
    }

    /** Logs entry at ERROR level. */
    public void error() {
      log(Level.ERROR);
    }

    /**
     * Logs entry at ERROR level with given cause.
     *
     * @param cause cause exception.
     */
    public void error(Throwable cause) {
      log(Level.ERROR, cause);
    }

    /** Logs entry at WARN level. */
    public void warn() {
      log(Level.WARN);
    }

    /**
     * Logs entry at WARN level with given cause.
     *
     * @param cause cause exception.
     */
    public void warn(Throwable cause) {
      log(Level.WARN, cause);
    }

    /** Logs entry at INFO level. */
    public void info() {
      log(Level.INFO);
    }

    /**
     * Logs entry at INFO level with given cause.
     *
     * @param cause cause exception.
     */
    public void info(Throwable cause) {
      log(Level.INFO, cause);
    }

    /** Logs entry at DEBUG level. */
    public void debug() {
      log(Level.DEBUG);
    }

    /**
     * Logs entry at DEBUG level with given cause.
     *
     * @param cause cause exception.
     */
    public void debug(Throwable cause) {
      log(Level.DEBUG, cause);
    }

    /** Logs entry at TRACE level. */
    public void trace() {
      log(Level.TRACE);
    }

    /**
     * Logs entry at TRACE level with given cause.
     *
     * @param cause cause exception.
     */
    public void trace(Throwable cause) {
      log(Level.TRACE, cause);
    }

    /**
     * Build an exception with a message with the same content as simple log entries. The message is
     * logged at the DEBUG level only in case the exception was not fully proceeded.
     *
     * <p>This is the preferred way to prepare exceptions.
     *
     * @param ex Throwable factory that takes the message as an argument.
     * @param <T> Type of the Throwable.
     * @return The new Throwable.
     */
    public <T extends Throwable> T exception(Function<String, T> ex) {
      StringBuilder msgBuilder = commonMessage();
      addBodyMessage(msgBuilder);
      String msg = msgBuilder.toString();
      log.debug(msg);
      return ex.apply(msg);
    }

    /**
     * Build an exception with a message with the same content as simple log entries. The message is
     * logged at the DEBUG level only in case the exception was not fully proceeded.
     *
     * <p>This is the preferred way to prepare exceptions.
     *
     * @param ex Throwable factory that takes the message and the cause as arguments.
     * @param <T> Type of the Throwable.
     * @param <U> Type of the cause.
     * @return The new Throwable.
     */
    public <T extends Throwable, U extends Throwable> T exception(
        U cause, BiFunction<String, U, T> ex) {
      StringBuilder msgBuilder = commonMessage();
      addBodyMessage(msgBuilder);
      String msg = msgBuilder.toString();
      log.debug(msg, cause);
      return ex.apply(msg, cause);
    }

    /**
     * Logs the entry at the specified level.
     *
     * @param level the log level
     */
    private void log(Level level) {
      if (isEnabled(level)) {
        StringBuilder msgBuilder = commonMessage();
        addBodyMessage(msgBuilder);
        writeLog(level, msgBuilder.toString());
      }
    }

    /**
     * Logs the entry at the specified level with the given cause.
     *
     * @param level the log level
     * @param cause the cause exception
     */
    private void log(Level level, Throwable cause) {
      if (isEnabled(level)) {
        StringBuilder msgBuilder = commonMessage();
        addBodyMessage(msgBuilder);
        if (verbosity == Verbosity.NORMAL
            || verbosity == Verbosity.QUIET && log.isDebugEnabled()
            || log.isTraceEnabled()) {
          writeLog(level, msgBuilder.toString(), cause);
        } else {
          addCauseMessage(msgBuilder, cause);
          writeLog(level, msgBuilder.toString());
        }
      }
    }

    /**
     * Builds the common part of the log message.
     *
     * @return the common part of the log message
     */
    private StringBuilder commonMessage() {
      StringBuilder sb = new StringBuilder();
      sb.append(code);
      if (context == null) {
        context = TracingContext.current();
      }
      if (context != null) {
        sb.append(' ').append(context);
      }
      if (startTime != null) {
        sb.append(" duration='");
        Durations.appendTo(sb, startTime, endTime);
        sb.append('\'');
      } else if (startTimeNanos >= 0) {
        sb.append(" duration='");
        Durations.appendTo(sb, Duration.ofNanos(endTimeNanos - startTimeNanos));
        sb.append('\'');
      }
      for (EntryValue value : values) {
        sb.append(' ');
        sb.append(value.name());
        sb.append("='");
        sb.append(value.value().replace("'", "''"));
        sb.append('\'');
      }
      return sb;
    }

    /**
     * Adds the body message to the provided StringBuilder.
     *
     * @param msgBuilder the StringBuilder to append the body message to
     */
    private void addBodyMessage(StringBuilder msgBuilder) {
      Object bodyVal = body != null ? body.get() : null;
      if (bodyVal != null) {
        msgBuilder.append(System.lineSeparator());
        msgBuilder.append("    ");
        msgBuilder.append("-".repeat(72));
        msgBuilder.append(System.lineSeparator());
        msgBuilder.append(indent(bodyVal.toString(), 4));
      }
      if (namedBodies != null) {
        for (var namedBody : namedBodies) {
          String name = namedBody.name();
          var nameLen = name.length();
          int leftLineLen, rightLineLen;
          if (nameLen < 62) {
            leftLineLen = (70 - nameLen) / 2;
            rightLineLen = 70 - nameLen - leftLineLen;
          } else {
            leftLineLen = rightLineLen = 4;
          }
          msgBuilder.append(System.lineSeparator());
          msgBuilder.append(indent("-".repeat(leftLineLen), 4));
          msgBuilder.append(' ');
          msgBuilder.append(name);
          msgBuilder.append(' ');
          msgBuilder.append("-".repeat(rightLineLen));
          msgBuilder.append(System.lineSeparator());
          msgBuilder.append(indent(namedBody.value(), 4));
        }
      }
      if (bodyVal != null || (namedBodies != null && !namedBodies.isEmpty())) {
        msgBuilder.append(System.lineSeparator());
        msgBuilder.append("    ");
        msgBuilder.append("-".repeat(72));
      }
    }

    /**
     * Adds the cause message to the provided StringBuilder.
     *
     * @param msgBuilder the StringBuilder to append the cause message to
     * @param cause the Throwable cause to add to the message
     */
    private void addCauseMessage(StringBuilder msgBuilder, Throwable cause) {
      Throwable c = cause;
      while (c != null) {
        msgBuilder.append(System.lineSeparator()).append(indent("Cause: " + c.getMessage(), 4));
        c = c.getCause();
      }
    }

    /**
     * Checks if logging is enabled for the specified level.
     *
     * @param level the log level to check
     * @return true if logging is enabled for the specified level, false otherwise
     */
    private boolean isEnabled(Level level) {
      switch (level) {
        case ERROR:
          return log.isErrorEnabled();
        case WARN:
          return log.isWarnEnabled();
        case INFO:
          return log.isInfoEnabled();
        case DEBUG:
          return log.isDebugEnabled();
        case TRACE:
          return log.isTraceEnabled();
        default:
          log.error("Unknown log level: " + level);
          return false;
      }
    }

    /**
     * Writes the log message at the specified level.
     *
     * @param level the log level
     * @param message the log message
     */
    private void writeLog(Level level, String message) {
      switch (level) {
        case ERROR:
          log.error(message);
          break;
        case WARN:
          log.warn(message);
          break;
        case INFO:
          log.info(message);
          break;
        case DEBUG:
          log.debug(message);
          break;
        case TRACE:
          log.trace(message);
          break;
        default:
          log.error("Unknown log level: " + level);
      }
    }

    /**
     * Writes the log message and cause at the specified level.
     *
     * @param level the log level
     * @param message the log message
     * @param cause the cause exception
     */
    private void writeLog(Level level, String message, Throwable cause) {
      switch (level) {
        case ERROR:
          log.error(message, cause);
          break;
        case WARN:
          log.warn(message, cause);
          break;
        case INFO:
          log.info(message, cause);
          break;
        case DEBUG:
          log.debug(message, cause);
          break;
        case TRACE:
          log.trace(message, cause);
          break;
        default:
          log.error("Unknown log level: " + level);
      }
    }
  }

  /** Represents a value in a log entry. */
  private static final class EntryValue {

    private final String name;
    private final Supplier<?> valueSupplier;

    /**
     * Constructs an EntryValue with the specified name and value supplier.
     *
     * @param name the name of the value
     * @param valueSupplier the supplier of the value
     */
    public EntryValue(String name, Supplier<?> valueSupplier) {
      this.name = name;
      this.valueSupplier = Objects.requireNonNull(valueSupplier);
    }

    /**
     * Returns the name of the value.
     *
     * @return the name of the value
     */
    String name() {
      return name;
    }

    /**
     * Returns the value as a string.
     *
     * @return the value as a string
     */
    private String value() {
      try {
        Object value = valueSupplier.get();
        return value != null ? value.toString() : "";
      } catch (RuntimeException e) {
        return "Failed to read value: " + e.getMessage();
      }
    }
  }

  /**
   * Indents the given string by the specified number of spaces.
   *
   * @param value the string to indent
   * @param count the number of spaces to indent
   * @return the indented string
   */
  public static String indent(String value, int count) {
    if (value == null) return null;
    if (count > 0) {
      return Pattern.compile("^", Pattern.MULTILINE).matcher(value).replaceAll(" ".repeat(count));
    }
    return value;
  }
}

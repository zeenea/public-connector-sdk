package zeenea.connector.log;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TracingContext {
  private static final ThreadLocal<TracingContext> current = new ThreadLocal<>();
  private static final AtomicInteger nextValue = new AtomicInteger();

  private final @NotNull String text;

  /**
   * Constructs a new TracingContext with the specified text.
   *
   * @param text the text for the tracing context
   */
  private TracingContext(@NotNull String text) {
    this.text = text;
  }

  /**
   * Creates a new TracingContext for a new connector with the specified connection code.
   *
   * @param connectionCode the connection code
   * @return a new TracingContext instance
   */
  public static @NotNull TracingContext newConnector(String connectionCode) {
    return new TracingContext(connectionCode + "/new_connector#" + nextNumber());
  }

  /**
   * Creates a new TracingContext for inventory with the specified connection code.
   *
   * @param connectionCode the connection code
   * @return a new TracingContext instance
   */
  public static @NotNull TracingContext inventory(@NotNull String connectionCode) {
    return new TracingContext(connectionCode + "/inventory#" + nextNumber());
  }

  /**
   * Creates a new TracingContext for extracting an item with the specified connection code and item
   * reference.
   *
   * @param connectionCode the connection code
   * @param itemRef the item reference
   * @return a new TracingContext instance
   */
  public static @NotNull TracingContext extractItem(
      @NotNull String connectionCode, @NotNull Object itemRef) {
    return new TracingContext(
        connectionCode + "/extract_item#" + nextNumber() + "(itemIdentifier='" + itemRef + "')");
  }

  /**
   * Creates a new TracingContext for synchronization with the specified connection code.
   *
   * @param connectionCode the connection code
   * @return a new TracingContext instance
   */
  public static @NotNull TracingContext synchronize(@NotNull String connectionCode) {
    return new TracingContext(connectionCode + "/synchonize#" + nextNumber());
  }

  /**
   * Creates a new TracingContext for testing purposes.
   *
   * @return a new TracingContext instance
   */
  public static @NotNull TracingContext test() {
    return new TracingContext("test/test#" + nextNumber());
  }

  /**
   * Generates the next unique number for the tracing context.
   *
   * @return the next unique number as a string
   */
  private static @NotNull String nextNumber() {
    return Integer.toUnsignedString(nextValue.getAndIncrement());
  }

  /**
   * Provides the current TracingContext if one is defined.
   *
   * @return the current TracingContext or null if none is defined
   */
  public static @Nullable TracingContext current() {
    return current.get();
  }

  /**
   * Provides the current TracingContext.
   *
   * @return the current TracingContext
   * @throws java.util.NoSuchElementException if no context is defined
   */
  public static @NotNull TracingContext get() {
    TracingContext tracingContext = current.get();
    if (tracingContext == null) {
      throw new NoSuchElementException("No tracing context defined");
    }
    return tracingContext;
  }

  /**
   * Executes the given action within the context of this TracingContext.
   *
   * @param <T> the type of the result
   * @param action the action to execute
   * @return the result of the action
   */
  public <T> T with(Supplier<T> action) {
    TracingContext old = current.get();
    current.set(this);
    try {
      return action.get();
    } finally {
      if (old != null) current.set(old);
      else current.remove();
    }
  }

  /**
   * Returns the string representation of this TracingContext.
   *
   * @return the string representation of this TracingContext
   */
  @Override
  public String toString() {
    return text;
  }
}

package zeenea.connector.synchronize;

import java.util.stream.Stream;
import zeenea.connector.action.ItemAction;

/**
 * The result of a synchronization operation, as performed by a {@link SynchronizeConnection} {@link
 * zeenea.connector.Connection}.
 *
 * @see SynchronizeConnection
 * @see ItemAction
 * @since 1.0.0
 */
public class SynchronizeResult {

  private final Stream<ItemAction> items;

  /**
   * Create a new SynchronizationResult with a {@code Stream} of item actions to perform on the
   * catalog. The Stream must terminate. Method {@link Stream#onClose(Runnable)} can be used to hook
   * onto actual consumption ending.
   *
   * @param items The stream of item actions to perform
   */
  public SynchronizeResult(Stream<ItemAction> items) {
    this.items = items;
  }

  /**
   * Get the stream of item actions to perform on the catalog. Method {@link
   * Stream#onClose(Runnable)} can be used to hook onto actual consumption ending.
   *
   * @return The stream of item actions to perform on the catalog
   */
  public Stream<ItemAction> getItems() {
    return items;
  }
}

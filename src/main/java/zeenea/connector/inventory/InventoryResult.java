package zeenea.connector.inventory;

import java.util.stream.Stream;
import zeenea.connector.action.ItemAction;
import zeenea.connector.common.SourceItemIdentifier;

/**
 * The result of a synchronization operation, as performed by a {@link InventoryConnection} {@link
 * zeenea.connector.Connection}.
 *
 * @see InventoryConnection
 * @see ItemAction
 * @since 1.0.0
 */
public class InventoryResult {

  private final Stream<SourceItemIdentifier> items;

  public InventoryResult(Stream<SourceItemIdentifier> items) {
    this.items = items;
  }

  public Stream<SourceItemIdentifier> getItems() {
    return items;
  }
}

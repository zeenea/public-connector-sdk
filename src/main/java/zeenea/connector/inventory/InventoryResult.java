package zeenea.connector.inventory;

import java.util.stream.Stream;
import zeenea.connector.action.ItemAction;
import zeenea.connector.common.ItemInventory;

/**
 * The result of a synchronization operation, as performed by a {@link InventoryConnection} {@link
 * zeenea.connector.Connection}.
 *
 * @see InventoryConnection
 * @see ItemAction
 * @since 1.0.0
 */
public class InventoryResult {

  private final Stream<ItemInventory> items;

  public InventoryResult(Stream<ItemInventory> items) {
    this.items = items;
  }

  public Stream<ItemInventory> getItems() {
    return items;
  }
}

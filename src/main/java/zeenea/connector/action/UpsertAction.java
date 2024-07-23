package zeenea.connector.action;

import java.util.Objects;
import java.util.StringJoiner;
import zeenea.connector.Item;

/**
 * A {@link ItemAction} used to create/update an item.
 *
 * @see ItemAction
 * @since 1.0.0
 */
public class UpsertAction implements ItemAction {

  private final Item item;

  UpsertAction(Item item) {
    this.item = item;
  }

  /**
   * Get the item to upsert.
   *
   * @return The item to upsert
   */
  public Item getItem() {
    return item;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UpsertAction that = (UpsertAction) o;
    return Objects.equals(item, that.item);
  }

  @Override
  public int hashCode() {
    return Objects.hash(item);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", UpsertAction.class.getSimpleName() + "[", "]")
        .add("item=" + item)
        .toString();
  }
}

package zeenea.connector.action;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * A {@link ItemAction} used to delete an item.
 *
 * @see ItemAction
 * @since 1.0.0
 */
public class DeleteAction implements ItemAction {

  private final String itemId;

  DeleteAction(String itemId) {
    this.itemId = itemId;
  }

  /**
   * Get the id of the item to delete.
   *
   * @return The id of the item to delete
   */
  public String getItemId() {
    return itemId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DeleteAction that = (DeleteAction) o;
    return Objects.equals(itemId, that.itemId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemId);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", DeleteAction.class.getSimpleName() + "[", "]")
        .add("itemId=" + itemId)
        .toString();
  }
}

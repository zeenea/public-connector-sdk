package zeenea.sdk.synchronization;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * A {@link SourceItemAction} used to delete an item.
 *
 * @see SourceItemAction
 * @since 1.0.0
 * @deprecated since 2.0.0, see 'zeenea.connector' package
 */
@Deprecated
public class DeleteAction implements SourceItemAction {

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

package zeenea.sdk.synchronization;

import java.util.Objects;
import java.util.StringJoiner;

public class DeleteAction implements SourceItemAction {

    private final String itemId;

    DeleteAction(String itemId) {
        this.itemId = itemId;
    }

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

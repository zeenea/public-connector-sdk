package zeenea.sdk;

import java.util.Objects;
import java.util.StringJoiner;

public class SourceItemAction {

    private final Action action;
    private final SourceItem item;

    public SourceItemAction(Action action, SourceItem item) {
        this.action = action;
        this.item = item;
    }

    public enum Action {
        UPSERT,
        DELETE
    }

    public Action getAction() {
        return action;
    }

    public SourceItem getItem() {
        return item;
    }

    /**
     * Convenient factory to upsert an item
     *
     * @param item the item to upsert
     * @return the new instance of SourceItemAction describing an item to be upserted
     */
    public static SourceItemAction upsert(SourceItem item) {
        return new SourceItemAction(Action.UPSERT, item);
    }

    /**
     * Convenient factory to delete an item
     *
     * @param item the item to delete
     * @return the new instance of SourceItemAction describing an item to be deleted
     */
    public static SourceItemAction delete(SourceItem item) {
        return new SourceItemAction(Action.DELETE, item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SourceItemAction that = (SourceItemAction) o;
        return action == that.action && Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(action, item);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SourceItemAction.class.getSimpleName() + "[", "]")
                .add("action=" + action)
                .add("item=" + item)
                .toString();
    }
}

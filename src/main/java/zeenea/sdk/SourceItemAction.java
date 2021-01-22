package zeenea.sdk;

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
}

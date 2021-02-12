package zeenea.sdk.synchronization;

import zeenea.sdk.SourceItem;
import zeenea.sdk.annotations.Beta;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * A {@link SourceItemAction} used to create/update an item.
 *
 * @see SourceItemAction
 * @since 1.0.0
 */
@Beta
public class UpsertAction implements SourceItemAction {

    private final SourceItem item;

    UpsertAction(SourceItem item) {
        this.item = item;
    }

    /**
     * Get the item to upsert.
     *
     * @return The item to upsert
     */
    public SourceItem getItem() {
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

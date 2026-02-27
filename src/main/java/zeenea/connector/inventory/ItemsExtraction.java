package zeenea.connector.inventory;

import zeenea.connector.Item;
import zeenea.connector.common.ItemReference;

import java.util.List;

public class ItemsExtraction {

    private final List<Item> items;

    /**
     * Example of an extra-information we may want
     */
    private final List<ItemReference> failedToExtractItems;

    public ItemsExtraction(List<Item> items) {
        this(items, List.of());
    }

    public ItemsExtraction(List<Item> items, List<ItemReference> failedItems) {
        this.items = items;
        this.failedToExtractItems = failedItems;
    }

    public List<Item> getItems() {
        return items;
    }

    public List<ItemReference> getFailedToExtractItems() {
        return failedToExtractItems;
    }
}

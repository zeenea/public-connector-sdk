package zeenea.connector.inventory;

import zeenea.connector.common.ItemReference;

import java.util.List;

public class ItemsExtractionRequest {

    private final List<ItemReference> itemsToExtract; // Ou List<ItemToExtract>

    /**
     * Example of an extra-information we may want
     */
    private final String extractionStrategy = "default";

    public ItemsExtractionRequest(List<ItemReference> itemsToExtract) {
        this.itemsToExtract = itemsToExtract;
    }

    public List<ItemReference> getItemsToExtract() {
        return itemsToExtract;
    }

    public String getExtractionStrategy() {
        return extractionStrategy;
    }
}

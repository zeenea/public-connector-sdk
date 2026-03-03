package zeenea.connector.inventory;

import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.common.ItemDesignator;

/** A request to extract items, from the connector */
public class ExtractionRequest {

  /** ItemsDesignator is used to designate a unique item (Item Identifier + Datasource Identifier) to be extracted */
  @NotNull private final Stream<ItemDesignator> itemsToExtract;

  public ExtractionRequest(@NotNull Stream<ItemDesignator> itemsToExtract) {
    this.itemsToExtract = itemsToExtract;
  }

  public @NotNull Stream<ItemDesignator> getItemsToExtract() {
    return itemsToExtract;
  }
}

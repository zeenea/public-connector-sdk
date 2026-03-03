package zeenea.connector.inventory;

import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.Item;

public class ExtractionResponse {

  /** Item that have been extracted */
  @NotNull private final Stream<Item> extractedItems;

  public ExtractionResponse(@NotNull Stream<Item> extractedItems) {
    this.extractedItems = extractedItems;
  }

  public @NotNull Stream<Item> getExtractedItems() {
    return extractedItems;
  }
}

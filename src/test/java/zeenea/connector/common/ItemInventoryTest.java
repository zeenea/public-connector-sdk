package zeenea.connector.common;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemInventoryTest {

  @Test
  @DisplayName("ItemInventory factory should create item inventory")
  void shouldCreateItemInventory() {
    ItemIdentifier identifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "value")));
    List<String> labels = List.of("label1", "label2");
    ItemInventory inventory = ItemInventory.of(identifier, labels);
    assertNotNull(inventory);
    assertEquals(identifier, inventory.getItemIdentifier());
    assertEquals(labels, inventory.getLabelPath());
  }

  @Test
  @DisplayName("ItemInventory should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    ItemIdentifier identifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "value")));
    List<String> labels = List.of("label1", "label2");
    ItemInventory inventory1 = ItemInventory.of(identifier, labels);
    ItemInventory inventory2 = ItemInventory.of(identifier, labels);
    assertEquals(inventory1, inventory2);
    assertEquals(inventory1.hashCode(), inventory2.hashCode());
  }

  @Test
  @DisplayName("ItemInventory should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    ItemIdentifier identifier1 =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key1", "value1")));
    ItemIdentifier identifier2 =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key2", "value2")));
    List<String> labels1 = List.of("label1", "label2");
    List<String> labels2 = List.of("label3", "label4");
    ItemInventory inventory1 = ItemInventory.of(identifier1, labels1);
    ItemInventory inventory2 = ItemInventory.of(identifier2, labels2);
    assertNotEquals(inventory1, inventory2);
  }

  @Test
  @DisplayName("ItemInventory factory should fail with null item identifier")
  void shouldFailWithNullItemIdentifier() {
    List<String> labels = List.of("label1", "label2");
    assertThrows(NullPointerException.class, () -> ItemInventory.of(null, labels));
  }

  @Test
  @DisplayName("ItemInventory factory should fail with null label path")
  void shouldFailWithNullLabelPath() {
    ItemIdentifier identifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "value")));
    assertThrows(NullPointerException.class, () -> ItemInventory.of(identifier, null));
  }
}

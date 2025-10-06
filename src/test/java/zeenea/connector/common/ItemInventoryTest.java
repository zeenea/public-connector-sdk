package zeenea.connector.common;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemInventoryTest {

  @Test
  @DisplayName("ItemInventory factory should create item inventory")
  void shouldCreateItemInventory() {
    ItemIdentifier identifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "value")));
    LabelIdentifier labelIdentifier =
        LabelIdentifier.of(
            IdentificationProperty.of("label1", "value1"),
            IdentificationProperty.of("label2", "value2"));
    ItemInventory inventory = ItemInventory.of(identifier, labelIdentifier);
    assertThat(inventory).isNotNull();
    assertThat(inventory.getItemIdentifier()).isEqualTo(identifier);
    assertThat(inventory.getLabelIdentifier()).isEqualTo(labelIdentifier);
  }

  @Test
  @DisplayName("ItemInventory should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    ItemIdentifier identifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "value")));
    LabelIdentifier labelIdentifier =
        LabelIdentifier.of(
            IdentificationProperty.of("label1", "value1"),
            IdentificationProperty.of("label2", "value2"));
    ItemInventory inventory1 = ItemInventory.of(identifier, labelIdentifier);
    ItemInventory inventory2 = ItemInventory.of(identifier, labelIdentifier);
    assertThat(inventory1).isEqualTo(inventory2);
    assertThat(inventory1.hashCode()).isEqualTo(inventory2.hashCode());
  }

  @Test
  @DisplayName("ItemInventory should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    ItemIdentifier identifier1 =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key1", "value1")));
    ItemIdentifier identifier2 =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key2", "value2")));
    LabelIdentifier labelIdentifier1 =
        LabelIdentifier.of(
            IdentificationProperty.of("label1", "value1"),
            IdentificationProperty.of("label2", "value2"));
    LabelIdentifier labelIdentifier2 =
        LabelIdentifier.of(
            IdentificationProperty.of("label1", "value3"),
            IdentificationProperty.of("label2", "value2"));

    ItemInventory inventory1 = ItemInventory.of(identifier1, labelIdentifier1);
    ItemInventory inventory2 = ItemInventory.of(identifier2, labelIdentifier2);
    assertThat(inventory1).isNotEqualTo(inventory2);
  }

  @Test
  @DisplayName("ItemInventory factory should fail with null item identifier")
  void shouldFailWithNullItemIdentifier() {
    LabelIdentifier labelIdentifier =
        LabelIdentifier.of(
            IdentificationProperty.of("label1", "value1"),
            IdentificationProperty.of("label2", "value2"));
    assertThatThrownBy(() -> ItemInventory.of(null, labelIdentifier))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  @DisplayName("ItemInventory factory should fail with null label identifier")
  void shouldFailWithNullLabelIdentifier() {
    ItemIdentifier identifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "value")));
    LabelIdentifier labelIdentifier = null;
    assertThatThrownBy(() -> ItemInventory.of(identifier, labelIdentifier))
        .isInstanceOf(NullPointerException.class);
  }
}

package zeenea.connector.common;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemReferenceTest {

  @Test
  @DisplayName("ItemReference factory should create item reference")
  void shouldCreateItemReference() {
    ItemIdentifier identifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "value")));
    DataSourceIdentifier dataSourceIdentifier =
        DataSourceIdentifier.of(
            List.of(
                IdentificationProperty.of("host", "localhost"),
                IdentificationProperty.of("port", "1111")));
    ItemReference itemReference = ItemReference.of(identifier, dataSourceIdentifier);
    assertNotNull(itemReference);
    assertEquals(identifier, itemReference.getItemIdentifier());
    assertEquals(Optional.of(dataSourceIdentifier), itemReference.getDataSourceIdentifier());
  }

  @Test
  @DisplayName("ItemReference should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    ItemIdentifier identifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "value")));
    DataSourceIdentifier dataSourceIdentifier =
        DataSourceIdentifier.of(
            List.of(
                IdentificationProperty.of("host", "localhost"),
                IdentificationProperty.of("port", "1111")));
    ItemReference itemReference1 = ItemReference.of(identifier, dataSourceIdentifier);
    ItemReference itemReference2 = ItemReference.of(identifier, dataSourceIdentifier);
    assertEquals(itemReference1, itemReference2);
    assertEquals(itemReference1.hashCode(), itemReference2.hashCode());
  }

  @Test
  @DisplayName("ItemReference should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    ItemIdentifier identifier1 =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key1", "value1")));
    ItemIdentifier identifier2 =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key2", "value2")));
    DataSourceIdentifier dataSourceIdentifier1 =
        DataSourceIdentifier.of(
            List.of(
                IdentificationProperty.of("host", "localhost"),
                IdentificationProperty.of("port", "1111")));
    DataSourceIdentifier dataSourceIdentifier2 =
        DataSourceIdentifier.of(
            List.of(
                IdentificationProperty.of("host", "localhost"),
                IdentificationProperty.of("port", "2222")));
    ItemReference itemReference1 = ItemReference.of(identifier1, dataSourceIdentifier1);
    ItemReference itemReference2 = ItemReference.of(identifier2, dataSourceIdentifier2);
    assertNotEquals(itemReference1, itemReference2);
  }

  @Test
  @DisplayName("ItemReference factory should fail with null item identifier")
  void shouldFailWithNullItemIdentifier() {
    DataSourceIdentifier dataSourceIdentifier =
        DataSourceIdentifier.of(
            List.of(
                IdentificationProperty.of("host", "localhost"),
                IdentificationProperty.of("port", "1111")));
    assertThrows(NullPointerException.class, () -> ItemReference.of(null, dataSourceIdentifier));
  }
}

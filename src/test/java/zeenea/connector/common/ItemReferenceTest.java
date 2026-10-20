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

  private static final class CustomItemReference extends ItemReference {
    CustomItemReference(ItemIdentifier itemIdentifier, DataSourceIdentifier dataSourceIdentifier) {
      super(itemIdentifier, dataSourceIdentifier);
    }
  }

  @Test
  @DisplayName("ItemReference subclass should expose identifiers and accept null data source")
  void subclassShouldExposeIdentifiers() {
    ItemIdentifier identifier = ItemIdentifier.of(IdentificationProperty.of("key", "value"));
    DataSourceIdentifier dataSourceIdentifier =
        DataSourceIdentifier.of(IdentificationProperty.of("host", "localhost"));

    ItemReference custom = new CustomItemReference(identifier, dataSourceIdentifier);
    assertEquals(identifier, custom.getItemIdentifier());
    assertEquals(Optional.of(dataSourceIdentifier), custom.getDataSourceIdentifier());

    ItemReference withoutDataSource = new CustomItemReference(identifier, null);
    assertEquals(Optional.empty(), withoutDataSource.getDataSourceIdentifier());
    assertThrows(NullPointerException.class, () -> new CustomItemReference(null, null));
  }

  @Test
  @DisplayName("ItemReference subclass should not be equal to a base instance (getClass semantics)")
  void subclassShouldNotBeEqualToBaseInstance() {
    ItemIdentifier identifier = ItemIdentifier.of(IdentificationProperty.of("key", "value"));
    ItemReference base = ItemReference.of(identifier);
    ItemReference custom = new CustomItemReference(identifier, null);
    assertNotEquals(base, custom);
    assertNotEquals(custom, base);
    assertEquals(custom, new CustomItemReference(identifier, null));
  }
}

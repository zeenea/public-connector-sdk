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
    ConnectionReference connection = ConnectionReferenceAlias.of("connectionAlias");
    ItemReference itemReference = ItemReference.of(identifier, connection);
    assertNotNull(itemReference);
    assertEquals(identifier, itemReference.getItemIdentifier());
    assertEquals(Optional.of(connection), itemReference.getConnectionReference());
  }

  @Test
  @DisplayName("ItemReference should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    ItemIdentifier identifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "value")));
    ConnectionReference connection = ConnectionReferenceAlias.of("connectionAlias");
    ItemReference itemReference1 = ItemReference.of(identifier, connection);
    ItemReference itemReference2 = ItemReference.of(identifier, connection);
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
    ConnectionReference connection1 = ConnectionReferenceAlias.of("connectionAlias1");
    ConnectionReference connection2 = ConnectionReferenceAlias.of("connectionAlias2");
    ItemReference itemReference1 = ItemReference.of(identifier1, connection1);
    ItemReference itemReference2 = ItemReference.of(identifier2, connection2);
    assertNotEquals(itemReference1, itemReference2);
  }

  @Test
  @DisplayName("ItemReference factory should fail with null item identifier")
  void shouldFailWithNullItemIdentifier() {
    ConnectionReference connection = ConnectionReferenceAlias.of("connectionAlias");
    assertThrows(NullPointerException.class, () -> ItemReference.of(null, connection));
  }
}

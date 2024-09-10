package zeenea.connector.common;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemIdentifierTest {

  @Test
  @DisplayName("ItemIdentifier factory should create item identifier")
  void shouldCreateItemIdentifier() {
    IdentificationProperty prop = IdentificationProperty.of("key", "value");
    ItemIdentifier identifier = ItemIdentifier.of(List.of(prop));
    assertNotNull(identifier);
    assertEquals(1, identifier.getIdentificationProperties().size());
    assertEquals(prop, identifier.getIdentificationProperties().get(0));
  }

  @Test
  @DisplayName("ItemIdentifier with prefix should create a new item identifier")
  void shouldCreateItemIdentifierWithPrefix() {
    IdentificationProperty prop1 = IdentificationProperty.of("key1", "value1");
    IdentificationProperty prop2 = IdentificationProperty.of("key2", "value2");
    ItemIdentifier identifier1 = ItemIdentifier.of(List.of(prop1));
    ItemIdentifier identifier2 = identifier1.withPrefix(prop2);
    assertEquals(1, identifier1.getIdentificationProperties().size());
    assertEquals(2, identifier2.getIdentificationProperties().size());
    assertEquals(prop2, identifier2.getIdentificationProperties().get(0));
    assertEquals(prop1, identifier2.getIdentificationProperties().get(1));
    assertEquals(prop1, identifier1.getIdentificationProperties().get(0));
  }

  @Test
  @DisplayName("ItemIdentifier with suffix should create a new item identifier")
  void shouldCreateItemIdentifierWithSuffix() {
    IdentificationProperty prop1 = IdentificationProperty.of("key1", "value1");
    IdentificationProperty prop2 = IdentificationProperty.of("key2", "value2");
    ItemIdentifier identifier1 = ItemIdentifier.of(List.of(prop1));
    ItemIdentifier identifier2 = identifier1.withSuffix(prop2);
    assertEquals(1, identifier1.getIdentificationProperties().size());
    assertEquals(2, identifier2.getIdentificationProperties().size());
    assertEquals(prop1, identifier2.getIdentificationProperties().get(0));
    assertEquals(prop2, identifier2.getIdentificationProperties().get(1));
    assertEquals(prop1, identifier1.getIdentificationProperties().get(0));
  }

  @Test
  @DisplayName("ItemIdentifier factory should fail with empty identification properties")
  void shouldFailWithEmptyIdentificationProperties() {
    assertThrows(
        IllegalArgumentException.class,
        () -> ItemIdentifier.of(List.of()),
        "identificationProperties cannot be null");
  }
}

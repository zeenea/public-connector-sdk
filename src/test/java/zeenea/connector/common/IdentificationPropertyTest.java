package zeenea.connector.common;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IdentificationPropertyTest {

  @Test
  @DisplayName("IdentificationProperty factory should create identification property")
  void shouldCreateIdentificationProperty() {
    IdentificationProperty prop = IdentificationProperty.of("key", "value");
    assertNotNull(prop);
    assertEquals("key", prop.getKey());
    assertEquals("value", prop.getValue());
  }

  @Test
  @DisplayName("IdentificationProperty should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    IdentificationProperty prop1 = IdentificationProperty.of("key", "value");
    IdentificationProperty prop2 = IdentificationProperty.of("key", "value");
    assertEquals(prop1, prop2);
    assertEquals(prop1.hashCode(), prop2.hashCode());
  }

  @Test
  @DisplayName("IdentificationProperty should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    IdentificationProperty prop1 = IdentificationProperty.of("key1", "value1");
    IdentificationProperty prop2 = IdentificationProperty.of("key2", "value2");
    assertNotEquals(prop1, prop2);
  }

  @Test
  @DisplayName("IdentificationProperty factory should fail with null key")
  void shouldFailWithNullKey() {
    assertThrows(NullPointerException.class, () -> IdentificationProperty.of(null, "value"));
  }

  @Test
  @DisplayName("IdentificationProperty factory should fail with null value")
  void shouldFailWithNullValue() {
    assertThrows(NullPointerException.class, () -> IdentificationProperty.of("key", null));
  }
}

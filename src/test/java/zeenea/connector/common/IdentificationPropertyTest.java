package zeenea.connector.common;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IdentificationPropertyTest {

  @Test
  @DisplayName("IdentificationProperty factory should create identification property")
  void shouldCreateIdentificationProperty() {
    IdentificationProperty prop = IdentificationProperty.of("key", "value");
    assertThat(prop).isNotNull();
    assertThat(prop.getKey()).isEqualTo("key");
    assertThat(prop.getValue()).isEqualTo("value");
  }

  @Test
  @DisplayName("IdentificationProperty should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    IdentificationProperty prop1 = IdentificationProperty.of("key", "value");
    IdentificationProperty prop2 = IdentificationProperty.of("key", "value");
    assertThat(prop1).isEqualTo(prop2);
    assertThat(prop1.hashCode()).isEqualTo(prop2.hashCode());
  }

  @Test
  @DisplayName("IdentificationProperty should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    IdentificationProperty prop1 = IdentificationProperty.of("key1", "value1");
    IdentificationProperty prop2 = IdentificationProperty.of("key2", "value2");
    assertThat(prop1).isNotEqualTo(prop2);
  }

  @Test
  @DisplayName("IdentificationProperty factory should fail with null key")
  void shouldFailWithNullKey() {
    assertThatThrownBy(() -> IdentificationProperty.of(null, "value"))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  @DisplayName("IdentificationProperty factory should fail with null value")
  void shouldFailWithNullValue() {
    assertThatThrownBy(() -> IdentificationProperty.of("key", null))
        .isInstanceOf(NullPointerException.class);
  }
}

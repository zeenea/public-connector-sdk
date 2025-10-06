package zeenea.connector.common;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ItemIdentifierTest {

  @Test
  @DisplayName("ItemIdentifier factory should create item identifier")
  void shouldCreateItemIdentifier() {
    IdentificationProperty prop = IdentificationProperty.of("key", "value");
    ItemIdentifier identifier = ItemIdentifier.of(List.of(prop));
    assertThat(identifier).isNotNull();
    assertThat(identifier.getIdentificationProperties()).hasSize(1);
    assertThat(identifier.getIdentificationProperties().get(0)).isEqualTo(prop);
  }

  @Test
  @DisplayName("ItemIdentifier with prefix should create a new item identifier")
  void shouldCreateItemIdentifierWithPrefix() {
    IdentificationProperty prop1 = IdentificationProperty.of("key1", "value1");
    IdentificationProperty prop2 = IdentificationProperty.of("key2", "value2");
    ItemIdentifier identifier1 = ItemIdentifier.of(List.of(prop1));
    ItemIdentifier identifier2 = identifier1.withPrefix(prop2);
    assertThat(identifier1.getIdentificationProperties()).hasSize(1);
    assertThat(identifier2.getIdentificationProperties()).hasSize(2);
    assertThat(identifier2.getIdentificationProperties().get(0)).isEqualTo(prop2);
    assertThat(identifier2.getIdentificationProperties().get(1)).isEqualTo(prop1);
    assertThat(identifier1.getIdentificationProperties().get(0)).isEqualTo(prop1);
  }

  @Test
  @DisplayName("ItemIdentifier with suffix should create a new item identifier")
  void shouldCreateItemIdentifierWithSuffix() {
    IdentificationProperty prop1 = IdentificationProperty.of("key1", "value1");
    IdentificationProperty prop2 = IdentificationProperty.of("key2", "value2");
    ItemIdentifier identifier1 = ItemIdentifier.of(List.of(prop1));
    ItemIdentifier identifier2 = identifier1.withSuffix(prop2);
    assertThat(identifier1.getIdentificationProperties()).hasSize(1);
    assertThat(identifier2.getIdentificationProperties()).hasSize(2);
    assertThat(identifier2.getIdentificationProperties().get(0)).isEqualTo(prop1);
    assertThat(identifier2.getIdentificationProperties().get(1)).isEqualTo(prop2);
    assertThat(identifier1.getIdentificationProperties().get(0)).isEqualTo(prop1);
  }

  @Test
  @DisplayName("ItemIdentifier should retrieve a unique property value by key")
  void shouldRetrieveAUniqueProperty() {
    IdentificationProperty prop1 = IdentificationProperty.of("key1", "value1");
    IdentificationProperty prop2 = IdentificationProperty.of("key2", "value2");
    ItemIdentifier identifier = ItemIdentifier.of(List.of(prop1, prop2));
    Optional<String> map = identifier.getUniquePropertyValue("key2");
    assertThat(map).isPresent().hasValue("value2");
  }

  @Test
  @DisplayName("ItemIdentifier should retrieve an empty property value by key")
  void shouldRetrieveAnEmptyProperty() {
    IdentificationProperty prop1 = IdentificationProperty.of("key1", "value1");
    IdentificationProperty prop2 = IdentificationProperty.of("key2", "value2");
    ItemIdentifier identifier = ItemIdentifier.of(List.of(prop1, prop2));
    Optional<String> map = identifier.getUniquePropertyValue("key3");
    assertThat(map).isEmpty();
  }

  @Test
  @DisplayName(
      "ItemIdentifier should throw an exception when retrieving unique property value by key")
  void shouldThrowAnExceptionWhenRetrievingAUniqueProperty() {
    IdentificationProperty prop1 = IdentificationProperty.of("key1", "value1");
    IdentificationProperty prop2 = IdentificationProperty.of("key1", "value2");
    ItemIdentifier identifier = ItemIdentifier.of(List.of(prop1, prop2));
    assertThatThrownBy(() -> identifier.getUniquePropertyValue("key1"))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("Multiple values found for key: key1");
  }

  @Test
  @DisplayName("ItemIdentifier should retrieve a list of properties value by key")
  void shouldRetrieveAListOfProperties() {
    IdentificationProperty prop1 = IdentificationProperty.of("key1", "value1");
    IdentificationProperty prop2 = IdentificationProperty.of("key1", "value2");
    ItemIdentifier identifier = ItemIdentifier.of(List.of(prop1, prop2));
    List<String> map = identifier.getPropertyValues("key1");
    assertThat(map).hasSize(2).containsExactlyInAnyOrder("value1", "value2");
  }

  @Test
  @DisplayName("ItemIdentifier factory should fail with empty identification properties")
  void shouldFailWithEmptyIdentificationProperties() {
    assertThatThrownBy(() -> ItemIdentifier.of(List.of()))
        .isInstanceOf(IllegalArgumentException.class)
        .describedAs("identificationProperties cannot be null");
  }
}

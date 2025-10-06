package zeenea.connector.common;

import static org.assertj.core.api.Assertions.*;

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
    assertThat(itemReference).isNotNull();
    assertThat(itemReference.getItemIdentifier()).isEqualTo(identifier);
    assertThat(itemReference.getDataSourceIdentifier())
        .isEqualTo(Optional.of(dataSourceIdentifier));
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
    assertThat(itemReference1).isEqualTo(itemReference2);
    assertThat(itemReference1.hashCode()).isEqualTo(itemReference2.hashCode());
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
    assertThat(itemReference1).isNotEqualTo(itemReference2);
  }

  @Test
  @DisplayName("ItemReference factory should fail with null item identifier")
  void shouldFailWithNullItemIdentifier() {
    DataSourceIdentifier dataSourceIdentifier =
        DataSourceIdentifier.of(
            List.of(
                IdentificationProperty.of("host", "localhost"),
                IdentificationProperty.of("port", "1111")));
    assertThatThrownBy(() -> ItemReference.of(null, dataSourceIdentifier))
        .isInstanceOf(NullPointerException.class);
  }
}

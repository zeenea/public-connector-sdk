package zeenea.connector.common;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DataSourceIdentifierTest {

  private static final class CustomDataSourceIdentifier extends DataSourceIdentifier {
    CustomDataSourceIdentifier(List<IdentificationProperty> identificationProperties) {
      super(identificationProperties);
    }
  }

  @Test
  @DisplayName("DataSourceIdentifier should retrieve a unique property value by key")
  void shouldRetrieveUniquePropertyValue() {
    DataSourceIdentifier identifier =
        DataSourceIdentifier.of(
            IdentificationProperty.of("host", "localhost"),
            IdentificationProperty.of("port", "1111"));
    assertEquals(Optional.of("localhost"), identifier.getUniquePropertyValue("host"));
    assertEquals(Optional.empty(), identifier.getUniquePropertyValue("database"));
  }

  @Test
  @DisplayName("DataSourceIdentifier should fail to retrieve a unique value for a repeated key")
  void shouldFailToRetrieveUniquePropertyValueForRepeatedKey() {
    DataSourceIdentifier identifier =
        DataSourceIdentifier.of(
            IdentificationProperty.of("host", "host1"), IdentificationProperty.of("host", "host2"));
    Assertions.assertThatThrownBy(() -> identifier.getUniquePropertyValue("host"))
        .isInstanceOf(IllegalStateException.class)
        .hasMessage("Multiple values found for key: host");
  }

  @Test
  @DisplayName("DataSourceIdentifier should retrieve a list of property values by key")
  void shouldRetrievePropertyValues() {
    DataSourceIdentifier identifier =
        DataSourceIdentifier.of(
            IdentificationProperty.of("host", "host1"), IdentificationProperty.of("host", "host2"));
    Assertions.assertThat(identifier.getPropertyValues("host")).containsExactly("host1", "host2");
    Assertions.assertThat(identifier.getPropertyValues("port")).isEmpty();
  }

  @Test
  @DisplayName(
      "DataSourceIdentifier subclass should be equal to a base instance with same properties")
  void subclassShouldBeEqualToBaseInstance() {
    List<IdentificationProperty> properties = List.of(IdentificationProperty.of("host", "h"));
    DataSourceIdentifier base = DataSourceIdentifier.of(properties);
    DataSourceIdentifier custom = new CustomDataSourceIdentifier(properties);
    assertEquals(base, custom);
    assertEquals(custom, base);
    assertEquals(base.hashCode(), custom.hashCode());
  }
}

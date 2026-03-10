package zeenea.connector.common;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DataSourceIdentifierTest {

  @Test
  @DisplayName("should retrieve unique property value")
  public void shouldRetrieveAUniqueProperty() {
    IdentificationProperty prop1 = IdentificationProperty.of("key1", "value1");
    IdentificationProperty prop2 = IdentificationProperty.of("key2", "value2");

    DataSourceIdentifier identifier = DataSourceIdentifier.of(List.of(prop1, prop2));

    Optional<String> map = identifier.getUniquePropertyValue("key2");
    assertThat(map).isPresent().hasValue("value2");
  }

  @Test
  @DisplayName("should retrieve identification properties")
  public void shouldRetrieveIdentificationProperties() {
    IdentificationProperty prop1 = IdentificationProperty.of("key1", "value1");
    IdentificationProperty prop2 = IdentificationProperty.of("key2", "value2");

    DataSourceIdentifier identifier = DataSourceIdentifier.of(List.of(prop1, prop2));

    var identificationProperties = identifier.getIdentificationProperties();
    assertThat(identificationProperties).size().isEqualTo(2);
    assertThat(identificationProperties).contains(prop1);
    assertThat(identificationProperties).contains(prop2);
  }

  @Test
  @DisplayName("should create a valid DataSourceIdentifier from a list")
  public void shouldCreateAValidDataSourceIdentifier() {
    IdentificationProperty prop1 = IdentificationProperty.of("key1", "value1");
    IdentificationProperty prop2 = IdentificationProperty.of("key2", "value2");

    DataSourceIdentifier identifier = DataSourceIdentifier.of(List.of(prop1, prop2));

    assertThat(identifier).isNotNull();
  }

  @Test
  @DisplayName("should create a valid DataSourceIdentifier without list")
  public void shouldCreateAValidDataSourceIdentifier2() {
    IdentificationProperty prop1 = IdentificationProperty.of("key1", "value1");
    IdentificationProperty prop2 = IdentificationProperty.of("key2", "value2");

    DataSourceIdentifier identifier = DataSourceIdentifier.of(prop1, prop2);

    assertThat(identifier).isNotNull();
  }

  @Test
  @DisplayName("should create a valid DataSourceIdentifier with builder")
  public void shouldCreateAValidDataSourceIdentifierWithBuilder() {
    IdentificationProperty prop1 = IdentificationProperty.of("key1", "value1");
    IdentificationProperty prop2 = IdentificationProperty.of("key2", "value2");

    DataSourceIdentifier dataSourceIdentifier =
        DataSourceIdentifier.builder().identificationProperties(List.of(prop1, prop2)).build();

    assertThat(dataSourceIdentifier).isNotNull();
  }
}

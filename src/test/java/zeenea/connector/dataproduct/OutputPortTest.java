package zeenea.connector.dataproduct;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.IdentificationProperty;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.dataset.Dataset;
import zeenea.connector.property.PropertyValue;

class OutputPortTest {

  @Test
  @DisplayName("OutputPort builder should create output port")
  void shouldCreateOutputPortWithBuilder() {
    DataContract dataContract = DataContract.of(DataContract.Type.Custom, "sourceValue");
    Dataset dataset1 =
        Dataset.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("name", "dataset1"))))
            .name("Dataset1")
            .build();

    List<Dataset> datasets = List.of(dataset1);

    Map<String, PropertyValue> properties =
        Map.of("key1", PropertyValue.string("value1"), "key2", PropertyValue.string("value2"));
    OutputPort outputPort =
        OutputPort.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("id", "output-port-1"))))
            .name("OutputPort1")
            .description("Description1")
            .dataContract(dataContract)
            .datasets(datasets)
            .properties(properties)
            .build();
    assertThat(outputPort).isNotNull();
    assertThat(outputPort.getName()).isEqualTo("OutputPort1");
    assertThat(outputPort.getDescription()).contains("Description1");
    assertThat(outputPort.getDataContract()).isEqualTo(dataContract);
    assertThat(outputPort.getDatasets()).isEqualTo(datasets);
    assertThat(outputPort.getProperties()).isEqualTo(properties);
  }

  @Test
  @DisplayName("OutputPort should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    DataContract dataContract = DataContract.of(DataContract.Type.Custom, "sourceValue");
    Dataset dataset1 =
        Dataset.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("name", "dataset1"))))
            .name("Dataset1")
            .build();

    List<Dataset> datasets = List.of(dataset1);
    Map<String, PropertyValue> properties =
        Map.of("key1", PropertyValue.string("value1"), "key2", PropertyValue.string("value2"));
    OutputPort outputPort1 =
        OutputPort.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("id", "output-port-1"))))
            .name("OutputPort1")
            .description("Description1")
            .dataContract(dataContract)
            .datasets(datasets)
            .properties(properties)
            .build();
    OutputPort outputPort2 =
        OutputPort.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("id", "output-port-1"))))
            .name("OutputPort1")
            .description("Description1")
            .dataContract(dataContract)
            .datasets(datasets)
            .properties(properties)
            .build();
    assertThat(outputPort1).isEqualTo(outputPort2);
    assertThat(outputPort1.hashCode()).isEqualTo(outputPort2.hashCode());
  }

  @Test
  @DisplayName("OutputPort should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    DataContract dataContract1 = DataContract.of(DataContract.Type.Custom, "sourceValue1");
    DataContract dataContract2 =
        DataContract.of(DataContract.Type.DataContractDotCom, "sourceValue2");
    Dataset dataset1 =
        Dataset.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("name", "dataset1"))))
            .name("Dataset1")
            .build();
    Dataset dataset2 =
        Dataset.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("name", "dataset2"))))
            .name("Dataset2")
            .build();

    List<Dataset> datasets1 = List.of(dataset1);
    List<Dataset> datasets2 = List.of(dataset2);
    Map<String, PropertyValue> properties1 =
        Map.of("key1", PropertyValue.string("value1"), "key2", PropertyValue.string("value2"));
    Map<String, PropertyValue> properties2 =
        Map.of("key3", PropertyValue.string("value3"), "key4", PropertyValue.string("value4"));
    OutputPort outputPort1 =
        OutputPort.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("id", "output-port-1"))))
            .name("OutputPort1")
            .description("Description1")
            .dataContract(dataContract1)
            .datasets(datasets1)
            .properties(properties1)
            .build();
    OutputPort outputPort2 =
        OutputPort.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("id", "output-port-2"))))
            .name("OutputPort2")
            .description("Description2")
            .dataContract(dataContract2)
            .datasets(datasets2)
            .properties(properties2)
            .build();
    assertThat(outputPort1).isNotEqualTo(outputPort2);
  }

  @Test
  @DisplayName("OutputPort builder should fail with null id")
  void builderShouldFailWithNullId() {
    DataContract dataContract = DataContract.of(DataContract.Type.Custom, "sourceValue");
    Dataset dataset1 =
        Dataset.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("name", "dataset1"))))
            .name("Dataset1")
            .build();

    List<Dataset> datasets = List.of(dataset1);
    Map<String, PropertyValue> properties =
        Map.of("key1", PropertyValue.string("value1"), "key2", PropertyValue.string("value2"));
    assertThatThrownBy(
            () ->
                OutputPort.builder()
                    .id(null)
                    .name("OutputPort1")
                    .description("Description1")
                    .dataContract(dataContract)
                    .datasets(datasets)
                    .properties(properties)
                    .build())
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  @DisplayName("OutputPort builder should fail with null name")
  void builderShouldFailWithNullName() {
    DataContract dataContract = DataContract.of(DataContract.Type.Custom, "sourceValue");
    Dataset dataset1 =
        Dataset.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("name", "dataset1"))))
            .name("Dataset1")
            .build();

    List<Dataset> datasets = List.of(dataset1);
    Map<String, PropertyValue> properties =
        Map.of("key1", PropertyValue.string("value1"), "key2", PropertyValue.string("value2"));
    assertThatThrownBy(
            () ->
                OutputPort.builder()
                    .id(
                        ItemIdentifier.of(
                            List.of(IdentificationProperty.of("id", "output-port-1"))))
                    .name(null)
                    .description("Description1")
                    .dataContract(dataContract)
                    .datasets(datasets)
                    .properties(properties)
                    .build())
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  @DisplayName("OutputPort builder should fail with null data contract")
  void builderShouldFailWithNullDataContract() {
    Dataset dataset1 =
        Dataset.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("name", "dataset1"))))
            .name("Dataset1")
            .build();

    List<Dataset> datasets = List.of(dataset1);
    Map<String, PropertyValue> properties =
        Map.of("key1", PropertyValue.string("value1"), "key2", PropertyValue.string("value2"));
    assertThatThrownBy(
            () ->
                OutputPort.builder()
                    .id(
                        ItemIdentifier.of(
                            List.of(IdentificationProperty.of("id", "output-port-1"))))
                    .name("OutputPort1")
                    .description("Description1")
                    .dataContract(null)
                    .datasets(datasets)
                    .properties(properties)
                    .build())
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  @DisplayName("OutputPort builder should fail with null datasets")
  void builderShouldFailWithNullDatasets() {
    DataContract dataContract = DataContract.of(DataContract.Type.Custom, "sourceValue");
    Map<String, PropertyValue> properties =
        Map.of("key1", PropertyValue.string("value1"), "key2", PropertyValue.string("value2"));
    assertThatThrownBy(
            () ->
                OutputPort.builder()
                    .id(
                        ItemIdentifier.of(
                            List.of(IdentificationProperty.of("id", "output-port-1"))))
                    .name("OutputPort1")
                    .description("Description1")
                    .dataContract(dataContract)
                    .datasets((Dataset) null)
                    .properties(properties)
                    .build())
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  @DisplayName("OutputPort builder should fail with null properties")
  void builderShouldFailWithNullProperties() {
    DataContract dataContract = DataContract.of(DataContract.Type.Custom, "sourceValue");
    Dataset dataset1 =
        Dataset.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("name", "dataset1"))))
            .name("Dataset1")
            .build();

    List<Dataset> datasets = List.of(dataset1);
    assertThatThrownBy(
            () ->
                OutputPort.builder()
                    .id(
                        ItemIdentifier.of(
                            List.of(IdentificationProperty.of("id", "output-port-1"))))
                    .name("OutputPort1")
                    .description("Description1")
                    .dataContract(dataContract)
                    .datasets(datasets)
                    .properties(null)
                    .build())
        .isInstanceOf(NullPointerException.class);
  }
}

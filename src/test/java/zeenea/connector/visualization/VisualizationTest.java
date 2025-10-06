package zeenea.connector.visualization;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.*;
import zeenea.connector.dataset.DataType;
import zeenea.connector.field.Field;

class VisualizationTest {

  @Test
  @DisplayName("Visualization builder should create visualization")
  void shouldCreateVisualizationWithBuilder() {
    List<Field> fields =
        List.of(
            Field.builder()
                .id(ItemIdentifier.of(IdentificationProperty.of("key", "field")))
                .name("field")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .build());
    List<ItemReference> linkedDataset =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source"))),
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "visualization")));
    Visualization visualization =
        Visualization.builder()
            .id(itemIdentifier)
            .name("Visualization")
            .description("Description")
            .fields(fields)
            .sourceDatasets(linkedDataset)
            .build();
    assertThat(visualization).isNotNull();
    assertThat(visualization.getId()).isEqualTo(itemIdentifier);
    assertThat(visualization.getFields()).isEqualTo(fields);
    assertThat(visualization.getSourceDatasets()).isEqualTo(linkedDataset);
  }

  @Test
  @DisplayName("Visualization should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    List<Field> fields =
        List.of(
            Field.builder()
                .id(ItemIdentifier.of(IdentificationProperty.of("key", "field")))
                .name("field")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .build());
    List<ItemReference> linkedDataset =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source"))),
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "visualization")));
    Visualization visualization1 =
        Visualization.builder()
            .id(itemIdentifier)
            .name("Visualization")
            .description("Description")
            .fields(fields)
            .sourceDatasets(linkedDataset)
            .build();
    Visualization visualization2 =
        Visualization.builder()
            .id(itemIdentifier)
            .name("Visualization")
            .description("Description")
            .fields(fields)
            .sourceDatasets(linkedDataset)
            .build();
    assertThat(visualization1).isEqualTo(visualization2);
    assertThat(visualization1.hashCode()).isEqualTo(visualization2.hashCode());
  }

  @Test
  @DisplayName("Visualization should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    List<Field> fields =
        List.of(
            Field.builder()
                .id(ItemIdentifier.of(IdentificationProperty.of("key", "field")))
                .name("field")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .build());
    List<ItemReference> linkedDataset =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source"))),
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));

    Visualization visualization1 =
        Visualization.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "visualization1"))))
            .name("Visualization1")
            .description("Description")
            .fields(fields)
            .sourceDatasets(linkedDataset)
            .build();
    Visualization visualization2 =
        Visualization.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "visualization2"))))
            .name("Visualization2")
            .description("Description")
            .fields(List.of())
            .sourceDatasets(List.of())
            .build();
    assertThat(visualization1).isNotEqualTo(visualization2);
  }

  @Test
  @DisplayName("Visualization builder should fail with null fields")
  void builderShouldFailWithNullFields() {
    List<ItemReference> linkedDataset =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source"))),
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));
    assertThatThrownBy(
            () ->
                Visualization.builder()
                    .id(
                        ItemIdentifier.of(
                            List.of(IdentificationProperty.of("key", "visualization1"))))
                    .name("Visualization1")
                    .description("Description")
                    .fields((Field) null)
                    .sourceDatasets(linkedDataset)
                    .build())
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  @DisplayName("Visualization builder should fail with null linked dataset")
  void builderShouldFailWithNullLinkedDataset() {
    List<Field> fields =
        List.of(
            Field.builder()
                .id(ItemIdentifier.of(IdentificationProperty.of("key", "field")))
                .name("field")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .build());
    assertThatThrownBy(
            () ->
                Visualization.builder()
                    .id(
                        ItemIdentifier.of(
                            List.of(IdentificationProperty.of("key", "visualization"))))
                    .name("Visualization")
                    .description("Description")
                    .fields(fields)
                    .sourceDatasets((List<ItemReference>) null)
                    .build())
        .isInstanceOf(NullPointerException.class);
  }
}

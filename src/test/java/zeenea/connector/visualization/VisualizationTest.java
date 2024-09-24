package zeenea.connector.visualization;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.ConnectionReferenceCode;
import zeenea.connector.common.IdentificationProperty;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.common.ItemReference;
import zeenea.connector.dataset.DataType;

class VisualizationTest {

  @Test
  @DisplayName("Visualization builder should create visualization")
  void shouldCreateVisualizationWithBuilder() {
    List<VisualizationField> fields =
        List.of(
            VisualizationField.builder()
                .fieldType(VisualizationFieldType.Measure)
                .name("field")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .keys(List.of("field"))
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .build());
    List<ItemReference> linkedDataset =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source"))),
                ConnectionReferenceCode.of("reference")));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "visualization")));
    Visualization visualization =
        Visualization.builder()
            .id(itemIdentifier)
            .name("Visualization")
            .description("Description")
            .fields(fields)
            .linkedDataset(linkedDataset)
            .build();
    assertNotNull(visualization);
    assertEquals(itemIdentifier, visualization.getId());
    assertEquals(fields, visualization.getFields());
    assertEquals(linkedDataset, visualization.getLinkedDataset());
  }

  @Test
  @DisplayName("Visualization should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    List<VisualizationField> fields =
        List.of(
            VisualizationField.builder()
                .fieldType(VisualizationFieldType.Measure)
                .name("field")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .keys(List.of("field"))
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .build());
    List<ItemReference> linkedDataset =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source"))),
                ConnectionReferenceCode.of("reference")));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "visualization")));
    Visualization visualization1 =
        Visualization.builder()
            .id(itemIdentifier)
            .name("Visualization")
            .description("Description")
            .fields(fields)
            .linkedDataset(linkedDataset)
            .build();
    Visualization visualization2 =
        Visualization.builder()
            .id(itemIdentifier)
            .name("Visualization")
            .description("Description")
            .fields(fields)
            .linkedDataset(linkedDataset)
            .build();
    assertEquals(visualization1, visualization2);
    assertEquals(visualization1.hashCode(), visualization2.hashCode());
  }

  @Test
  @DisplayName("Visualization should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    List<VisualizationField> fields =
        List.of(
            VisualizationField.builder()
                .fieldType(VisualizationFieldType.Measure)
                .name("field")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .keys(List.of("field"))
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .build());
    List<ItemReference> linkedDataset =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source"))),
                ConnectionReferenceCode.of("reference")));

    Visualization visualization1 =
        Visualization.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "visualization1"))))
            .name("Visualization1")
            .description("Description")
            .fields(fields)
            .linkedDataset(linkedDataset)
            .build();
    Visualization visualization2 =
        Visualization.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "visualization2"))))
            .name("Visualization2")
            .description("Description")
            .fields(List.of())
            .linkedDataset(List.of())
            .build();
    assertNotEquals(visualization1, visualization2);
  }

  @Test
  @DisplayName("Visualization builder should fail with null fields")
  void builderShouldFailWithNullFields() {
    List<ItemReference> linkedDataset =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source"))),
                ConnectionReferenceCode.of("reference")));
    assertThrows(
        NullPointerException.class,
        () ->
            Visualization.builder()
                .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "visualization"))))
                .name("Visualization")
                .description("Description")
                .fields(List.of(null))
                .linkedDataset(linkedDataset)
                .build());
  }

  @Test
  @DisplayName("Visualization builder should fail with null linked dataset")
  void builderShouldFailWithNullLinkedDataset() {
    List<VisualizationField> fields =
        List.of(
            VisualizationField.builder()
                .fieldType(VisualizationFieldType.Measure)
                .name("field")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .keys(List.of("field"))
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .build());
    assertThrows(
        NullPointerException.class,
        () ->
            Visualization.builder()
                .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "visualization"))))
                .name("Visualization")
                .description("Description")
                .fields(fields)
                .linkedDataset(List.of(null))
                .build());
  }
}

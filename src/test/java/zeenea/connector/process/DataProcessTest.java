package zeenea.connector.process;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.ConnectionReferenceCode;
import zeenea.connector.common.IdentificationProperty;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.common.ItemReference;

class DataProcessTest {

  @Test
  @DisplayName("DataProcess builder should create DataProcess")
  void shouldCreateDataProcessWithBuilder() {
    List<ItemReference> source =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source1"))),
                ConnectionReferenceCode.of("reference")));
    List<ItemReference> target =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source2"))),
                ConnectionReferenceCode.of("reference")));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataprocess")));
    DataProcess dataProcess =
        DataProcess.builder()
            .id(itemIdentifier)
            .name("DataProcess")
            .description("Description")
            .source(source)
            .target(target)
            .build();
    assertNotNull(dataProcess);
    assertEquals(itemIdentifier, dataProcess.getId());
    assertEquals(source, dataProcess.getSource());
    assertEquals(target, dataProcess.getTarget());
  }

  @Test
  @DisplayName("DataProcess should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    List<ItemReference> source =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source1"))),
                ConnectionReferenceCode.of("reference")));
    List<ItemReference> target =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source2"))),
                ConnectionReferenceCode.of("reference")));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataprocess")));
    DataProcess dataProcess1 =
        DataProcess.builder()
            .id(itemIdentifier)
            .name("DataProcess")
            .description("Description")
            .source(source)
            .target(target)
            .build();
    DataProcess dataProcess2 =
        DataProcess.builder()
            .id(itemIdentifier)
            .name("DataProcess")
            .description("Description")
            .source(source)
            .target(target)
            .build();
    assertEquals(dataProcess1, dataProcess2);
    assertEquals(dataProcess1.hashCode(), dataProcess2.hashCode());
  }

  @Test
  @DisplayName("DataProcess should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    List<ItemReference> source =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source1"))),
                ConnectionReferenceCode.of("reference")));
    List<ItemReference> target =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source2"))),
                ConnectionReferenceCode.of("reference")));
    DataProcess dataProcess1 =
        DataProcess.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataprocess1"))))
            .name("DataProcess1")
            .description("Description")
            .source(source)
            .target(target)
            .build();
    DataProcess dataProcess2 =
        DataProcess.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataprocess2"))))
            .name("DataProcess2")
            .description("Description")
            .source(target)
            .target(source)
            .build();
    assertNotEquals(dataProcess1, dataProcess2);
  }

  @Test
  @DisplayName("DataProcess builder should fail with null source")
  void builderShouldFailWithNullSource() {
    List<ItemReference> target =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source"))),
                ConnectionReferenceCode.of("reference")));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataprocess")));
    assertThrows(
        NullPointerException.class,
        () ->
            DataProcess.builder()
                .id(itemIdentifier)
                .name("DataProcess1")
                .description("Description")
                .source(null)
                .target(target)
                .build());
  }

  @Test
  @DisplayName("DataProcess builder should fail with null target")
  void builderShouldFailWithNullTarget() {
    List<ItemReference> source =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source"))),
                ConnectionReferenceCode.of("reference")));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataprocess")));
    assertThrows(
        NullPointerException.class,
        () ->
            DataProcess.builder()
                .id(itemIdentifier)
                .name("DataProcess1")
                .description("Description")
                .source(source)
                .target(null)
                .build());
  }
}

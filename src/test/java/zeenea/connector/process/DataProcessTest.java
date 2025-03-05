package zeenea.connector.process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.DataSourceIdentifier;
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
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));
    List<ItemReference> target =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source2"))),
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataprocess")));
    DataProcess dataProcess =
        DataProcess.builder()
            .id(itemIdentifier)
            .name("DataProcess")
            .description("Description")
            .sources(source)
            .targets(target)
            .build();
    assertNotNull(dataProcess);
    assertEquals(itemIdentifier, dataProcess.getId());
    assertEquals(source, dataProcess.getSources());
    assertEquals(target, dataProcess.getTargets());
  }

  @Test
  @DisplayName("DataProcess should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    List<ItemReference> source =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source1"))),
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));
    List<ItemReference> target =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source2"))),
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataprocess")));
    DataProcess dataProcess1 =
        DataProcess.builder()
            .id(itemIdentifier)
            .name("DataProcess")
            .description("Description")
            .sources(source)
            .targets(target)
            .build();
    DataProcess dataProcess2 =
        DataProcess.builder()
            .id(itemIdentifier)
            .name("DataProcess")
            .description("Description")
            .sources(source)
            .targets(target)
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
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));
    List<ItemReference> target =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source2"))),
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));
    DataProcess dataProcess1 =
        DataProcess.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataprocess1"))))
            .name("DataProcess1")
            .description("Description")
            .sources(source)
            .targets(target)
            .build();
    DataProcess dataProcess2 =
        DataProcess.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataprocess2"))))
            .name("DataProcess2")
            .description("Description")
            .sources(target)
            .targets(source)
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
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataprocess")));
    assertThrows(
        NullPointerException.class,
        () ->
            DataProcess.builder()
                .id(itemIdentifier)
                .name("DataProcess1")
                .description("Description")
                .sources((List<ItemReference>) null)
                .targets(target)
                .build());
  }

  @Test
  @DisplayName("DataProcess builder should fail with null target")
  void builderShouldFailWithNullTarget() {
    List<ItemReference> source =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source"))),
                DataSourceIdentifier.of(
                    List.of(
                        IdentificationProperty.of("host", "localhost"),
                        IdentificationProperty.of("port", "1111")))));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataprocess")));
    assertThrows(
        NullPointerException.class,
        () ->
            DataProcess.builder()
                .id(itemIdentifier)
                .name("DataProcess1")
                .description("Description")
                .sources(source)
                .targets((List<ItemReference>) null)
                .build());
  }

  @Test
  @DisplayName("DataProcess builder should not fail with empty target or sources")
  void builderShouldNotFailWithEmptyTargetOrSources() {
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataprocess")));
    DataProcess dataProcess =
        DataProcess.builder()
            .id(itemIdentifier)
            .name("DataProcess1")
            .description("Description")
            .build();
    assertThat(dataProcess.getTargets()).isEmpty();
    assertThat(dataProcess.getSources()).isEmpty();
  }
}

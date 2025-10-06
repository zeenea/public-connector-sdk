package zeenea.connector.process;

import static org.assertj.core.api.Assertions.*;

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
    assertThat(dataProcess).isNotNull();
    assertThat(dataProcess.getId()).isEqualTo(itemIdentifier);
    assertThat(dataProcess.getSources()).isEqualTo(source);
    assertThat(dataProcess.getTargets()).isEqualTo(target);
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
    assertThat(dataProcess1).isEqualTo(dataProcess2);
    assertThat(dataProcess1.hashCode()).isEqualTo(dataProcess2.hashCode());
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
    assertThat(dataProcess1).isNotEqualTo(dataProcess2);
  }

  @Test
  @DisplayName("DataProcess builder should fail with null fields")
  void builderShouldFailWithNullFields() {
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
    assertThatThrownBy(
            () ->
                DataProcess.builder()
                    .id(
                        ItemIdentifier.of(
                            List.of(IdentificationProperty.of("key", "dataprocess1"))))
                    .name("DataProcess1")
                    .description("Description")
                    .sources((ItemReference) null)
                    .targets(target)
                    .build())
        .isInstanceOf(NullPointerException.class);
  }
}

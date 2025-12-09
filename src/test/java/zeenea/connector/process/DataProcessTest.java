package zeenea.connector.process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.*;

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
    List<QueryReference> queries =
        List.of(
            QueryReference.of(
                "SELECT * FROM process_input",
                SqlDialect.SNOWFLAKE,
                DataSourceIdentifier.of(
                    List.of(IdentificationProperty.of("account", "account1")))));

    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataprocess")));
    DataSourceIdentifier dataSourceIdentifier =
        DataSourceIdentifier.of(List.of(IdentificationProperty.of("alias", "mock-dataset")));
    DataProcess dataProcess =
        DataProcess.builder()
            .id(itemIdentifier)
            .dataSourceIdentifier(dataSourceIdentifier)
            .name("DataProcess")
            .description("Description")
            .sources(source)
            .targets(target)
            .queries(queries)
            .build();
    assertNotNull(dataProcess);
    assertEquals(itemIdentifier, dataProcess.getId());
    assertEquals(source, dataProcess.getSources());
    assertEquals(target, dataProcess.getTargets());
    assertEquals(queries, dataProcess.getQueries());
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
    List<QueryReference> queries = List.of(QueryReference.of("SELECT 1", SqlDialect.MYSQL));
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataprocess")));
    DataSourceIdentifier dataSourceIdentifier =
        DataSourceIdentifier.of(List.of(IdentificationProperty.of("alias", "mock-dataset")));
    DataProcess dataProcess1 =
        DataProcess.builder()
            .id(itemIdentifier)
            .dataSourceIdentifier(dataSourceIdentifier)
            .name("DataProcess")
            .description("Description")
            .sources(source)
            .targets(target)
            .queries(queries)
            .build();
    DataProcess dataProcess2 =
        DataProcess.builder()
            .id(itemIdentifier)
            .dataSourceIdentifier(dataSourceIdentifier)
            .name("DataProcess")
            .description("Description")
            .sources(source)
            .targets(target)
            .queries(queries)
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
            .dataSourceIdentifier(
                DataSourceIdentifier.of(
                    List.of(IdentificationProperty.of("alias", "mock-dataset"))))
            .name("DataProcess1")
            .description("Description")
            .sources(source)
            .targets(target)
            .queries(List.of(QueryReference.of("SELECT 1", SqlDialect.MYSQL)))
            .build();
    DataProcess dataProcess2 =
        DataProcess.builder()
            .id(ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataprocess2"))))
            .dataSourceIdentifier(
                DataSourceIdentifier.of(
                    List.of(IdentificationProperty.of("alias", "mock-dataset"))))
            .name("DataProcess2")
            .description("Description")
            .sources(target)
            .targets(source)
            .queries(List.of())
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
    List<QueryReference> queries = List.of(QueryReference.of("SELECT 1", SqlDialect.MYSQL));
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
                .queries(queries)
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
    List<QueryReference> queries = List.of(QueryReference.of("SELECT 1", SqlDialect.MYSQL));
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
                .queries(queries)
                .build());
  }

  @Test
  @DisplayName("DataProcess builder should fail with null queries")
  void builderShouldFailWithNullQueries() {
    List<ItemReference> source =
        List.of(
            ItemReference.of(
                ItemIdentifier.of(List.of(IdentificationProperty.of("name", "source"))),
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
    assertThrows(
        NullPointerException.class,
        () ->
            DataProcess.builder()
                .id(itemIdentifier)
                .name("DataProcess1")
                .description("Description")
                .sources(source)
                .targets(target)
                .queries((List<QueryReference>) null)
                .build());
  }

  @Test
  @DisplayName("DataProcess builder should not fail with empty target or sources")
  void builderShouldNotFailWithEmptyTargetOrSources() {
    ItemIdentifier itemIdentifier =
        ItemIdentifier.of(List.of(IdentificationProperty.of("key", "dataprocess")));
    DataSourceIdentifier dataSourceIdentifier =
        DataSourceIdentifier.of(List.of(IdentificationProperty.of("alias", "mock-dataset")));
    DataProcess dataProcess =
        DataProcess.builder()
            .id(itemIdentifier)
            .dataSourceIdentifier(dataSourceIdentifier)
            .name("DataProcess1")
            .description("Description")
            .build();
    assertThat(dataProcess.getTargets()).isEmpty();
    assertThat(dataProcess.getSources()).isEmpty();
    assertThat(dataProcess.getQueries()).isEmpty();
  }
}

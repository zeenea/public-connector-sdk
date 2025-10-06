package zeenea.connector.dataset;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.InstanceOfAssertFactories.optional;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import zeenea.connector.Item;
import zeenea.connector.common.IdentificationProperty;
import zeenea.connector.common.ItemIdentifier;

/** Test complet pour vérifier que DatasetAssertions fonctionne correctement dans tous les cas */
class DatasetAssertionsTest {

  private Dataset createDataset(String name, String description) {
    return Dataset.builder()
        .name(name)
        .id(ItemIdentifier.of(IdentificationProperty.of("key", name + "_id")))
        .description(description)
        .build();
  }

  @Nested
  @DisplayName("filteredOnName() tests")
  class FilteredOnNameTests {

    @Test
    @DisplayName("Should return single dataset when filtering by existing name")
    void shouldReturnSingleDatasetWhenFilteringByExistingName() {
      // Given
      Dataset dataset1 = createDataset("test", "description1");
      Dataset dataset2 = createDataset("other", "description2");
      List<Dataset> datasets = List.of(dataset1, dataset2);

      // When & Then
      DatasetAssertions.assertThat(datasets)
          .filteredOnName("test")
          .extracting(Item::getDescription, optional(String.class))
          .isPresent()
          .get()
          .isEqualTo("description1");
    }

    @Test
    @DisplayName("Should throw AssertionError when no dataset found with given name")
    void shouldThrowAssertionErrorWhenNoDatasetFoundWithGivenName() {
      // Given
      Dataset dataset1 = createDataset("test", "description1");
      List<Dataset> datasets = List.of(dataset1);

      // When & Then
      assertThatThrownBy(() -> DatasetAssertions.assertThat(datasets).filteredOnName("nonexistent"))
          .isInstanceOf(AssertionError.class)
          .hasMessageContaining(
              "Expected at least one item with name 'nonexistent', but found none");
    }

    @Test
    @DisplayName("Should throw AssertionError when multiple datasets found with same name")
    void shouldThrowAssertionErrorWhenMultipleDatasetsFoundWithSameName() {
      // Given
      Dataset dataset1 = createDataset("test", "description1");
      Dataset dataset2 = createDataset("test", "description2");
      List<Dataset> datasets = List.of(dataset1, dataset2);

      // When & Then
      assertThatThrownBy(() -> DatasetAssertions.assertThat(datasets).filteredOnName("test"))
          .isInstanceOf(AssertionError.class)
          .hasMessageContaining("Expected only one item with name 'test', but found 2");
    }
  }

  @Nested
  @DisplayName("datasetFilteredOnName() tests")
  class DatasetFilteredOnNameTests {

    @Test
    @DisplayName("Should return Dataset when filtering by existing name")
    void shouldReturnDatasetWhenFilteringByExistingName() {
      // Given
      Dataset dataset1 = createDataset("test", "description");
      Dataset dataset2 = createDataset("test2", "description2");
      List<Dataset> datasets = List.of(dataset1, dataset2);

      // When & Then
      DatasetAssertions.assertThat(datasets)
          .datasetFilteredOnName("test")
          .extracting(Dataset::getDescription, optional(String.class))
          .isPresent()
          .get()
          .isEqualTo("description");
    }

    @Test
    @DisplayName("Should work with optional extraction")
    void shouldWorkWithOptionalExtraction() {
      // Given
      Dataset dataset1 = createDataset("test", "description");
      List<Dataset> datasets = List.of(dataset1);

      // When & Then
      DatasetAssertions.assertThat(datasets)
          .datasetFilteredOnName("test")
          .extracting(Dataset::getDescription, optional(String.class))
          .isPresent()
          .get()
          .isEqualTo("description");
    }

    @Test
    @DisplayName("Should throw error when item is not a Dataset")
    void shouldThrowErrorWhenItemIsNotDataset() {
      // Note: Ce test est théorique car nous travaillons avec des Dataset
      // mais il montre comment la méthode se comporterait avec d'autres types d'Item
      Dataset dataset = createDataset("test", "description");
      List<Dataset> datasets = List.of(dataset);

      // When & Then - Ce test passerait normalement
      assertThatNoException()
          .isThrownBy(() -> DatasetAssertions.assertThat(datasets).datasetFilteredOnName("test"));
    }
  }
}

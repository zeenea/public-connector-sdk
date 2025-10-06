package zeenea.connector.dataset;

import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.ListAssert;
import org.assertj.core.api.ObjectAssert;
import zeenea.connector.Item;

/**
 * Custom AssertJ assertions for Dataset collections. Provides convenient methods for filtering and
 * asserting on Dataset lists.
 */
public class DatasetAssertions extends ListAssert<Item> {

  public DatasetAssertions(List<? extends Item> actual) {
    super(actual);
  }

  /**
   * Static factory method to create DatasetAssertions instances.
   *
   * @param actual the list of datasets to assert on
   * @return a new DatasetAssertions instance
   */
  public static DatasetAssertions assertThat(List<? extends Item> actual) {
    return new DatasetAssertions(actual);
  }

  public ObjectAssert<? extends Item> filteredOnName(String name) {
    return new ObjectAssert<>(getItems(name));
  }

  private Item getItems(String name) {
    isNotNull();
    List<Item> filtered =
        actual.stream().filter(item -> name.equals(item.getName())).collect(Collectors.toList());
    if (filtered.isEmpty()) {
      throw new AssertionError(
          "Expected at least one item with name '" + name + "', but found none");
    }
    if (filtered.size() > 1) {
      throw new AssertionError(
          "Expected only one item with name '" + name + "', but found " + filtered.size());
    }
    objects.getFailures().failure("test");
    return filtered.get(0);
  }

  public ObjectAssert<Dataset> datasetFilteredOnName(String name) {
    Item dataset = getItems(name);
    if (!(dataset instanceof Dataset)) {
      throw new AssertionError(
          "Expected item with name '"
              + name
              + "' to be a Dataset, but was "
              + dataset.getClass().getSimpleName());
    }
    return new ObjectAssert<>((Dataset) dataset);
  }
}

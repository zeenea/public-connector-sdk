package zeenea.connector.property;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PropertiesBuilderTest {

  @Test
  public void testPropertiesBuilder() {
    Map<String, PropertyValue> zeenea =
        PropertiesBuilder.create().put(CatalogProperty.of("ZEENEA")).build();
    var expected = Map.of("Catalog", PropertyValue.tag(List.of("ZEENEA")));
    Assertions.assertThat(zeenea).containsExactlyInAnyOrderEntriesOf(expected);
  }
}

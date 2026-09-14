package zeenea.connector;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.filter.Filter;
import zeenea.connector.common.filter.FilterConfiguration;

class ConnectionConfigurationTest {

  /** Accept-all stub: the only thing these tests care about is identity of the returned object. */
  private static final FilterConfiguration LEGACY_FILTERS =
      new FilterConfiguration() {
        @Override
        public boolean accepts(Map<String, String> filteredProperties) {
          return true;
        }

        @Override
        public List<Filter> getFilters() {
          return List.of();
        }
      };

  /** Minimal implementer that predates the split: it only knows about getFilters(). */
  private static final class LegacyOnlyConfiguration implements ConnectionConfiguration {
    @Override
    public Path getScannerHomeFolder() {
      return Path.of("");
    }

    @Override
    public String getConnectorId() {
      return "connector-id";
    }

    @Override
    public String getConnectionName() {
      return "connection-name";
    }

    @Override
    public String getConnectionCode() {
      return "connection-code";
    }

    @Override
    public String getString(String key) {
      return null;
    }

    @Override
    public Long getLong(String key) {
      return null;
    }

    @Override
    public Boolean getBoolean(String key) {
      throw new UnsupportedOperationException("not used by this test");
    }

    @Override
    public Path getPath(String key) {
      return null;
    }

    @Override
    public FilterConfiguration getFilters() {
      return LEGACY_FILTERS;
    }

    @Override
    public Map<String, String> getMap(String key) {
      return Map.of();
    }

    @Override
    public List<String> getList(String key) {
      return List.of();
    }
  }

  @Test
  void inventory_filters_default_to_legacy_filters() {
    ConnectionConfiguration configuration = new LegacyOnlyConfiguration();

    assertThat(configuration.getInventoryFilters()).isSameAs(LEGACY_FILTERS);
  }

  @Test
  void sampling_filters_default_to_legacy_filters() {
    ConnectionConfiguration configuration = new LegacyOnlyConfiguration();

    assertThat(configuration.getSamplingFilters()).isSameAs(LEGACY_FILTERS);
  }
}

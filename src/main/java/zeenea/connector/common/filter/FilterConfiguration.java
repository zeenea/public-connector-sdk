package zeenea.connector.common.filter;

import java.util.List;

/**
 * Interface representing a configuration for filters. This interface provides a method to retrieve
 * a list of filters.
 */
public interface FilterConfiguration {
  /**
   * Get the list of filters defined in this configuration.
   *
   * @return a list of filters
   */
  List<Filter> getFilters();
}

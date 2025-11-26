package zeenea.connector.common.filter;

import java.util.List;
import java.util.Map;

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

  /**
   * Applies whitelist filtering to be used in a pre-filtering step inside a connector.
   *
   * @param propertiesToFilterOn a map representing the key and the value of the properties you want
   *     to filter on.
   * @return true if the properties match the criteria or if whitelist filtering is not applicable
   *     with the properties.
   */
  boolean whitelistFilter(Map<String, String> propertiesToFilterOn);
}

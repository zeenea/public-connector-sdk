package zeenea.connector.common.filter;

import java.util.List;
import java.util.Map;

/**
 * Interface representing a configuration for filters. This interface provides a method to retrieve
 * a list of filters.
 */
public interface FilterConfiguration {

  /**
   * Check if the given properties are accepted by the filter configuration. If a rule is checked on
   * a missing property, it matches if filterAction == ACCEPT.
   *
   * <p>Use it for middle-filtering, to limit the number of queries/calls executed during
   * inventory/synchronization step.
   *
   * @param filteredProperties the properties to check against the filter configuration. The key is
   *     the name of the property, and the value is the property value presented as a string.
   * @return true if the properties evaluate to ACCEPT with the universal filter rules
   */
  boolean accepts(Map<String, String> filteredProperties);

  /**
   * Get the list of filters defined in this configuration.
   *
   * <p>It should only be useful for pre-filtering, to create WHERE conditions on a SQL query for
   * example.
   *
   * @return a list of filters
   */
  List<Filter> getFilters();
}

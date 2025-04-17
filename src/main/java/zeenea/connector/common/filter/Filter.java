package zeenea.connector.common.filter;

import java.util.List;

/** Interface representing a filter that can be applied to a collection of items. */
public interface Filter {
  /**
   * Get the unique identifier of the filter.
   *
   * @return the unique identifier of the filter
   */
  String getId();

  /**
   * Get the action to perform on the items that match the filter.
   *
   * @return the action to perform
   */
  FilterAction getAction();

  /**
   * Get the list of rules that define the filter criteria.
   *
   * @return the list of rules
   */
  List<Rule> getRules();
}

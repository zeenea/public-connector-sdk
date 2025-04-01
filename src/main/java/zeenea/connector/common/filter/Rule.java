package zeenea.connector.common.filter;

/**
 * Interface representing a rule that can be applied to filter items. A rule consists of a key,
 * type, value, raw value, and a method to check if a value matches the rule.
 */
public interface Rule {
  /**
   * Get the item property key that this rule applies to.
   *
   * <p>Example: for a rule `workspace_name: "glob:some_name"`, the key is `workspace_name`.
   *
   * @return the key of the item property
   */
  String getKey();

  /**
   * Get the matching type of the rule. It can either be glob or regex.
   *
   * <p>Example: for a rule `workspace_name: "glob:some_name"`, the type is `glob`.
   *
   * @return the type of the rule
   */
  RuleType getType();

  /**
   * Get the value of the rule. This is the pattern that will be used to match against the item
   * property.
   *
   * <p>Example: for a rule `workspace_name: "glob:some_name*"`, the value is `some_name*`.
   *
   * @return the value of the rule
   */
  String getValue();

  /**
   * Get the raw value of the rule. This is the original string representation of the rule.
   *
   * <p>Example: for a rule `workspace_name: "glob:some_name"`, the raw value is `glob:some_name`.
   *
   * @return the raw value of the rule
   */
  String getRawValue();

  /**
   * Check if the given value matches the rule.
   *
   * @param value the value to check
   * @return true if the value matches the rule, false otherwise
   */
  boolean matches(String value);
}

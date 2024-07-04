package zeenea.connector.exception;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * Utility class for exception handling and validation. This class provides methods to check for
 * null or empty values and throw appropriate exceptions.
 */
public final class ExceptionUtils {

  /** Private constructor to prevent instantiation. */
  private ExceptionUtils() {}

  /**
   * Ensures that the specified collection and its elements are not null.
   *
   * @param attributeName the name of the attribute being checked
   * @param attributeValues the collection of attribute values to check
   * @throws NullPointerException if the collection or any of its elements are null
   */
  public static void requireNonNull(String attributeName, Collection<?> attributeValues) {
    Objects.requireNonNull(attributeValues, attributeName);
    for (Object attributeValue : attributeValues) {
      Objects.requireNonNull(attributeValue, attributeName);
    }
  }

  /**
   * Ensures that the specified map and its values are not null.
   *
   * @param attributeName the name of the attribute being checked
   * @param attributeValues the map of attribute values to check
   * @throws NullPointerException if the map or any of its values are null
   */
  public static void requireNonNull(String attributeName, Map<?, ?> attributeValues) {
    Objects.requireNonNull(attributeValues, attributeName);
    requireNonNull(attributeName, attributeValues.values());
  }

  /**
   * Ensures that the specified string is not null or empty.
   *
   * @param attributeName the name of the attribute being checked
   * @param attributeValue the string value to check
   * @throws NullPointerException if the string is null
   * @throws IllegalArgumentException if the string is empty
   */
  public static void requireNonNullOrEmpty(String attributeName, String attributeValue) {
    Objects.requireNonNull(attributeValue, attributeName);
    if (attributeValue.isEmpty())
      throw new IllegalArgumentException("Attribute \"" + attributeName + "\" cannot be empty");
  }

  /**
   * Ensures that the specified collection is not null or empty.
   *
   * @param attributeName the name of the attribute being checked
   * @param attributeValues the collection of attribute values to check
   * @throws NullPointerException if the collection or any of its elements are null
   * @throws IllegalArgumentException if the collection is empty
   */
  public static void requireNonNullOrEmpty(String attributeName, Collection<?> attributeValues) {
    requireNonNull(attributeName, attributeValues);
    if (attributeValues.isEmpty())
      throw new IllegalArgumentException("Attribute \"" + attributeName + "\" cannot be empty");
  }
}

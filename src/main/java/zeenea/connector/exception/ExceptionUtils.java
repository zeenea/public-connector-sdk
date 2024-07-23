package zeenea.connector.exception;

public final class ExceptionUtils {

  private ExceptionUtils() {}

  public static void throwIfNull(String attributeName, Object attributeValue) {
    if (attributeValue == null)
      throw new NullPointerException("Attribute \"" + attributeName + "\" cannot be null");
  }

  public static void throwIfInvalidLength(
      String attributeName, String attributeValue, int maxLength) {
    if (attributeValue != null && attributeValue.length() > maxLength)
      throw new IllegalArgumentException(
          "Attribute \""
              + attributeName
              + "\" cannot be more than "
              + maxLength
              + " characters long");
  }
}

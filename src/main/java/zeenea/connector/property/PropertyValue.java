package zeenea.connector.property;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.util.List;

/** Represents a property value. This is a marker interface for property values. */
public interface PropertyValue {

  /**
   * Creates a new StringPropertyValue with the specified string value.
   *
   * @param value the string value
   * @return a new StringPropertyValue
   */
  static StringPropertyValue string(String value) {
    return new StringPropertyValue(value);
  }

  /**
   * Creates a new NumberPropertyValue with the specified long value.
   *
   * @param value the long value
   * @return a new NumberPropertyValue
   */
  static NumberPropertyValue number(long value) {
    return new NumberPropertyValue(BigDecimal.valueOf(value));
  }

  /**
   * Creates a new NumberPropertyValue with the specified double value.
   *
   * @param value the double value
   * @return a new NumberPropertyValue
   */
  static NumberPropertyValue number(double value) {
    return new NumberPropertyValue(BigDecimal.valueOf(value));
  }

  /**
   * Creates a new NumberPropertyValue with the specified BigDecimal value.
   *
   * @param value the BigDecimal value
   * @return a new NumberPropertyValue
   */
  static NumberPropertyValue number(BigDecimal value) {
    return new NumberPropertyValue(value);
  }

  /**
   * Creates a new UrlPropertyValue with the specified URI value.
   *
   * @param value the URI value
   * @return a new UrlPropertyValue
   */
  static UrlPropertyValue url(URI value) {
    return new UrlPropertyValue(value);
  }

  /**
   * Creates a new UrlPropertyValue with the specified URI value and label.
   *
   * @param value the URI value
   * @param label the label for the URI
   * @return a new UrlPropertyValue
   */
  static UrlPropertyValue url(URI value, String label) {
    return new UrlPropertyValue(value, label);
  }

  /**
   * Creates a new InstantPropertyValue with the specified Instant value.
   *
   * @param value the Instant value
   * @return a new InstantPropertyValue
   */
  static InstantPropertyValue instant(Instant value) {
    return new InstantPropertyValue(value);
  }

  /**
   * Creates a new TagPropertyValue with the specified list of tags.
   *
   * @param value the list of tags
   * @return a new TagPropertyValue
   */
  static TagPropertyValue tag(List<String> value) {
    return new TagPropertyValue(value);
  }
}

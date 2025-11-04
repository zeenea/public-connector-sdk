package zeenea.connector.property;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PropertiesBuilder {
  private final Map<String, PropertyValue> properties = new HashMap<>();

  public static PropertiesBuilder create() {
    return new PropertiesBuilder();
  }

  /**
   * Adds a string property to the item.
   *
   * @param propertyDefinition the definition of the string property
   * @param value the value of the string property
   * @return the builder instance
   */
  public PropertiesBuilder put(
      @NotNull StringPropertyDefinition propertyDefinition, @Nullable String value) {
    if (value != null) properties.put(propertyDefinition.getName(), new StringPropertyValue(value));
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  /**
   * Adds a string property to the item.
   *
   * @param propertyDefinition the definition of the string property
   * @param value the value of the string property
   * @return the builder instance
   */
  public PropertiesBuilder put(
      @NotNull StringPropertyDefinition propertyDefinition, @Nullable Enum<?> value) {
    if (value != null)
      properties.put(propertyDefinition.getName(), new StringPropertyValue(value.toString()));
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  /**
   * Adds a string property to the item.
   *
   * @param propertyDefinition the definition of the string property
   * @param value the value of the string property
   * @return the builder instance
   */
  public PropertiesBuilder put(
      @NotNull StringPropertyDefinition propertyDefinition, @Nullable Boolean value) {
    if (value != null)
      properties.put(propertyDefinition.getName(), new StringPropertyValue(value.toString()));
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  /**
   * Adds a string property to the item.
   *
   * @param propertyDefinition the definition of the string property
   * @param value the value of the string property
   * @return the builder instance
   */
  public PropertiesBuilder put(
      @NotNull StringPropertyDefinition propertyDefinition, @Nullable StringPropertyValue value) {
    if (value != null) properties.put(propertyDefinition.getName(), value);
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  /**
   * Adds a long text property to the item.
   *
   * @param propertyDefinition the definition of the long text property
   * @param value the value of the long text property
   * @return the builder instance
   */
  public PropertiesBuilder put(
      @NotNull LongTextPropertyDefinition propertyDefinition, @Nullable String value) {
    if (value != null)
      properties.put(propertyDefinition.getName(), new LongTextPropertyValue(value));
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  /**
   * Adds a number property to the item.
   *
   * @param propertyDefinition the definition of the number property
   * @param value the value of the number property
   * @return the builder instance
   */
  public PropertiesBuilder put(
      @NotNull NumberPropertyDefinition propertyDefinition, @Nullable Integer value) {
    if (value != null)
      properties.put(
          propertyDefinition.getName(), new NumberPropertyValue(BigDecimal.valueOf(value)));
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  /**
   * Adds a number property to the item.
   *
   * @param propertyDefinition the definition of the number property
   * @param value the value of the number property
   * @return the builder instance
   */
  public PropertiesBuilder put(
      @NotNull NumberPropertyDefinition propertyDefinition, @Nullable Long value) {
    if (value != null)
      properties.put(
          propertyDefinition.getName(), new NumberPropertyValue(BigDecimal.valueOf(value)));
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  /**
   * Adds a number property to the item.
   *
   * @param propertyDefinition the definition of the number property
   * @param value the value of the number property
   * @return the builder instance
   */
  public PropertiesBuilder put(
      @NotNull NumberPropertyDefinition propertyDefinition, @Nullable Double value) {
    if (value != null)
      properties.put(
          propertyDefinition.getName(), new NumberPropertyValue(BigDecimal.valueOf(value)));
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  /**
   * Adds a number property to the item.
   *
   * @param propertyDefinition the definition of the number property
   * @param value the value of the number property
   * @return the builder instance
   */
  public PropertiesBuilder put(
      @NotNull NumberPropertyDefinition propertyDefinition, @Nullable BigDecimal value) {
    if (value != null) properties.put(propertyDefinition.getName(), new NumberPropertyValue(value));
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  /**
   * Adds a number property to the item.
   *
   * @param propertyDefinition the definition of the number property
   * @param value the value of the number property
   * @return the builder instance
   */
  public PropertiesBuilder put(
      @NotNull NumberPropertyDefinition propertyDefinition, @Nullable NumberPropertyValue value) {
    if (value != null) properties.put(propertyDefinition.getName(), value);
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  /**
   * Adds a URL property to the item.
   *
   * @param propertyDefinition the definition of the URL property
   * @param value the value of the URL property
   * @return the builder instance
   */
  public PropertiesBuilder put(
      @NotNull UrlPropertyDefinition propertyDefinition, @Nullable URI value) {
    if (value != null) properties.put(propertyDefinition.getName(), new UrlPropertyValue(value));
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  /**
   * Adds a URL property to the item.
   *
   * @param propertyDefinition the definition of the URL property
   * @param uri the URI of the URL property
   * @param label the label of the URL property
   * @return the builder instance
   */
  public PropertiesBuilder put(
      @NotNull UrlPropertyDefinition propertyDefinition,
      @Nullable URI uri,
      @Nullable String label) {
    if (uri != null) properties.put(propertyDefinition.getName(), new UrlPropertyValue(uri, label));
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  /**
   * Adds a URL property to the item.
   *
   * @param propertyDefinition the definition of the URL property
   * @param value the value of the URL property
   * @return the builder instance
   */
  public PropertiesBuilder put(
      @NotNull UrlPropertyDefinition propertyDefinition, @Nullable UrlPropertyValue value) {
    if (value != null) properties.put(propertyDefinition.getName(), value);
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  /**
   * Adds an instant property to the item.
   *
   * @param propertyDefinition the definition of the instant property
   * @param value the value of the instant property
   * @return the builder instance
   */
  public PropertiesBuilder put(
      @NotNull InstantPropertyDefinition propertyDefinition, @Nullable Instant value) {
    if (value != null)
      properties.put(propertyDefinition.getName(), new InstantPropertyValue(value));
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  /**
   * Adds an instant property to the item.
   *
   * @param propertyDefinition the definition of the instant property
   * @param value the value of the instant property
   * @return the builder instance
   */
  public PropertiesBuilder put(
      @NotNull InstantPropertyDefinition propertyDefinition, @Nullable InstantPropertyValue value) {
    if (value != null) properties.put(propertyDefinition.getName(), value);
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  /**
   * Adds a tag property to the item.
   *
   * @param propertyDefinition the definition of the tag property
   * @param value the value of the tag property
   * @return the builder instance
   */
  public PropertiesBuilder put(
      @NotNull TagPropertyDefinition propertyDefinition, @Nullable TagPropertyValue value) {
    if (value != null) properties.put(propertyDefinition.getName(), value);
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  /**
   * Adds a tag property to the item.
   *
   * @param propertyDefinition the definition of the tag property
   * @param value the value of the tag property
   * @return the builder instance
   */
  public PropertiesBuilder put(
      @NotNull TagPropertyDefinition propertyDefinition, @Nullable List<String> value) {
    if (value != null && !value.isEmpty())
      properties.put(propertyDefinition.getName(), new TagPropertyValue(value));
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  /**
   * Adds a tag property to the item.
   *
   * @param propertyDefinition the definition of the tag property
   * @param value the value of the tag property
   * @return the builder instance
   */
  public PropertiesBuilder put(@NotNull TagPropertyDefinition propertyDefinition, String... value) {
    if (value.length > 0)
      properties.put(propertyDefinition.getName(), new TagPropertyValue(List.of(value)));
    else properties.remove(propertyDefinition.getName());
    return this;
  }

  public PropertiesBuilder put(@NotNull CommonProperty commonProperty) {
    properties.put(commonProperty.getPropertyDefinition().getName(), commonProperty.getValue());
    return this;
  }

  public Map<String, PropertyValue> build() {
    return Map.copyOf(properties);
  }
}

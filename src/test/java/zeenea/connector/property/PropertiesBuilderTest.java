package zeenea.connector.property;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PropertiesBuilderTest {

  @Test
  public void shouldExcludeNullValueInsideStringPropertyValue() {
    // Given
    StringPropertyDefinition propertyDefinition = new StringPropertyDefinition("Test Property");
    StringPropertyValue propertyValue = new StringPropertyValue(null);

    // When
    Map<String, PropertyValue> builder =
        PropertiesBuilder.create().put(propertyDefinition, propertyValue).build();

    // Then
    Assertions.assertThat(builder).hasSize(0);
  }

  @Test
  public void shouldExcludeNullValueInsideNumberPropertyValue() {
    // Given
    NumberPropertyDefinition propertyDefinition = new NumberPropertyDefinition("Test Property");
    NumberPropertyValue propertyValue = new NumberPropertyValue(null);

    // When
    Map<String, PropertyValue> builder =
        PropertiesBuilder.create().put(propertyDefinition, propertyValue).build();

    // Then
    Assertions.assertThat(builder).hasSize(0);
  }

  @Test
  public void shouldExcludeNullValueInsideUrlPropertyValue() {
    // Given
    UrlPropertyDefinition propertyDefinition = new UrlPropertyDefinition("Test Property");
    UrlPropertyValue propertyValue = new UrlPropertyValue(null);

    // When
    Map<String, PropertyValue> builder =
        PropertiesBuilder.create().put(propertyDefinition, propertyValue).build();

    // Then
    Assertions.assertThat(builder).hasSize(0);
  }

  @Test
  public void shouldExcludeNullValueInsideInstantPropertyValue() {
    // Given
    InstantPropertyDefinition propertyDefinition = new InstantPropertyDefinition("Test Property");
    InstantPropertyValue propertyValue = new InstantPropertyValue(null);

    // When
    Map<String, PropertyValue> builder =
        PropertiesBuilder.create().put(propertyDefinition, propertyValue).build();

    // Then
    Assertions.assertThat(builder).hasSize(0);
  }

  @Test
  public void shouldExcludeNullValueInsideTagPropertyValue() {
    // Given
    TagPropertyDefinition propertyDefinition = new TagPropertyDefinition("Test Property");
    TagPropertyValue propertyValue = new TagPropertyValue(null);

    // When
    Map<String, PropertyValue> builder =
        PropertiesBuilder.create().put(propertyDefinition, propertyValue).build();

    // Then
    Assertions.assertThat(builder).hasSize(0);
  }

  @Test
  public void shouldExcludeEmptyValueInsideTagPropertyValue() {
    // Given
    TagPropertyDefinition propertyDefinition = new TagPropertyDefinition("Test Property");
    TagPropertyValue propertyValue = new TagPropertyValue(List.of());

    // When
    Map<String, PropertyValue> builder =
        PropertiesBuilder.create().put(propertyDefinition, propertyValue).build();

    // Then
    Assertions.assertThat(builder).hasSize(0);
  }
}

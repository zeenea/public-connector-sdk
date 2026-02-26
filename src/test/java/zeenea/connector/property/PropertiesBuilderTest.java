package zeenea.connector.property;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PropertiesBuilderTest {

  @Test
  public void shouldExcludeNullValueInsideStringPropertyValue() {
    // Given
    StringPropertyDefinition propertyDefinition = new StringPropertyDefinition("Test Property");

    // When
    Map<String, PropertyValue> builder =
        PropertiesBuilder.create().put(propertyDefinition, (String) null).build();

    // Then
    Assertions.assertThat(builder).hasSize(0);
  }

  @Test
  public void shouldExcludeNullValueInsideNumberPropertyValue() {
    // Given
    NumberPropertyDefinition propertyDefinition = new NumberPropertyDefinition("Test Property");

    // When
    Map<String, PropertyValue> builder =
        PropertiesBuilder.create().put(propertyDefinition, (BigDecimal) null).build();

    // Then
    Assertions.assertThat(builder).hasSize(0);
  }

  @Test
  public void shouldExcludeNullValueInsideUrlPropertyValue() {
    // Given
    UrlPropertyDefinition propertyDefinition = new UrlPropertyDefinition("Test Property");

    // When
    Map<String, PropertyValue> builder =
        PropertiesBuilder.create().put(propertyDefinition, (URI) null).build();

    // Then
    Assertions.assertThat(builder).hasSize(0);
  }

  @Test
  public void shouldExcludeNullValueInsideInstantPropertyValue() {
    // Given
    InstantPropertyDefinition propertyDefinition = new InstantPropertyDefinition("Test Property");

    // When
    Map<String, PropertyValue> builder =
        PropertiesBuilder.create().put(propertyDefinition, (Instant) null).build();

    // Then
    Assertions.assertThat(builder).hasSize(0);
  }

  @Test
  public void shouldExcludeNullValueInsideTagPropertyValue() {
    // Given
    TagPropertyDefinition propertyDefinition = new TagPropertyDefinition("Test Property");

    // When
    Map<String, PropertyValue> builder =
        PropertiesBuilder.create().put(propertyDefinition, (List<String>) null).build();

    // Then
    Assertions.assertThat(builder).hasSize(0);
  }

  @Test
  public void shouldExcludeEmptyValueInsideTagPropertyValue() {
    // Given
    TagPropertyDefinition propertyDefinition = new TagPropertyDefinition("Test Property");

    // When
    Map<String, PropertyValue> builder =
        PropertiesBuilder.create().put(propertyDefinition, List.of()).build();

    // Then
    Assertions.assertThat(builder).hasSize(0);
  }
}

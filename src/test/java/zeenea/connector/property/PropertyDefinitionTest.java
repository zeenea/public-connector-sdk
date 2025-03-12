package zeenea.connector.property;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PropertyDefinitionTest {

  @Test
  public void testPropertyWithName() {
    StringPropertyDefinition property = new StringPropertyDefinition("Some Property");
    Assertions.assertThat(property.getName()).isEqualTo("Some Property");
    Assertions.assertThat(property.getType()).isEqualTo(PropertyType.STRING);
  }

  @Test
  public void testThrowErrorWhenNameIsNull() {
    Assertions.assertThatThrownBy(
            () -> {
              new StringPropertyDefinition(null);
            })
        .isInstanceOf(NullPointerException.class);
  }
}

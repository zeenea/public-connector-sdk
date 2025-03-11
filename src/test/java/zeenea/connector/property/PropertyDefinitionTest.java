package zeenea.connector.property;

import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PropertyDefinitionTest {

  @Test
  public void testUseCodeAsNameWhenLabelIsNull() {
    StringPropertyDefinition property = new StringPropertyDefinition("some-code", null);
    Assertions.assertThat(property.getName()).isEqualTo("some-code");
    Assertions.assertThat(property.getCode()).isEqualTo("some-code");
    Assertions.assertThat(property.getType()).isEqualTo(PropertyType.STRING);
    Assertions.assertThat(property.getLabel()).isEqualTo(Optional.of("some-code"));
  }

  @Test
  public void testUseLabelAsNameWhenBothCodeAndLabelAreDefined() {
    StringPropertyDefinition property = new StringPropertyDefinition("some-code", "some-label");
    Assertions.assertThat(property.getName()).isEqualTo("some-label");
    Assertions.assertThat(property.getCode()).isEqualTo("some-label");
    Assertions.assertThat(property.getType()).isEqualTo(PropertyType.STRING);
    Assertions.assertThat(property.getLabel()).isEqualTo(Optional.of("some-label"));
  }

  @Test
  public void testThrowErrorWhenBotCodeAndLabelAreNull() {
    Assertions.assertThatThrownBy(
            () -> {
              new StringPropertyDefinition(null, null);
            })
        .isInstanceOf(NullPointerException.class);
  }
}

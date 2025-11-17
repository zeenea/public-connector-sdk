package zeenea.connector.property;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import zeenea.common.properties.CommonProperties;
import zeenea.common.properties.CommonProperty;

class CommonPropertyDefinitionsTest {

  @Test
  void allCommonPropertiesFromLibraryShouldBeAvailable() {
    Constructor<CommonPropertyDefinitions> constructor =
        (Constructor<CommonPropertyDefinitions>)
            CommonPropertyDefinitions.class.getDeclaredConstructors()[0];
    constructor.setAccessible(true);
    List<UUID> commonPropertiesDefinitionUUIDs =
        Arrays.stream(CommonPropertyDefinitions.class.getDeclaredFields())
            .filter(field -> PropertyDefinition.class.isAssignableFrom(field.getType()))
            .map(
                field -> {
                  try {
                    return field.get(constructor.newInstance());
                  } catch (IllegalAccessException
                      | InvocationTargetException
                      | InstantiationException e) {
                    throw new RuntimeException(e);
                  }
                })
            .map(fielValue -> (PropertyDefinition) fielValue)
            .map(PropertyDefinition::getUuid)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());

    UUID[] libraryUuuids =
        CommonProperties.commonProperties.stream().map(CommonProperty::uuid).toArray(UUID[]::new);

    assertThat(commonPropertiesDefinitionUUIDs).containsExactlyInAnyOrder(libraryUuuids);
  }
}

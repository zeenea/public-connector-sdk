package zeenea.connector.dataset;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zeenea.connector.property.PropertyValue;

class FieldTest {

  @Test
  @DisplayName("Field builder should create field")
  void shouldCreateFieldWithBuilder() {
    Map<String, PropertyValue> properties =
        Map.of("key1", PropertyValue.string("value1"), "key2", PropertyValue.string("value2"));
    List<String> keys = List.of("key1", "key2");
    Field field =
        Field.builder()
            .name("FieldName")
            .dataType(DataType.String)
            .nativeType("String")
            .nativeIndex(1)
            .keys(keys)
            .nullable(true)
            .multivalued(false)
            .description("Field description")
            .properties(properties)
            .build();
    assertNotNull(field);
    assertEquals("FieldName", field.getName());
    assertEquals(DataType.String, field.getDataType());
    assertEquals("String", field.getNativeType());
    assertEquals(1, field.getNativeIndex());
    assertEquals(keys, field.getKeys());
    assertTrue(field.isNullable());
    assertFalse(field.isMultivalued());
    assertEquals(Optional.of("Field description"), field.getDescription());
    assertEquals(properties, field.getProperties());
  }

  @Test
  @DisplayName("Field should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    Map<String, PropertyValue> properties =
        Map.of("key1", PropertyValue.string("value1"), "key2", PropertyValue.string("value2"));
    List<String> keys = List.of("key1", "key2");
    Field field1 =
        Field.builder()
            .name("FieldName")
            .dataType(DataType.String)
            .nativeType("String")
            .nativeIndex(1)
            .keys(keys)
            .nullable(true)
            .multivalued(false)
            .description("Field description")
            .properties(properties)
            .build();
    Field field2 =
        Field.builder()
            .name("FieldName")
            .dataType(DataType.String)
            .nativeType("String")
            .nativeIndex(1)
            .keys(keys)
            .nullable(true)
            .multivalued(false)
            .description("Field description")
            .properties(properties)
            .build();
    assertEquals(field1, field2);
    assertEquals(field1.hashCode(), field2.hashCode());
  }

  @Test
  @DisplayName("Field should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    Map<String, PropertyValue> properties1 =
        Map.of("key1", PropertyValue.string("value1"), "key2", PropertyValue.string("value2"));
    Map<String, PropertyValue> properties2 =
        Map.of("key3", PropertyValue.string("value3"), "key4", PropertyValue.string("value4"));
    List<String> keys1 = List.of("key1", "key2");
    List<String> keys2 = List.of("key3", "key4");
    Field field1 =
        Field.builder()
            .name("FieldName1")
            .dataType(DataType.String)
            .nativeType("String")
            .nativeIndex(1)
            .keys(keys1)
            .nullable(true)
            .multivalued(false)
            .description("Field description 1")
            .properties(properties1)
            .build();
    Field field2 =
        Field.builder()
            .name("FieldName2")
            .dataType(DataType.Integer)
            .nativeType("Integer")
            .nativeIndex(2)
            .keys(keys2)
            .nullable(false)
            .multivalued(true)
            .description("Field description 2")
            .properties(properties2)
            .build();
    assertNotEquals(field1, field2);
  }

  @Test
  @DisplayName("Field builder should fail with null name")
  void builderShouldFailWithNullName() {
    Map<String, PropertyValue> properties =
        Map.of("key1", PropertyValue.string("value1"), "key2", PropertyValue.string("value2"));
    List<String> keys = List.of("key1", "key2");
    assertThrows(
        NullPointerException.class,
        () ->
            Field.builder()
                .name(null)
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .keys(keys)
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .properties(properties)
                .build());
  }

  @Test
  @DisplayName("Field builder should fail with null keys")
  void builderShouldFailWithNullKeys() {
    Map<String, PropertyValue> properties =
        Map.of("key1", PropertyValue.string("value1"), "key2", PropertyValue.string("value2"));
    assertThrows(
        NullPointerException.class,
        () ->
            Field.builder()
                .name("FieldName")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .keys((List<String>) null)
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .properties(properties)
                .build());
  }

  @Test
  @DisplayName("Field builder should fail with null properties")
  void builderShouldFailWithNullProperties() {
    List<String> keys = List.of("key1", "key2");
    assertThrows(
        NullPointerException.class,
        () ->
            Field.builder()
                .name("FieldName")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .keys(keys)
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .properties(null)
                .build());
  }
}

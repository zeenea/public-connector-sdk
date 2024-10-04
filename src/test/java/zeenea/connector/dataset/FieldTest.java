package zeenea.connector.dataset;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.IdentificationProperty;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.property.PropertyValue;

class FieldTest {

  @Test
  @DisplayName("Field builder should create field")
  void shouldCreateFieldWithBuilder() {
    Map<String, PropertyValue> properties =
        Map.of("key1", PropertyValue.string("value1"), "key2", PropertyValue.string("value2"));
    ItemIdentifier identifier =
        ItemIdentifier.of(
            IdentificationProperty.of("key1", "value1"),
            IdentificationProperty.of("key2", "value2"));
    Field field =
        Field.builder()
            .identifier(identifier)
            .name("FieldName")
            .dataType(DataType.String)
            .nativeType("String")
            .nativeIndex(1)
            .nullable(true)
            .multivalued(false)
            .description("Field description")
            .properties(properties)
            .build();
    assertNotNull(field);
    assertEquals(identifier, field.getId());
    assertEquals("FieldName", field.getName());
    assertEquals(DataType.String, field.getDataType());
    assertEquals("String", field.getNativeType());
    assertEquals(1, field.getNativeIndex());
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
    ItemIdentifier identifier =
        ItemIdentifier.of(
            IdentificationProperty.of("key1", "value1"),
            IdentificationProperty.of("key2", "value2"));
    Field field1 =
        Field.builder()
            .identifier(identifier)
            .name("FieldName")
            .dataType(DataType.String)
            .nativeType("String")
            .nativeIndex(1)
            .nullable(true)
            .multivalued(false)
            .description("Field description")
            .properties(properties)
            .build();
    Field field2 =
        Field.builder()
            .identifier(identifier)
            .name("FieldName")
            .dataType(DataType.String)
            .nativeType("String")
            .nativeIndex(1)
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
    ItemIdentifier identifier1 =
        ItemIdentifier.of(
            IdentificationProperty.of("key1", "value1"),
            IdentificationProperty.of("key2", "value2"));
    ItemIdentifier identifier2 =
        ItemIdentifier.of(
            IdentificationProperty.of("key2", "value2"),
            IdentificationProperty.of("key3", "value3"));
    Field field1 =
        Field.builder()
            .identifier(identifier1)
            .name("FieldName1")
            .dataType(DataType.String)
            .nativeType("String")
            .nativeIndex(1)
            .nullable(true)
            .multivalued(false)
            .description("Field description 1")
            .properties(properties1)
            .build();
    Field field2 =
        Field.builder()
            .identifier(identifier2)
            .name("FieldName2")
            .dataType(DataType.Integer)
            .nativeType("Integer")
            .nativeIndex(2)
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
    ItemIdentifier identifier =
        ItemIdentifier.of(
            IdentificationProperty.of("key1", "value1"),
            IdentificationProperty.of("key2", "value2"));
    assertThrows(
        NullPointerException.class,
        () ->
            Field.builder()
                .identifier(identifier)
                .name(null)
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .properties(properties)
                .build());
  }

  @Test
  @DisplayName("Field builder should fail with null identifier")
  void builderShouldFailWithNullKeys() {
    Map<String, PropertyValue> properties =
        Map.of("key1", PropertyValue.string("value1"), "key2", PropertyValue.string("value2"));
    assertThrows(
        NullPointerException.class,
        () ->
            Field.builder()
                .identifier(null)
                .name("FieldName")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .properties(properties)
                .build());
  }

  @Test
  @DisplayName("Field builder should fail with null properties")
  void builderShouldFailWithNullProperties() {
    ItemIdentifier identifier =
        ItemIdentifier.of(
            IdentificationProperty.of("key1", "value1"),
            IdentificationProperty.of("key2", "value2"));
    assertThrows(
        NullPointerException.class,
        () ->
            Field.builder()
                .identifier(identifier)
                .name("FieldName")
                .dataType(DataType.String)
                .nativeType("String")
                .nativeIndex(1)
                .nullable(true)
                .multivalued(false)
                .description("Field description")
                .properties(null)
                .build());
  }
}

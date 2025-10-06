package zeenea.connector.field;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.IdentificationProperty;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.dataset.DataType;
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
            .id(identifier)
            .name("FieldName")
            .dataType(DataType.String)
            .nativeType("String")
            .nativeIndex(1)
            .nullable(true)
            .multivalued(false)
            .description("Field description")
            .properties(properties)
            .build();
    assertThat(field).isNotNull();
    assertThat(field.getId()).isEqualTo(identifier);
    assertThat(field.getName()).isEqualTo("FieldName");
    assertThat(field.getDataType()).isEqualTo(DataType.String);
    assertThat(field.getNativeType()).isEqualTo("String");
    assertThat(field.getNativeIndex()).isEqualTo(1);
    assertThat(field.isNullable()).isTrue();
    assertThat(field.isMultivalued()).isFalse();
    assertThat(field.getDescription()).isPresent().get().isEqualTo("Field description");
    assertThat(field.getProperties()).isEqualTo(properties);
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
            .id(identifier)
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
            .id(identifier)
            .name("FieldName")
            .dataType(DataType.String)
            .nativeType("String")
            .nativeIndex(1)
            .nullable(true)
            .multivalued(false)
            .description("Field description")
            .properties(properties)
            .build();
    assertThat(field1).isEqualTo(field2);
    assertThat(field1.hashCode()).isEqualTo(field2.hashCode());
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
            .id(identifier1)
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
            .id(identifier2)
            .name("FieldName2")
            .dataType(DataType.Integer)
            .nativeType("Integer")
            .nativeIndex(2)
            .nullable(false)
            .multivalued(true)
            .description("Field description 2")
            .properties(properties2)
            .build();
    assertThat(field1).isNotEqualTo(field2);
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
    assertThatThrownBy(
            () ->
                Field.builder()
                    .id(identifier)
                    .name(null)
                    .dataType(DataType.String)
                    .nativeType("String")
                    .nativeIndex(1)
                    .nullable(true)
                    .multivalued(false)
                    .description("Field description")
                    .properties(properties)
                    .build())
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  @DisplayName("Field builder should fail with null identifier")
  void builderShouldFailWithNullKeys() {
    Map<String, PropertyValue> properties =
        Map.of("key1", PropertyValue.string("value1"), "key2", PropertyValue.string("value2"));
    assertThatThrownBy(
            () ->
                Field.builder()
                    .id(null)
                    .name("FieldName")
                    .dataType(DataType.String)
                    .nativeType("String")
                    .nativeIndex(1)
                    .nullable(true)
                    .multivalued(false)
                    .description("Field description")
                    .properties(properties)
                    .build())
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  @DisplayName("Field builder should fail with null properties")
  void builderShouldFailWithNullProperties() {
    ItemIdentifier identifier =
        ItemIdentifier.of(
            IdentificationProperty.of("key1", "value1"),
            IdentificationProperty.of("key2", "value2"));
    assertThatThrownBy(
            () ->
                Field.builder()
                    .id(identifier)
                    .name("FieldName")
                    .dataType(DataType.String)
                    .nativeType("String")
                    .nativeIndex(1)
                    .nullable(true)
                    .multivalued(false)
                    .description("Field description")
                    .properties(null)
                    .build())
        .isInstanceOf(NullPointerException.class);
  }
}

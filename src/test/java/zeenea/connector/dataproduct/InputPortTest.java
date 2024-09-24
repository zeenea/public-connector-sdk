package zeenea.connector.dataproduct;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import zeenea.connector.common.*;

class InputPortTest {

  @Test
  @DisplayName("InputPort builder should create input port")
  void shouldCreateInputPortWithBuilder() {
    ItemIdentifier input1 = ItemIdentifier.of(List.of(IdentificationProperty.of("name", "input1")));
    ItemIdentifier input2 = ItemIdentifier.of(List.of(IdentificationProperty.of("name", "input2")));
    ConnectionReferenceCode referenceCode = ConnectionReferenceCode.of("code");
    ItemIdentifier output1 =
        ItemIdentifier.of(List.of(IdentificationProperty.of("name", "output1")));
    ItemIdentifier output2 =
        ItemIdentifier.of(List.of(IdentificationProperty.of("name", "output2")));
    List<ItemReference> inputs =
        List.of(ItemReference.of(input1, referenceCode), ItemReference.of(input2, referenceCode));
    List<ItemIdentifier> outputs = List.of(output1, output2);
    InputPort inputPort =
        InputPort.builder()
            .name("InputPort1")
            .description("Description1")
            .inputs(inputs)
            .outputs(outputs)
            .build();
    assertNotNull(inputPort);
    assertEquals("InputPort1", inputPort.getName());
    assertEquals(Optional.of("Description1"), inputPort.getDescription());
    assertEquals(inputs, inputPort.getInputs());
    assertEquals(outputs, inputPort.getOutputs());
  }

  @Test
  @DisplayName("InputPort should be equal to another with same properties")
  void shouldBeEqualToAnotherWithSameProperties() {
    ItemIdentifier input1 = ItemIdentifier.of(List.of(IdentificationProperty.of("name", "input1")));
    ItemIdentifier input2 = ItemIdentifier.of(List.of(IdentificationProperty.of("name", "input2")));
    ConnectionReferenceCode referenceCode = ConnectionReferenceCode.of("code");
    ItemIdentifier output1 =
        ItemIdentifier.of(List.of(IdentificationProperty.of("name", "output1")));
    ItemIdentifier output2 =
        ItemIdentifier.of(List.of(IdentificationProperty.of("name", "output2")));
    List<ItemReference> inputs =
        List.of(ItemReference.of(input1, referenceCode), ItemReference.of(input2, referenceCode));
    List<ItemIdentifier> outputs = List.of(output1, output2);
    InputPort inputPort1 =
        InputPort.builder()
            .name("InputPort1")
            .description("Description1")
            .inputs(inputs)
            .outputs(outputs)
            .build();
    InputPort inputPort2 =
        InputPort.builder()
            .name("InputPort1")
            .description("Description1")
            .inputs(inputs)
            .outputs(outputs)
            .build();
    assertEquals(inputPort1, inputPort2);
    assertEquals(inputPort1.hashCode(), inputPort2.hashCode());
  }

  @Test
  @DisplayName("InputPort should not be equal to another with different properties")
  void shouldNotBeEqualToAnotherWithDifferentProperties() {
    ItemIdentifier input1 = ItemIdentifier.of(List.of(IdentificationProperty.of("name", "input1")));
    ItemIdentifier input2 = ItemIdentifier.of(List.of(IdentificationProperty.of("name", "input2")));
    ConnectionReferenceCode referenceCode = ConnectionReferenceCode.of("code");
    ItemIdentifier output1 =
        ItemIdentifier.of(List.of(IdentificationProperty.of("name", "output1")));
    ItemIdentifier output2 =
        ItemIdentifier.of(List.of(IdentificationProperty.of("name", "output2")));
    List<ItemReference> inputs =
        List.of(ItemReference.of(input1, referenceCode), ItemReference.of(input2, referenceCode));
    List<ItemIdentifier> outputs1 = List.of(output1);
    List<ItemIdentifier> outputs2 = List.of(output2);
    InputPort inputPort1 =
        InputPort.builder()
            .name("InputPort1")
            .description("Description1")
            .inputs(inputs)
            .outputs(outputs1)
            .build();
    InputPort inputPort2 =
        InputPort.builder()
            .name("InputPort2")
            .description("Description2")
            .inputs(inputs)
            .outputs(outputs2)
            .build();
    assertNotEquals(inputPort1, inputPort2);
  }

  @Test
  @DisplayName("InputPort builder should fail with null inputs")
  void builderShouldFailWithNullInputs() {
    ItemIdentifier output1 =
        ItemIdentifier.of(List.of(IdentificationProperty.of("name", "output1")));
    List<ItemIdentifier> outputs = List.of(output1);
    assertThrows(
        NullPointerException.class,
        () ->
            InputPort.builder()
                .name("InputPort1")
                .description("Description1")
                .inputs(List.of(null))
                .outputs(outputs)
                .build());
  }

  @Test
  @DisplayName("InputPort builder should fail with null outputs")
  void builderShouldFailWithNullOutputs() {
    ItemIdentifier input1 = ItemIdentifier.of(List.of(IdentificationProperty.of("name", "input1")));
    ConnectionReferenceCode referenceCode = ConnectionReferenceCode.of("code");
    List<ItemReference> inputs = List.of(ItemReference.of(input1, referenceCode));

    assertThrows(
        NullPointerException.class,
        () ->
            InputPort.builder()
                .name("InputPort1")
                .description("Description1")
                .inputs(inputs)
                .outputs(List.of(null))
                .build());
  }
}

package zeenea.connector.dataproduct;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.common.ItemReference;
import zeenea.connector.exception.ExceptionUtils;

/** Represents an input port in a data product. */
public final class InputPort {
  /** The name of the input port. */
  @NotNull private final String name;

  /** The description of the input port. */
  @Nullable private final String description;

  /** The list of input references for the input port. */
  @NotNull private final List<ItemReference> inputs;

  /** The list of output identifiers for the input port. */
  @NotNull private final List<ItemIdentifier> outputs;

  /**
   * Constructs an InputPort instance using the provided builder.
   *
   * @param builder the builder used to create the InputPort instance
   */
  private InputPort(Builder builder) {
    ExceptionUtils.requireNonNull("input", builder.inputs);
    ExceptionUtils.requireNonNull("output", builder.outputs);
    this.name = builder.name;
    this.description = builder.description;
    this.inputs = List.copyOf(builder.inputs);
    this.outputs = List.copyOf(builder.outputs);
  }

  /**
   * Gets the name of the input port.
   *
   * @return the name of the input port
   */
  public @NotNull String getName() {
    return name;
  }

  /**
   * Gets the description of the input port.
   *
   * @return an Optional containing the description of the input port if present, otherwise an empty
   *     Optional
   */
  public Optional<String> getDescription() {
    return Optional.ofNullable(description);
  }

  /**
   * Gets the list of input references for the input port.
   *
   * @return the list of input references
   */
  public @NotNull List<ItemReference> getInputs() {
    return inputs;
  }

  /**
   * Gets the list of output identifiers for the input port.
   *
   * @return the list of output identifiers
   */
  public @NotNull List<ItemIdentifier> getOutputs() {
    return outputs;
  }

  /**
   * Checks if this InputPort is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this InputPort is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    InputPort inputPort = (InputPort) o;
    return Objects.equals(name, inputPort.name)
        && Objects.equals(description, inputPort.description)
        && Objects.equals(inputs, inputPort.inputs)
        && Objects.equals(outputs, inputPort.outputs);
  }

  /**
   * Computes the hash code for this InputPort.
   *
   * @return the hash code of this InputPort
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, description, inputs, outputs);
  }

  /**
   * Returns a string representation of this InputPort.
   *
   * @return a string representation of this InputPort
   */
  @Override
  public String toString() {
    return "InputPort{"
        + "description='"
        + description
        + ", name='"
        + name
        + "', inputs="
        + inputs
        + ", outputs="
        + outputs
        + "}";
  }

  /**
   * Creates a new builder for the InputPort class.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder class for creating instances of InputPort. */
  public static class Builder {

    /** The name of the input port. */
    private String name;

    /** The description of the input port. */
    private String description = null;

    /** The list of input references for the input port. */
    private List<ItemReference> inputs;

    /** The list of output identifiers for the input port. */
    private List<ItemIdentifier> outputs;

    public Builder name(@NotNull String name) {
      this.name = name;
      return this;
    }

    /**
     * Sets the description of the input port.
     *
     * @param description the description of the input port
     * @return the builder instance
     */
    public Builder description(@Nullable String description) {
      this.description = description;
      return this;
    }

    /**
     * Set a collection of input references to the input port.
     *
     * @param inputs the collection of input references to add
     * @return the builder instance
     */
    public Builder inputs(@NotNull Collection<ItemReference> inputs) {
      this.inputs = List.copyOf(inputs);
      return this;
    }

    /**
     * Set a collection of output identifiers to the input port.
     *
     * @param outputs the collection of output identifiers to add
     * @return the builder instance
     */
    public Builder outputs(@NotNull Collection<ItemIdentifier> outputs) {
      this.outputs = List.copyOf(outputs);
      return this;
    }

    /**
     * Builds and returns an InputPort instance.
     *
     * @return the created InputPort instance
     */
    public InputPort build() {
      return new InputPort(this);
    }
  }
}

package zeenea.connector.dataproduct;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.common.ItemReference;
import zeenea.connector.exception.ExceptionUtils;

/** Represents an input port in a data product. */
public final class InputPort {

  /** The identifier of the input port. */
  @NotNull private final ItemIdentifier id;

  /** The name of the input port. */
  @NotNull private final String name;

  /** The description of the input port. */
  @Nullable private final String description;

  /** The list of input references for the input port. */
  @NotNull private final List<ItemReference> inputs;

  /** The list of output identifiers for the input port. */
  @NotNull private final List<ItemIdentifier> outputs;

  /** The glossaryRefs of the input port. */
  @NotNull private final List<String> glossaryRefs;

  /** The customItemRefs of the input port. */
  @NotNull private final List<String> customItemRefs;

  /**
   * Constructs an InputPort instance using the provided builder.
   *
   * @param builder the builder used to create the InputPort instance
   */
  private InputPort(Builder builder) {
    Objects.requireNonNull(builder.id, "id");
    ExceptionUtils.requireNonNull("input", builder.inputs);
    ExceptionUtils.requireNonNull("output", builder.outputs);
    this.id = builder.id;
    this.name = builder.name;
    this.description = builder.description;
    this.inputs = List.copyOf(builder.inputs);
    this.outputs = List.copyOf(builder.outputs);
    this.glossaryRefs = List.copyOf(builder.glossaryRefs);
    this.customItemRefs = List.copyOf(builder.customItemRefs);
  }

  /**
   * Gets the identifier of the input port.
   *
   * @return the identifier of the input port
   */
  public @NotNull ItemIdentifier getId() {
    return id;
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
   * Gets the glossary refs of the input port.
   *
   * @return a collection of the glossary refs of the input port
   */
  public Collection<String> getGlossaryRefs() {
    return glossaryRefs;
  }

  /**
   * Gets the custom item refs of the input port.
   *
   * @return a collection of the custom item refs of the input port
   */
  public Collection<String> getCustomItemRefs() {
    return customItemRefs;
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
    return Objects.equals(id, inputPort.id)
        && Objects.equals(name, inputPort.name)
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
    return Objects.hash(id, name, description, inputs, outputs);
  }

  /**
   * Returns a string representation of this InputPort.
   *
   * @return a string representation of this InputPort
   */
  @Override
  public String toString() {
    return "InputPort{"
        + "id='"
        + id
        + "', description='"
        + description
        + "', name='"
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

    private ItemIdentifier id;

    private String name;

    private String description = null;

    private List<ItemReference> inputs = Collections.emptyList();

    private List<ItemIdentifier> outputs = Collections.emptyList();

    /** The glossary refs of the input port. */
    private List<String> glossaryRefs = List.of();

    /** The custom item refs of the input port. */
    private List<String> customItemRefs = List.of();

    /**
     * Sets the identifier of the input port.
     *
     * @param id the identifier of the input port
     * @return the builder instance
     */
    public Builder id(@NotNull ItemIdentifier id) {
      this.id = id;
      return this;
    }

    /**
     * Sets the name of the input port.
     *
     * @param name the name of the input port
     * @return the builder instance
     */
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
     * Set a collection of input references to the input port.
     *
     * @param inputs the collection of input references to add
     * @return the builder instance
     */
    public Builder inputs(ItemReference... inputs) {
      this.inputs = List.of(inputs);
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
     * Set a collection of output identifiers to the input port.
     *
     * @param outputs the collection of output identifiers to add
     * @return the builder instance
     */
    public Builder outputs(ItemIdentifier... outputs) {
      this.outputs = List.of(outputs);
      return this;
    }

    /**
     * Set a list of glossary refs to the input port.
     *
     * @param glossaryRefs the list of glossary refs to add
     * @return the builder instance
     */
    public Builder glossaryRefs(@NotNull Collection<String> glossaryRefs) {
      this.glossaryRefs = List.copyOf(glossaryRefs);
      return this;
    }

    /**
     * Set a list of glossary refs to the input port.
     *
     * @param glossaryRefs the list of glossary refs to add
     * @return the builder instance
     */
    public Builder glossaryRefs(String... glossaryRefs) {
      this.glossaryRefs = List.of(glossaryRefs);
      return this;
    }

    /**
     * Set a list of custom item refs to the input port.
     *
     * @param customItemRefs the list of custom item refs to add
     * @return the builder instance
     */
    public Builder customItemRefs(@NotNull Collection<String> customItemRefs) {
      this.customItemRefs = List.copyOf(customItemRefs);
      return this;
    }

    /**
     * Set a list of custom item refs to the input port.
     *
     * @param customItemRefs the list of custom item refs to add
     * @return the builder instance
     */
    public Builder customItemRefs(String... customItemRefs) {
      this.customItemRefs = List.of(customItemRefs);
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

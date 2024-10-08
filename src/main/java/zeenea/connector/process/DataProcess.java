package zeenea.connector.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.Item;
import zeenea.connector.common.ItemReference;
import zeenea.connector.exception.ExceptionUtils;

/** Represents a data process which extends the Item class. */
public final class DataProcess extends Item {

  /** The source datasets references. */
  @NotNull private final List<ItemReference> sources;

  /** The target datasets references. */
  @NotNull private final List<ItemReference> targets;

  /** The list of field to field operations. */
  @NotNull private final List<Operation> operations;

  /**
   * Constructs a DataProcess instance using the provided builder.
   *
   * @param builder the builder to construct the DataProcess instance
   */
  private DataProcess(Builder builder) {
    super(builder);
    ExceptionUtils.requireNonNullOrEmpty("sources", builder.sources);
    ExceptionUtils.requireNonNullOrEmpty("targets", builder.targets);
    ExceptionUtils.requireNonNull("operation", builder.operations);
    this.sources = List.copyOf(builder.sources);
    this.targets = List.copyOf(builder.targets);
    this.operations = List.copyOf(builder.operations);
  }

  /**
   * Gets the list of source datasets references.
   *
   * @return the list of source datasets references
   */
  public @NotNull List<ItemReference> getSources() {
    return sources;
  }

  /**
   * Gets the list of target datasets references.
   *
   * @return the list of target datasets references
   */
  public @NotNull List<ItemReference> getTargets() {
    return targets;
  }

  /**
   * Gets the list of operations.
   *
   * @return the list of operations
   */
  public @NotNull List<Operation> getOperations() {
    return operations;
  }

  /**
   * Checks if this DataProcess is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this DataProcess is equal to the specified object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DataProcess dataProcess = (DataProcess) o;
    return Objects.equals(getId(), dataProcess.getId())
        && Objects.equals(getName(), dataProcess.getName())
        && Objects.equals(getDescription(), dataProcess.getDescription())
        && Objects.equals(getContacts(), dataProcess.getContacts())
        && Objects.equals(getProperties(), dataProcess.getProperties())
        && Objects.equals(sources, dataProcess.sources)
        && Objects.equals(targets, dataProcess.targets)
        && Objects.equals(operations, dataProcess.operations);
  }

  /**
   * Returns the hash code of this DataProcess.
   *
   * @return the hash code of this DataProcess
   */
  @Override
  public int hashCode() {
    return Objects.hash(
        getId(),
        getName(),
        getDescription(),
        getContacts(),
        getProperties(),
        sources,
        targets,
        operations);
  }

  /**
   * Returns the string representation of this DataProcess.
   *
   * @return the string representation of this DataProcess
   */
  @Override
  public String toString() {
    return "DataProcess{"
        + "id="
        + getId()
        + ", name='"
        + getName()
        + "', description="
        + getDescription()
        + ", contactRelations="
        + getContacts()
        + ", properties="
        + getProperties()
        + "sources="
        + sources
        + ", targets="
        + targets
        + ", operations="
        + operations
        + "}";
  }

  /**
   * Creates a new Builder instance for constructing a DataProcess.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder class for constructing DataProcess instances. */
  public static class Builder extends Item.Builder<DataProcess, Builder> {

    /** The list of source datasets references to be set in the DataProcess. */
    private List<ItemReference> sources = new ArrayList<>();

    /** The list of target datasets references to be set in the DataProcess. */
    private List<ItemReference> targets = new ArrayList<>();

    /** The list of operations to be set in the DataProcess. */
    private List<Operation> operations = new ArrayList<>();

    /**
     * Sets the list of source datasets references for the DataProcess.
     *
     * @param sources the list of source datasets references
     * @return this Builder instance
     */
    public Builder sources(@NotNull List<ItemReference> sources) {
      this.sources = List.copyOf(sources);
      return this;
    }

    /**
     * Sets the list of source datasets references for the DataProcess.
     *
     * @param sources the list of source datasets references
     * @return this Builder instance
     */
    public Builder sources(ItemReference... sources) {
      this.sources = List.of(sources);
      return this;
    }

    /**
     * Sets the list of target datasets references for the DataProcess.
     *
     * @param targets the list of target datasets references
     * @return this Builder instance
     */
    public Builder targets(@NotNull List<ItemReference> targets) {
      this.targets = List.copyOf(targets);
      return this;
    }

    /**
     * Sets the list of target datasets references for the DataProcess.
     *
     * @param targets the list of target datasets references
     * @return this Builder instance
     */
    public Builder targets(ItemReference... targets) {
      this.targets = List.of(targets);
      return this;
    }

    /**
     * Sets the list of operations for the DataProcess.
     *
     * @param operations the list of operations
     * @return this Builder instance
     */
    public Builder operations(@NotNull List<Operation> operations) {
      this.operations = List.copyOf(operations);
      return this;
    }

    /**
     * Sets the list of operations for the DataProcess.
     *
     * @param operations the list of operations
     * @return this Builder instance
     */
    public Builder operations(Operation... operations) {
      this.operations = List.of(operations);
      return this;
    }

    /**
     * Builds and returns a DataProcess instance.
     *
     * @return a new DataProcess instance
     */
    @Override
    protected DataProcess performBuild() {
      return new DataProcess(this);
    }
  }
}

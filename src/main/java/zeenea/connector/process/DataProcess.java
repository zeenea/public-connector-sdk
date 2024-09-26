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

  /** The source item references. */
  @NotNull private final List<ItemReference> sources;

  /** The target item references. */
  @NotNull private final List<ItemReference> targets;

  /**
   * Constructs a DataProcess instance using the provided builder.
   *
   * @param builder the builder to construct the DataProcess instance
   */
  private DataProcess(Builder builder) {
    super(builder);
    ExceptionUtils.requireNonNullOrEmpty("sources", builder.sources);
    ExceptionUtils.requireNonNullOrEmpty("targets", builder.targets);
    this.sources = List.copyOf(builder.sources);
    this.targets = List.copyOf(builder.targets);
  }

  /**
   * Gets the list of source item references.
   *
   * @return the list of source item references
   */
  public @NotNull List<ItemReference> getSources() {
    return sources;
  }

  /**
   * Gets the list of target item references.
   *
   * @return the list of target item references
   */
  public @NotNull List<ItemReference> getTargets() {
    return targets;
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
        && Objects.equals(getContactRelations(), dataProcess.getContactRelations())
        && Objects.equals(getProperties(), dataProcess.getProperties())
        && Objects.equals(sources, dataProcess.sources)
        && Objects.equals(targets, dataProcess.targets);
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
        getContactRelations(),
        getProperties(),
        sources,
        targets);
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
        + getContactRelations()
        + ", properties="
        + getProperties()
        + "sources="
        + sources
        + ", targets="
        + targets
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

    /** The list of source item references to be set in the DataProcess. */
    private List<ItemReference> sources = new ArrayList<>();
    ;

    /** The list of target item references to be set in the DataProcess. */
    private List<ItemReference> targets = new ArrayList<>();
    ;

    /**
     * Sets the list of source item references for the DataProcess.
     *
     * @param sources the list of source item references
     * @return this Builder instance
     */
    public Builder sources(@NotNull List<ItemReference> sources) {
      this.sources = List.copyOf(sources);
      return this;
    }

    /**
     * Sets the list of source item references for the DataProcess.
     *
     * @param sources the list of source item references
     * @return this Builder instance
     */
    public Builder sources(ItemReference... sources) {
      this.sources = List.of(sources);
      return this;
    }

    /**
     * Sets the list of target item references for the DataProcess.
     *
     * @param targets the list of target item references
     * @return this Builder instance
     */
    public Builder targets(@NotNull List<ItemReference> targets) {
      this.targets = List.copyOf(targets);
      return this;
    }

    /**
     * Sets the list of target item references for the DataProcess.
     *
     * @param targets the list of target item references
     * @return this Builder instance
     */
    public Builder targets(ItemReference... targets) {
      this.targets = List.of(targets);
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

package zeenea.connector.process;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.Item;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.common.ItemReference;
import zeenea.connector.exception.ExceptionUtils;

/** Represents a data process which extends the Item class. */
public final class DataProcess extends Item {

  /** The source item references. */
  @NotNull private final List<ItemReference> sourceItemReference;

  /** The target item references. */
  @NotNull private final List<ItemReference> targetItemReference;

  /**
   * Constructs a DataProcess instance using the provided builder.
   *
   * @param builder the builder to construct the DataProcess instance
   */
  private DataProcess(Builder builder) {
    super(builder);
    ExceptionUtils.requireNonNullOrEmpty("sourceItemReference", builder.sourceItemReference);
    ExceptionUtils.requireNonNullOrEmpty("targetItemReference", builder.targetItemReference);
    this.sourceItemReference = List.copyOf(builder.sourceItemReference);
    this.targetItemReference = List.copyOf(builder.targetItemReference);
  }

  /**
   * Gets the source item references.
   *
   * @return the source item references
   */
  public @NotNull List<ItemReference> getSourceItemReference() {
    return sourceItemReference;
  }

  /**
   * Gets the target item references.
   *
   * @return the target item references
   */
  public @NotNull List<ItemReference> getTargetItemReference() {
    return targetItemReference;
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
    DataProcess that = (DataProcess) o;
    return Objects.equals(sourceItemReference, that.sourceItemReference)
        && Objects.equals(targetItemReference, that.targetItemReference);
  }

  /**
   * Returns the hash code of this DataProcess.
   *
   * @return the hash code of this DataProcess
   */
  @Override
  public int hashCode() {
    return Objects.hash(sourceItemReference, targetItemReference);
  }

  /**
   * Returns the string representation of this DataProcess.
   *
   * @return the string representation of this DataProcess
   */
  @Override
  public String toString() {
    return new StringJoiner(", ", DataProcess.class.getSimpleName() + "[", "]")
        .add("sourceItemReference=" + sourceItemReference)
        .add("targetItemReference=" + targetItemReference)
        .toString();
  }

  /**
   * Creates a new Builder instance for constructing a DataProcess.
   *
   * @param id the item identifier
   * @param name the name of the item
   * @return a new Builder instance
   */
  public static Builder builder(@NotNull ItemIdentifier id, @NotNull String name) {
    return new Builder(id, name);
  }

  /** Builder class for constructing DataProcess instances. */
  public static class Builder extends Item.Builder<DataProcess, Builder> {

    /** The source item references to be set in the DataProcess. */
    private List<ItemReference> sourceItemReference;

    /** The target item references to be set in the DataProcess. */
    private List<ItemReference> targetItemReference;

    /**
     * Constructs a Builder instance with the specified item identifier and name.
     *
     * @param id the item identifier
     * @param name the name of the item
     */
    private Builder(@NotNull ItemIdentifier id, @NotNull String name) {
      super(id, name);
    }

    /**
     * Sets the source item references for the DataProcess.
     *
     * @param sourceItemReference the source item references
     * @return this Builder instance
     */
    public Builder sourceItemReference(@NotNull List<ItemReference> sourceItemReference) {
      this.sourceItemReference = sourceItemReference;
      return this;
    }

    /**
     * Sets the target item references for the DataProcess.
     *
     * @param targetItemReference the target item references
     * @return this Builder instance
     */
    public Builder targetItemReference(@NotNull List<ItemReference> targetItemReference) {
      this.targetItemReference = targetItemReference;
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

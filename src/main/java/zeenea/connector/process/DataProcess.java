package zeenea.connector.process;

import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.Item;
import zeenea.connector.common.ItemReference;
import zeenea.connector.exception.ExceptionUtils;

/** Represents a data process which extends the Item class. */
public final class DataProcess extends Item {

  /** The source item references. */
  @NotNull private final List<ItemReference> source;

  /** The target item references. */
  @NotNull private final List<ItemReference> target;

  /**
   * Constructs a DataProcess instance using the provided builder.
   *
   * @param builder the builder to construct the DataProcess instance
   */
  private DataProcess(Builder builder) {
    super(builder);
    ExceptionUtils.requireNonNullOrEmpty("sourceItemReference", builder.source);
    ExceptionUtils.requireNonNullOrEmpty("targetItemReference", builder.target);
    this.source = List.copyOf(builder.source);
    this.target = List.copyOf(builder.target);
  }

  /**
   * Gets the source item references.
   *
   * @return the source item references
   */
  public @NotNull List<ItemReference> getSource() {
    return source;
  }

  /**
   * Gets the target item references.
   *
   * @return the target item references
   */
  public @NotNull List<ItemReference> getTarget() {
    return target;
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
    return Objects.equals(source, that.source) && Objects.equals(target, that.target);
  }

  /**
   * Returns the hash code of this DataProcess.
   *
   * @return the hash code of this DataProcess
   */
  @Override
  public int hashCode() {
    return Objects.hash(source, target);
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
        + "source="
        + source
        + ", target="
        + target
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

    /** The source item references to be set in the DataProcess. */
    private List<ItemReference> source;

    /** The target item references to be set in the DataProcess. */
    private List<ItemReference> target;

    /**
     * Sets the source item references for the DataProcess.
     *
     * @param source the source item references
     * @return this Builder instance
     */
    public Builder source(@NotNull List<ItemReference> source) {
      this.source = List.copyOf(source);
      return this;
    }

    /**
     * Sets the target item references for the DataProcess.
     *
     * @param target the target item references
     * @return this Builder instance
     */
    public Builder target(@NotNull List<ItemReference> target) {
      this.target = List.copyOf(target);
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

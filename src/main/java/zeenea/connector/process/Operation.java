package zeenea.connector.process;

import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.common.ItemReference;
import zeenea.connector.exception.ExceptionUtils;

/** Represents a field to field lineage operation in a DataProcess. */
public class Operation {

  /** The source item references. */
  @NotNull private final List<ItemReference> sources;

  /** The target item references. */
  @NotNull private final List<ItemReference> targets;

  /**
   * Constructs an Operation instance using the provided builder.
   *
   * @param builder the builder to construct the Operation instance
   */
  private Operation(Builder builder) {
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
   * Creates a new Builder instance for constructing a Operation.
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /** Builder class for constructing Operation instances. */
  public static class Builder {

    /** The list of source item references for the operation. */
    private List<ItemReference> sources = new ArrayList<>();

    /** The list of target item references for the operation. */
    private List<ItemReference> targets = new ArrayList<>();

    /**
     * Sets the list of source item references for the Operation.
     *
     * @param sources the list of source item references
     * @return this Builder instance
     */
    public Builder sources(@NotNull List<ItemReference> sources) {
      this.sources = List.copyOf(sources);
      return this;
    }

    /**
     * Sets the list of source item references for the Operation.
     *
     * @param sources the list of source item references
     * @return this Builder instance
     */
    public Builder sources(ItemReference... sources) {
      this.sources = List.of(sources);
      return this;
    }

    /**
     * Sets the list of target item references for the Operation.
     *
     * @param targets the list of target item references
     * @return this Builder instance
     */
    public Builder targets(@NotNull List<ItemReference> targets) {
      this.targets = List.copyOf(targets);
      return this;
    }

    /**
     * Sets the list of target item references for the Operation.
     *
     * @param targets the list of target item references
     * @return this Builder instance
     */
    public Builder targets(ItemReference... targets) {
      this.targets = List.of(targets);
      return this;
    }

    /**
     * Builds and returns an Operation instance.
     *
     * @return a new Operation instance
     */
    protected Operation build() {
      return new Operation(this);
    }
  }
}

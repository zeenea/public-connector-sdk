package zeenea.connector;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.contact.ContactRelation;
import zeenea.connector.property.*;

/**
 * Base class for all types of source items. An item is an object that is managed and searchable in
 * Zeenea Data Catalog.
 *
 * @see zeenea.connector.dataset.Dataset
 * @see zeenea.connector.visualization.Visualization
 * @see zeenea.connector.process.DataProcess
 * @see zeenea.connector.dataproduct.DataProduct
 */
public abstract class Item {

  /** The identifier of the item. */
  private final ItemIdentifier id;

  /** The name of the item. */
  private final String name;

  /** The description of the item. */
  private final String description;

  /** The properties of the item. */
  private final Map<String, PropertyValue> properties;

  /** The contact relations of the item. */
  private final List<ContactRelation> contactRelations;

  /**
   * Constructs an Item instance using the provided builder.
   *
   * @param builder the builder used to create the Item instance
   */
  protected Item(Builder<?, ?> builder) {
    this.name = Objects.requireNonNull(builder.name, "name");
    this.id = Objects.requireNonNull(builder.id, "id");
    this.description = builder.description;
    this.properties = new HashMap<>(builder.properties);
    this.contactRelations = List.copyOf(builder.contactRelations);
  }

  /**
   * Gets the name of the item.
   *
   * @return the name of the item
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the identifier of the item.
   *
   * @return the identifier of the item
   */
  public ItemIdentifier getId() {
    return id;
  }

  /**
   * Gets the description of the item.
   *
   * @return an Optional containing the description of the item if present, otherwise an empty
   *     Optional
   */
  public Optional<String> getDescription() {
    return Optional.ofNullable(description);
  }

  /**
   * Gets the properties of the item.
   *
   * @return an unmodifiable map of the properties of the item
   */
  public Map<String, PropertyValue> getProperties() {
    return Collections.unmodifiableMap(properties);
  }

  /**
   * Gets the contact relations of the item.
   *
   * @return a collection of the contact relations of the item
   */
  public Collection<ContactRelation> getContactRelations() {
    return contactRelations;
  }

  /**
   * Abstract builder class for creating instances of items.
   *
   * @param <T> the type of item being built
   * @param <THIS> the type of the builder itself
   */
  public abstract static class Builder<T, THIS extends Builder<T, THIS>> {

    /** The identifier of the item. */
    private ItemIdentifier id;

    /** The name of the item. */
    private String name;

    /** The description of the item. */
    private String description;

    /** The properties of the item. */
    private Map<String, PropertyValue> properties = Map.of();

    /** The contact relations of the item. */
    private List<ContactRelation> contactRelations = List.of();

    /**
     * Sets the identifier of the item.
     *
     * @param id the identifier of the item
     * @return the builder instance
     */
    public THIS id(@NotNull ItemIdentifier id) {
      this.id = id;
      return self();
    }

    /**
     * Sets the name of the item.
     *
     * @param name the name of the item
     * @return the builder instance
     */
    public THIS name(@NotNull String name) {
      this.name = name;
      return self();
    }

    /**
     * Sets the description of the item.
     *
     * @param description the description of the item
     * @return the builder instance
     */
    public THIS description(@Nullable String description) {
      this.description = description;
      return self();
    }

    /**
     * Sets the properties of the item.
     *
     * @param properties the properties of the item
     * @return the builder instance
     */
    public THIS properties(@NotNull Map<String, PropertyValue> properties) {
      this.properties = Map.copyOf(properties);
      return self();
    }

    /**
     * Set a list of contact relations to the item.
     *
     * @param contactRelations the list of contact relations to add
     * @return the builder instance
     */
    public THIS contactRelations(@NotNull List<ContactRelation> contactRelations) {
      this.contactRelations = List.copyOf(contactRelations);
      return self();
    }

    /**
     * Builds and returns the item instance.
     *
     * @return the created item instance
     */
    public final T build() {
      return performBuild();
    }

    /**
     * Performs the build operation to create the item instance.
     *
     * @return the created item instance
     */
    protected abstract T performBuild();

    /**
     * Returns the builder instance itself.
     *
     * @return the builder instance
     */
    @SuppressWarnings("unchecked")
    private THIS self() {
      return (THIS) this;
    }
  }
}

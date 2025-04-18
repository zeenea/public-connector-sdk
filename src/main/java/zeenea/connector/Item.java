package zeenea.connector;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zeenea.connector.common.IdentificationProperty;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.contact.Contact;
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

  /** The contacts of the item. */
  private final List<Contact> contacts;

  /** The glossaryRefs of the item. */
  private final List<String> glossaryRefs;

  /** The customItemRefs of the item. */
  private final List<String> customItemRefs;

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
    this.contacts = List.copyOf(builder.contacts);
    this.glossaryRefs = List.copyOf(builder.glossaryRefs);
    this.customItemRefs = List.copyOf(builder.customItemRefs);
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
   * Gets the contacts of the item.
   *
   * @return a collection of the contacts of the item
   */
  public Collection<Contact> getContacts() {
    return contacts;
  }

  /**
   * Gets the glossary refs of the item.
   *
   * @return a collection of the glossary refs of the item
   */
  public Collection<String> getGlossaryRefs() {
    return glossaryRefs;
  }

  /**
   * Gets the custom item refs of the item.
   *
   * @return a collection of the custom item refs of the item
   */
  public Collection<String> getCustomItemRefs() {
    return customItemRefs;
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
    private List<Contact> contacts = List.of();

    /** The glossary refs of the item. */
    private List<String> glossaryRefs = List.of();

    /** The custom item refs of the item. */
    private List<String> customItemRefs = List.of();

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
     * Sets the identifier of the item.
     *
     * @param id the identifier of the item
     * @return the builder instance
     */
    public THIS id(IdentificationProperty... id) {
      this.id = ItemIdentifier.of(id);
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
     * Set a list of contacts to the item.
     *
     * @param contacts the list of contacts to add
     * @return the builder instance
     */
    public THIS contacts(@NotNull List<Contact> contacts) {
      this.contacts = List.copyOf(contacts);
      return self();
    }

    /**
     * Set a list of contacts to the item.
     *
     * @param contacts the list of contacts to add
     * @return the builder instance
     */
    public THIS contacts(Contact... contacts) {
      this.contacts = List.of(contacts);
      return self();
    }

    /**
     * Set a list of glossary refs to the item.
     *
     * @param glossaryRefs the list of glossary refs to add
     * @return the builder instance
     */
    public THIS glossaryRefs(@NotNull Collection<String> glossaryRefs) {
      this.glossaryRefs = List.copyOf(glossaryRefs);
      return self();
    }

    /**
     * Set a list of glossary refs to the item.
     *
     * @param glossaryRefs the list of glossary refs to add
     * @return the builder instance
     */
    public THIS glossaryRefs(String... glossaryRefs) {
      this.glossaryRefs = List.of(glossaryRefs);
      return self();
    }

    /**
     * Set a list of custom item refs to the item.
     *
     * @param customItemRefs the list of custom item refs to add
     * @return the builder instance
     */
    public THIS customItemRefs(@NotNull Collection<String> customItemRefs) {
      this.customItemRefs = List.copyOf(customItemRefs);
      return self();
    }

    /**
     * Set a list of custom item refs to the item.
     *
     * @param customItemRefs the list of custom item refs to add
     * @return the builder instance
     */
    public THIS customItemRefs(String... customItemRefs) {
      this.customItemRefs = List.of(customItemRefs);
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

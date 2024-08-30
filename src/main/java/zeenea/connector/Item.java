package zeenea.connector;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.contact.ContactRelation;
import zeenea.connector.exception.ExceptionUtils;
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
    ExceptionUtils.requireNonNull("contactRelations", builder.contactRelations);
    ExceptionUtils.requireNonNull("propertyDefinition", builder.properties);
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
    private final ItemIdentifier id;

    /** The name of the item. */
    private final String name;

    /** The description of the item. */
    private String description;

    /** The properties of the item. */
    private final Map<String, PropertyValue> properties = new HashMap<>();

    /** The contact relations of the item. */
    private final List<ContactRelation> contactRelations = new ArrayList<>();

    /**
     * Constructs a Builder instance with the specified identifier and name.
     *
     * @param id the identifier of the item
     * @param name the name of the item
     */
    protected Builder(@NotNull ItemIdentifier id, @NotNull String name) {
      this.id = id;
      this.name = name;
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
     * Adds a string property to the item.
     *
     * @param propertyDefinition the definition of the string property
     * @param value the value of the string property
     * @return the builder instance
     */
    public THIS addProperty(
        @NotNull StringPropertyDefinition propertyDefinition, @NotNull String value) {
      return addProperty(propertyDefinition, new StringPropertyValue(value));
    }

    /**
     * Adds a string property to the item.
     *
     * @param propertyDefinition the definition of the string property
     * @param value the value of the string property
     * @return the builder instance
     */
    public THIS addProperty(
        @NotNull StringPropertyDefinition propertyDefinition, @NotNull StringPropertyValue value) {
      return putProperty(propertyDefinition, value);
    }

    /**
     * Adds a number property to the item.
     *
     * @param propertyDefinition the definition of the number property
     * @param value the value of the number property
     * @return the builder instance
     */
    public THIS addProperty(@NotNull NumberPropertyDefinition propertyDefinition, long value) {
      return addProperty(propertyDefinition, BigDecimal.valueOf(value));
    }

    /**
     * Adds a number property to the item.
     *
     * @param propertyDefinition the definition of the number property
     * @param value the value of the number property
     * @return the builder instance
     */
    public THIS addProperty(@NotNull NumberPropertyDefinition propertyDefinition, double value) {
      return addProperty(propertyDefinition, BigDecimal.valueOf(value));
    }

    /**
     * Adds a number property to the item.
     *
     * @param propertyDefinition the definition of the number property
     * @param value the value of the number property
     * @return the builder instance
     */
    public THIS addProperty(
        @NotNull NumberPropertyDefinition propertyDefinition, @NotNull BigDecimal value) {
      return addProperty(propertyDefinition, new NumberPropertyValue(value));
    }

    /**
     * Adds a number property to the item.
     *
     * @param propertyDefinition the definition of the number property
     * @param value the value of the number property
     * @return the builder instance
     */
    public THIS addProperty(
        @NotNull NumberPropertyDefinition propertyDefinition, @NotNull NumberPropertyValue value) {
      return putProperty(propertyDefinition, value);
    }

    /**
     * Adds a URL property to the item.
     *
     * @param propertyDefinition the definition of the URL property
     * @param value the value of the URL property
     * @return the builder instance
     */
    public THIS addProperty(@NotNull UrlPropertyDefinition propertyDefinition, @NotNull URI value) {
      return addProperty(propertyDefinition, new UrlPropertyValue(value));
    }

    /**
     * Adds a URL property to the item.
     *
     * @param propertyDefinition the definition of the URL property
     * @param uri the URI of the URL property
     * @param label the label of the URL property
     * @return the builder instance
     */
    public THIS addProperty(
        @NotNull UrlPropertyDefinition propertyDefinition,
        @NotNull URI uri,
        @NotNull String label) {
      return addProperty(propertyDefinition, new UrlPropertyValue(uri, label));
    }

    /**
     * Adds a URL property to the item.
     *
     * @param propertyDefinition the definition of the URL property
     * @param value the value of the URL property
     * @return the builder instance
     */
    public THIS addProperty(
        @NotNull UrlPropertyDefinition propertyDefinition, @NotNull UrlPropertyValue value) {
      return putProperty(propertyDefinition, value);
    }

    /**
     * Adds an instant property to the item.
     *
     * @param propertyDefinition the definition of the instant property
     * @param value the value of the instant property
     * @return the builder instance
     */
    public THIS addProperty(
        @NotNull InstantPropertyDefinition propertyDefinition, @NotNull Instant value) {
      return addProperty(propertyDefinition, new InstantPropertyValue(value));
    }

    /**
     * Adds an instant property to the item.
     *
     * @param propertyDefinition the definition of the instant property
     * @param value the value of the instant property
     * @return the builder instance
     */
    public THIS addProperty(
        @NotNull InstantPropertyDefinition propertyDefinition,
        @NotNull InstantPropertyValue value) {
      return putProperty(propertyDefinition, value);
    }

    /**
     * Adds a tag property to the item.
     *
     * @param propertyDefinition the definition of the tag property
     * @param value the value of the tag property
     * @return the builder instance
     */
    public THIS addProperty(
        @NotNull TagPropertyDefinition propertyDefinition, @NotNull TagPropertyValue value) {
      return putProperty(propertyDefinition, value);
    }

    /**
     * Adds a property to the item.
     *
     * @param propertyDefinition the definition of the property
     * @param value the value of the property
     * @return the builder instance
     */
    private THIS putProperty(PropertyDefinition propertyDefinition, PropertyValue value) {
      this.properties.put(propertyDefinition.getCode(), value);
      return self();
    }

    /**
     * Adds a contact relation to the item.
     *
     * @param contactRelation the contact relation to add
     * @return the builder instance
     */
    public THIS addContactRelation(@NotNull ContactRelation contactRelation) {
      this.contactRelations.add(contactRelation);
      return self();
    }

    /**
     * Adds a list of contact relations to the item.
     *
     * @param contactRelations the list of contact relations to add
     * @return the builder instance
     */
    public THIS addContactRelations(@NotNull List<ContactRelation> contactRelations) {
      this.contactRelations.addAll(contactRelations);
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

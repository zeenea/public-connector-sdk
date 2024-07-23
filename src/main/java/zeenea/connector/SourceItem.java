package zeenea.connector;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Instant;
import java.util.*;
import zeenea.connector.common.ItemIdentifier;
import zeenea.connector.contact.SourceContactRelation;
import zeenea.connector.property.*;

/**
 * Base class for all kinds of source items. An item is something that is managed and searchable in
 * Zeenea Data Catalog.
 *
 * @since 1.0.0
 */
public abstract class SourceItem {

  private final String name;

  private final ItemIdentifier id;

  private final String description;

  private final Map<String, SourcePropertyValue> properties;

  private final Instant updateTime;

  private final Collection<SourceContactRelation> contactRelations;

  protected SourceItem(Builder<?, ?> builder) {
    this.name = builder.name;
    this.id = builder.id;
    this.description = builder.description;
    this.properties = new HashMap<>(builder.properties);
    this.updateTime = builder.updateTime;
    this.contactRelations = List.copyOf(builder.contactRelations);
  }

  /**
   * Get the name of the source item.
   *
   * @return The name of the source item, cannot be longer than 1024 characters
   */
  public String getName() {
    return name;
  }

  /**
   * Get the id of the source item. This is unique to the source item.
   *
   * @return The id of the source item, cannot be longer than 1024 characters
   */
  public ItemIdentifier getId() {
    return id;
  }

  /**
   * Get the description of the source item. This is an optional property.
   *
   * @return The description of the source item, cannot be longer than 32768 (32x1024) characters,
   *     or {@code Optional.empty()} if absent
   */
  public Optional<String> getDescription() {
    return Optional.ofNullable(description);
  }

  /**
   * Get the technical metadata of the source item. Metadata are necessary for search engine usage.
   * The collection cannot be modified.
   *
   * @return The technical metadata of the source item
   */
  public Map<String, SourcePropertyValue> getProperties() {
    return Collections.unmodifiableMap(properties);
  }

  /**
   * Get the last update time of the source item. This is an optional property.
   *
   * @return The last update time of the source item, or {@code Optional.empty()} if absent
   */
  public Optional<Instant> getUpdateTime() {
    return Optional.ofNullable(updateTime);
  }

  /**
   * Get the contact relations of the source item. The collection cannot be modified.
   *
   * @return The contact relations of the source item
   */
  public Collection<SourceContactRelation> getContactRelations() {
    return contactRelations;
  }

  /**
   * A utility class to create {@link SourceItem}-subclass instances following the <em>Builder</em>
   * design pattern (abstract base class).
   */
  public abstract static class Builder<T, SELF extends Builder<T, ?>> {

    private final Map<String, SourcePropertyValue> properties = new HashMap<>();
    private final List<SourceContactRelation> contactRelations = new ArrayList<>();
    private String name;
    private ItemIdentifier id;
    private String description;
    private Instant updateTime;

    protected Builder() {}

    protected static void throwIfNull(String attributeName, Object attributeValue) {
      if (attributeValue == null)
        throw new NullPointerException("Attribute \"" + attributeName + "\" cannot be null");
    }

    protected static void throwIfInvalidLength(
        String attributeName, String attributeValue, int maxLength) {
      if (attributeValue != null && attributeValue.length() > maxLength)
        throw new IllegalArgumentException(
            "Attribute \""
                + attributeName
                + "\" cannot be more than "
                + maxLength
                + " characters long");
    }

    /**
     * Get the name of the source item. This is required to build a SourceItem.
     *
     * @return The name of the source item, cannot be longer than 1024 characters
     */
    public String getName() {
      return name;
    }

    /**
     * Set the name of the source item. This is required to build a SourceItem.
     *
     * @param name The name of the source item, cannot be longer than 1024 characters
     * @return This builder
     */
    public SELF name(String name) {
      this.name = name;
      return self();
    }

    /**
     * Get the id of the source item. Must be unique to the source item. This is required to build a
     * SourceItem.
     *
     * @return The id of the source item, cannot be longer than 1024 characters
     */
    public ItemIdentifier getId() {
      return id;
    }

    /**
     * Set the id of the source item. Must be unique to the source item. This is required to build a
     * SourceItem.
     *
     * @param id The id of the source item, cannot be longer than 1024 characters
     * @return This builder
     */
    public SELF id(ItemIdentifier id) {
      this.id = id;
      return self();
    }

    /**
     * Get the description of the source item.
     *
     * @return The description of the source item, cannot be longer than 32768 (32x1024) characters
     */
    public String getDescription() {
      return description;
    }

    /**
     * Set the description of the source item.
     *
     * @param description The description of the source item, cannot be longer than 32768 (32x1024)
     *     characters
     * @return This builder
     */
    public SELF description(String description) {
      this.description = description;
      return self();
    }

    /**
     * Get the technical properties of the source item.
     *
     * @return The technical properties of the source item
     */
    public Map<String, SourcePropertyValue> getProperties() {
      return properties;
    }

    /**
     * Add a StringMetadata and its value to the source item. Metadata are necessary for search
     * engine usage.
     *
     * @param property the property definition
     * @param value the value for this metadata
     * @return This builder
     */
    public SELF addProperty(StringSourcePropertyDefinition property, String value) {
      return addProperty(property, new StringSourcePropertyValue(value));
    }

    /**
     * Add a StringMetadata and its value to the source item. Metadata are necessary for search
     * engine usage.
     *
     * @param property the metadata
     * @param value the value for this metadata
     * @return This builder
     */
    public SELF addProperty(
        StringSourcePropertyDefinition property, StringSourcePropertyValue value) {
      return putMetadata(property, value);
    }

    /**
     * Add a NumberMetadata and its value to the source item. Metadata are necessary for search
     * engine usage.
     *
     * @param property the metadata
     * @param value the value for this metadata
     * @return This builder
     */
    public SELF addProperty(NumberSourcePropertyDefinition property, long value) {
      return addProperty(property, BigDecimal.valueOf(value));
    }

    /**
     * Add a NumberMetadata and its value to the source item. Metadata are necessary for search
     * engine usage.
     *
     * @param property the metadata
     * @param value the value for this metadata
     * @return This builder
     */
    public SELF addProperty(NumberSourcePropertyDefinition property, double value) {
      return addProperty(property, BigDecimal.valueOf(value));
    }

    /**
     * Add a NumberMetadata and its value to the source item. Metadata are necessary for search
     * engine usage.
     *
     * @param property the metadata
     * @param value the value for this metadata
     * @return This builder
     */
    public SELF addProperty(NumberSourcePropertyDefinition property, BigDecimal value) {
      return addProperty(property, new NumberSourcePropertyValue(value));
    }

    /**
     * Add a NumberMetadata and its value to the source item. Metadata are necessary for search
     * engine usage.
     *
     * @param property the metadata
     * @param value the value for this metadata
     * @return This builder
     */
    public SELF addProperty(
        NumberSourcePropertyDefinition property, NumberSourcePropertyValue value) {
      return putMetadata(property, value);
    }

    /**
     * Add an UrlMetadata and its value to the source item. Metadata are necessary for search engine
     * usage.
     *
     * @param property the metadata
     * @param value the value for this metadata
     * @return This builder
     */
    public SELF addProperty(UrlSourcePropertyDefinition property, URI value) {
      return addProperty(property, new UrlSourcePropertyValue(value));
    }

    /**
     * Add an UrlMetadata and its value to the source item. Metadata are necessary for search engine
     * usage.
     *
     * @param property the metadata
     * @param uri the uri value for this metadata
     * @param label the label value for this metadata
     * @return This builder
     */
    public SELF addProperty(UrlSourcePropertyDefinition property, URI uri, String label) {
      return addProperty(property, new UrlSourcePropertyValue(uri, label));
    }

    /**
     * Add an UrlMetadata and its value to the source item. Metadata are necessary for search engine
     * usage.
     *
     * @param property the metadata
     * @param value the value for this metadata
     * @return This builder
     */
    public SELF addProperty(UrlSourcePropertyDefinition property, UrlSourcePropertyValue value) {
      return putMetadata(property, value);
    }

    /**
     * Add an InstantMetadata and its value to the source item. Metadata are necessary for search
     * engine usage.
     *
     * @param property the metadata
     * @param value the value for this metadata
     * @return This builder
     */
    public SELF addProperty(InstantSourcePropertyDefinition property, Instant value) {
      return addProperty(property, new InstantSourcePropertyValue(value));
    }

    /**
     * Add an InstantMetadata and its value to the source item. Metadata are necessary for search
     * engine usage.
     *
     * @param metadata the metadata
     * @param value the value for this metadata
     * @return This builder
     */
    public SELF addProperty(
        InstantSourcePropertyDefinition metadata, InstantSourcePropertyValue value) {
      return putMetadata(metadata, value);
    }

    private SELF putMetadata(
        SourcePropertyDefinition sourcePropertyDefinition, SourcePropertyValue value) {
      this.properties.put(sourcePropertyDefinition.getCode(), value);
      return self();
    }

    /**
     * Get the last update time of the source item.
     *
     * @return The last update time of the source item
     */
    public Instant getUpdateTime() {
      return updateTime;
    }

    /**
     * Set the last update time of the source item.
     *
     * @param updateTime The last update time of the source item
     * @return This builder
     */
    public SELF updateTime(Instant updateTime) {
      this.updateTime = updateTime;
      return self();
    }

    /**
     * Get the contact relations of the source item.
     *
     * @return The contact relations of the source item
     */
    public List<SourceContactRelation> getContactRelations() {
      return contactRelations;
    }

    /**
     * Add a ContactRelation to the source item.
     *
     * @param contactRelation the ContactRelation to add
     * @return This builder
     */
    public SELF addContactRelation(SourceContactRelation contactRelation) {
      this.contactRelations.add(contactRelation);
      return self();
    }

    /**
     * Build the source item.
     *
     * @return The source item
     * @throws NullPointerException if one or more mandatory attributes are not set
     * @throws IllegalArgumentException if one or more attributes set are not valid
     */
    public final T build() {
      throwIfNull("name", name);
      throwIfInvalidLength("name", name, 1024);
      throwIfNull("id", id);
      throwIfInvalidLength("description", description, 32 * 1024);
      return performBuild();
    }

    protected abstract T performBuild();

    @SuppressWarnings("unchecked")
    private SELF self() {
      return (SELF) this;
    }
  }
}

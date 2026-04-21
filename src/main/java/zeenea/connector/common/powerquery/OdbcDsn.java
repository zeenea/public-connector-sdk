package zeenea.connector.common.powerquery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an ODBC DSN definition. This is used to obtain the details of an ODBC data source when
 * one is used within a Power Query. This makes it possible to establish lineage to the source
 * dataset.
 */
public class OdbcDsn {
  /** The name of the data source */
  @NotNull private final String name;

  /** The engine type for the data source */
  @NotNull private final OdbcEngine engine;

  /** The list of attributes associated with the data source */
  @NotNull private final List<Attribute> attributes;

  /**
   * Constructs an OdbcDsn instance using the provided builder.
   *
   * @param builder the builder used to create the OdbcDsn instance
   */
  private OdbcDsn(OdbcDsn.Builder builder) {
    this.name = Objects.requireNonNull(builder.name, "name");
    this.engine = Objects.requireNonNull(builder.engine, "engine");
    this.attributes = builder.attributes;
  }

  /**
   * Creates a new OdbcDsn instance.
   *
   * @param name the name of the data source
   * @param engine the type of engine defined by the data source
   * @param attributes the list of attributes defined by the data source
   * @return a new OdbcDsn instance
   */
  public static OdbcDsn of(
      @NotNull String name, @NotNull OdbcEngine engine, @NotNull List<Attribute> attributes) {
    return builder().name(name).engine(engine).attributes(attributes).build();
  }

  /**
   * Creates a new OdbcDsn instance with an empty list of attributes.
   *
   * @param name the name of the data source
   * @param engine the type of engine defined by the data source
   * @return a new OdbcDsn instance
   */
  public static OdbcDsn of(@NotNull String name, @NotNull OdbcEngine engine) {
    return of(name, engine, List.of());
  }

  /**
   * Gets the name of the data source.
   *
   * @return the name of the data source.
   */
  public @NotNull String getName() {
    return name;
  }

  /**
   * Gets the engine defined by the data source.
   *
   * @return the engine defined by the data source.
   */
  public @NotNull OdbcEngine getEngine() {
    return engine;
  }

  /**
   * Gets the attributes defined by the data source.
   *
   * @return the attributes defined by the data source.
   */
  public @NotNull List<Attribute> getAttributes() {
    return attributes;
  }

  /**
   * Gets a specific attribute
   *
   * @param name the name of the attribute
   * @return the value of the attribute
   */
  public Optional<String> getAttribute(String name) {
    return attributes.stream()
        .filter(attribute -> attribute.getName().equals(name))
        .map(Attribute::getValue)
        .findFirst();
  }

  /**
   * Creates a new builder for the OdbcDsn class.
   *
   * @return a new Builder instance
   */
  public static OdbcDsn.Builder builder() {
    return new OdbcDsn.Builder();
  }

  /**
   * Checks if this OdbcDsn is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this OdbcDsn is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OdbcDsn that = (OdbcDsn) o;
    return Objects.equals(name, that.name)
        && Objects.equals(engine, that.engine)
        && Objects.equals(attributes, that.attributes);
  }

  /**
   * Computes the hash code for this OdbcDsn.
   *
   * @return the hash code of this OdbcDsn
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, engine, attributes);
  }

  /**
   * Returns a string representation of this OdbcDsn.
   *
   * @return a string representation of this OdbcDsn
   */
  @Override
  public String toString() {
    return "OdbcDsn{" + "name=" + name + ", engine=" + engine + ", attributes=" + attributes + "}";
  }

  /** Builder class for creating instances of OdbcDsn. */
  public static class Builder {

    /** The name of the data source. */
    private String name;

    /** The engine type for the data source */
    private OdbcEngine engine;

    /** The list of attributes associated with the data source */
    private List<Attribute> attributes = new ArrayList<>();

    /**
     * Sets the name of the data source.
     *
     * @param name the name of the data source
     * @return the Builder instance
     */
    public OdbcDsn.Builder name(@NotNull String name) {
      this.name = name;
      return this;
    }

    /**
     * Sets the engine type for the data source.
     *
     * @param engine the engine type for the data source
     * @return the Builder instance
     */
    public OdbcDsn.Builder engine(@NotNull OdbcEngine engine) {
      this.engine = engine;
      return this;
    }

    /**
     * Sets the list of attributes associated with the data source.
     *
     * @param attributes the list of attributes associated with the data source
     * @return the Builder instance
     */
    public OdbcDsn.Builder attributes(@NotNull List<Attribute> attributes) {
      this.attributes = attributes;
      return this;
    }

    /**
     * Builds and returns a OdbcDsn instance.
     *
     * @return the created OdbcDsn instance
     */
    public OdbcDsn build() {
      return new OdbcDsn(this);
    }
  }
}

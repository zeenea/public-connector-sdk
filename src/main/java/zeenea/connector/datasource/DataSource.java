package zeenea.connector.datasource;

import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.common.DataSourceIdentifier;

/**
 * Represents a data source with a unique identifier and a human-readable name.
 *
 * <p>This DataSource must be unique for each data source in the system.
 *
 * <pre>Example : <br>
 * {
 *  "identifier": {
 *    "identificationProperties": [
 *      { "key": "type", "value": "mysql" },
 *      { "key": "host", "value": "mysql.database-cluster1.example.com" },
 *      { "key": "port", "value": "3306" },
 *      { "key": "database", "value": "acme-db" },
 *    ]
 *  },
 *  "name": "My Local Database"
 * }
 * </pre>
 *
 * type is mandatory for a proper matching
 */
public final class DataSource {
  /** The unique identifier for the data source. */
  @NotNull private final DataSourceIdentifier identifier;

  /** The human-readable name for the data source. */
  @NotNull private final String name;

  /**
   * Constructs a DataSource instance using the provided builder.
   *
   * @param builder the builder used to create the DataSource instance
   */
  private DataSource(Builder builder) {

    this.identifier = Objects.requireNonNull(builder.identifier, "identifier");
    this.name = Objects.requireNonNull(builder.name, "name");
  }

  /**
   * Creates a new builder for DataSource class
   *
   * @return a new Builder instance
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Get the name of the data source.
   *
   * @return the name of the data source
   */
  public @NotNull String getName() {
    return name;
  }

  /**
   * Get the unique identifier of the data source.
   *
   * @return the DataSourceIdentifier instance
   */
  public @NotNull DataSourceIdentifier getIdentifier() {
    return identifier;
  }

  /**
   * Checks if this DataSource is equal to another object.
   *
   * @param o the reference object with which to compare.
   * @return true if this DataSource is the same as the obj argument; false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    DataSource that = (DataSource) o;
    return Objects.equals(identifier, that.identifier) && Objects.equals(name, that.name);
  }

  /**
   * Returns a hash code value for the DataSource.
   *
   * @return a hash code value for this DataSource.
   */
  @Override
  public int hashCode() {
    return Objects.hash(identifier, name);
  }

  @Override
  public String toString() {
    return "DataSource{" + "identifier=" + identifier + ", name='" + name + '\'' + '}';
  }

  /** Builder class for creating instances of DataSource. */
  public static class Builder {

    private DataSourceIdentifier identifier;
    private String name;

    /**
     * Set a DataSourceIdentifier to the builder.
     *
     * @param identifier the DataSourceIdentifier to set
     * @return the builder instance
     */
    public Builder identifier(@NotNull DataSourceIdentifier identifier) {
      this.identifier = identifier;
      return this;
    }

    /**
     * Sets the human-readable name for the data source.
     *
     * @param name the name to set
     * @return the builder instance
     */
    public Builder name(@NotNull String name) {
      if (name.isEmpty()) {
        throw new IllegalArgumentException("name cannot be empty");
      }
      this.name = name;
      return this;
    }

    /**
     * Builds and returns the DataSource instance.
     *
     * @return the created DataSource instance
     */
    public DataSource build() {
      return new DataSource(this);
    }
  }
}

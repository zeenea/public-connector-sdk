package zeenea.connector.common;

import java.util.Objects;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/** Represents a SQL query linked to an {@link zeenea.connector.Item}. */
public final class QueryReference {
  /** The SQL query for the item. */
  @NotNull private final String sqlQuery;

  /** The SQL dialect of the query. */
  @NotNull private final SqlDialect sqlDialect;

  /** The data source identifier associated with the query */
  @Nullable private final DataSourceIdentifier dataSourceIdentifier;

  /**
   * Constructs an QueryReference instance using the provided builder.
   *
   * @param builder the builder used to create the QueryReference instance
   */
  private QueryReference(QueryReference.Builder builder) {
    this.sqlQuery = Objects.requireNonNull(builder.sqlQuery, "sqlQuery");
    this.sqlDialect = Objects.requireNonNull(builder.sqlDialect, "sqlDialect");
    this.dataSourceIdentifier = builder.dataSourceIdentifier;
  }

  /**
   * Creates a new QueryReference instance with the specified SQL query, SQL dialect and Data Source
   * Identifier.
   *
   * @param sqlQuery the query for the item
   * @param sqlDialect the sql dialect for the query
   * @param dataSourceIdentifier the data source identifier associated with the query
   * @return a new QueryReference instance
   */
  public static QueryReference of(
      @NotNull String sqlQuery,
      @NotNull SqlDialect sqlDialect,
      @Nullable DataSourceIdentifier dataSourceIdentifier) {
    return builder()
        .sqlQuery(sqlQuery)
        .sqlDialect(sqlDialect)
        .dataSourceIdentifier(dataSourceIdentifier)
        .build();
  }

  /**
   * Creates a new QueryReference instance with the specified item SQL query and SQL dialect,
   * without any data source reference.
   *
   * @param sqlQuery the query for the item
   * @param sqlDialect the sql dialect for the query
   * @return a new QueryReference instance
   */
  public static QueryReference of(@NotNull String sqlQuery, @NotNull SqlDialect sqlDialect) {
    return of(sqlQuery, sqlDialect, null);
  }

  /**
   * Gets the SQL query for the item.
   *
   * @return the SQL query for the item
   */
  public @NotNull String getSqlQuery() {
    return sqlQuery;
  }

  /**
   * Gets the SQL dialect of the query.
   *
   * @return the SQL dialect of the query
   */
  public @NotNull SqlDialect getSqlDialect() {
    return sqlDialect;
  }

  /**
   * Gets the data source identifier associated with the query, if any.
   *
   * @return an Optional containing the data source identifier associated with the query if present,
   *     otherwise an empty Optional
   */
  public Optional<DataSourceIdentifier> getDataSourceIdentifier() {
    return Optional.ofNullable(dataSourceIdentifier);
  }

  /**
   * Creates a new builder for the QueryReference class.
   *
   * @return a new Builder instance
   */
  public static QueryReference.Builder builder() {
    return new QueryReference.Builder();
  }

  /**
   * Checks if this QueryReference is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this QueryReference is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    QueryReference that = (QueryReference) o;
    return Objects.equals(sqlQuery, that.sqlQuery)
        && Objects.equals(sqlDialect, that.sqlDialect)
        && Objects.equals(dataSourceIdentifier, that.dataSourceIdentifier);
  }

  /**
   * Computes the hash code for this QueryReference.
   *
   * @return the hash code of this QueryReference
   */
  @Override
  public int hashCode() {
    return Objects.hash(sqlQuery, sqlDialect, dataSourceIdentifier);
  }

  /**
   * Returns a string representation of this QueryReference.
   *
   * @return a string representation of this QueryReference
   */
  @Override
  public String toString() {
    return "QueryReference{"
        + "sqlQuery="
        + sqlQuery
        + ", sqlDialect="
        + sqlDialect
        + ", dataSourceIdentifier="
        + dataSourceIdentifier
        + "}";
  }

  /** Builder class for creating instances of QueryReference. */
  public static class Builder {

    /** The SQL query for the item. */
    private String sqlQuery;

    /** The SQL dialect for the query. */
    private SqlDialect sqlDialect;

    /** The data source identifier associated with the query, if any. */
    private DataSourceIdentifier dataSourceIdentifier;

    /**
     * Sets the query for the item.
     *
     * @param sqlQuery the query for the item
     * @return the Builder instance
     */
    public QueryReference.Builder sqlQuery(@Nullable String sqlQuery) {
      this.sqlQuery = sqlQuery;
      return this;
    }

    /**
     * Sets the sql dialect for the query.
     *
     * @param sqlDialect the dialect of the query
     * @return the Builder instance
     */
    public QueryReference.Builder sqlDialect(@Nullable SqlDialect sqlDialect) {
      this.sqlDialect = sqlDialect;
      return this;
    }

    /**
     * Sets the sql dialect for the query.
     *
     * @param sqlDialectName the dialect name, ANSI will be used if null or not found
     * @return the Builder instance
     */
    public QueryReference.Builder sqlDialect(@Nullable String sqlDialectName) {
      this.sqlDialect = SqlDialect.fromCustomName(sqlDialectName);
      return this;
    }

    /**
     * Sets the data source identifier for the query.
     *
     * @param dataSourceIdentifier the data source identifier to set
     * @return the builder instance
     */
    public QueryReference.Builder dataSourceIdentifier(
        @Nullable DataSourceIdentifier dataSourceIdentifier) {
      this.dataSourceIdentifier = dataSourceIdentifier;
      return this;
    }

    /**
     * Builds and returns an QueryReference instance.
     *
     * @return the created QueryReference instance
     */
    public QueryReference build() {
      return new QueryReference(this);
    }
  }
}

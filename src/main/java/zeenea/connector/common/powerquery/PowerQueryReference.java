package zeenea.connector.common.powerquery;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Represents a Power Query linked to an {@link zeenea.connector.Item} that will generate lineage
 * reference based on the query.
 */
public final class PowerQueryReference {
  /** The Power Query for the item. */
  @NotNull private final String powerQuery;

  /** ODBC DSN details to resolve lineage for ODBC sources */
  @NotNull private final List<OdbcDsn> dsns;

  /**
   * Constructs an QueryReference instance using the provided builder.
   *
   * @param builder the builder used to create the QueryReference instance
   */
  private PowerQueryReference(PowerQueryReference.Builder builder) {
    this.powerQuery = Objects.requireNonNull(builder.powerQuery, "powerQuery");
    this.dsns = builder.dsns != null ? builder.dsns : List.of();
  }

  /**
   * Creates a new PowerQueryReference instance with the specified Power Query, and optional DSNs.
   *
   * @param powerQuery the query for the item
   * @param dsns the list of ODBC DSN definitions
   * @return a new PowerQueryReference instance
   */
  public static PowerQueryReference of(@NotNull String powerQuery, @Nullable List<OdbcDsn> dsns) {
    return builder().powerQuery(powerQuery).dsns(dsns).build();
  }

  /**
   * Creates a new PowerQueryReference instance with the specified Power Query, without any DSNs.
   *
   * @param powerQuery the query for the item
   * @return a new QueryReference instance
   */
  public static PowerQueryReference of(@NotNull String powerQuery) {
    return of(powerQuery, null);
  }

  /**
   * Gets the Power Query for the item.
   *
   * @return the Power Query for the item
   */
  public @NotNull String getPowerQuery() {
    return powerQuery;
  }

  /**
   * Gets the ODBC DSNs for the item.
   *
   * @return the ODBC DSNs for the item.
   */
  public @NotNull List<OdbcDsn> getDsns() {
    return dsns;
  }

  /**
   * Creates a new builder for the PowerQueryReference class.
   *
   * @return a new Builder instance
   */
  public static PowerQueryReference.Builder builder() {
    return new PowerQueryReference.Builder();
  }

  /**
   * Checks if this PowerQueryReference is equal to another object.
   *
   * @param o the object to compare with
   * @return true if this PowerQueryReference is equal to the specified object, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PowerQueryReference that = (PowerQueryReference) o;
    return Objects.equals(powerQuery, that.powerQuery) && Objects.equals(dsns, that.dsns);
  }

  /**
   * Computes the hash code for this PowerQueryReference.
   *
   * @return the hash code of this PowerQueryReference
   */
  @Override
  public int hashCode() {
    return Objects.hash(powerQuery, dsns);
  }

  /**
   * Returns a string representation of this PowerQueryReference.
   *
   * @return a string representation of this PowerQueryReference
   */
  @Override
  public String toString() {
    return "PowerQueryReference{" + "powerQuery=" + powerQuery + ", dsns=" + dsns + "}";
  }

  /** Builder class for creating instances of PowerQueryReference. */
  public static class Builder {

    /** The Power Query for the item. */
    private String powerQuery;

    /** The list of DSNs. */
    private List<OdbcDsn> dsns = new ArrayList<>();

    /**
     * Sets the Power Query for the item.
     *
     * @param powerQuery the query for the item
     * @return the Builder instance
     */
    public PowerQueryReference.Builder powerQuery(@Nullable String powerQuery) {
      this.powerQuery = powerQuery;
      return this;
    }

    /**
     * Sets the list of DSNs.
     *
     * @param dsns the list of DSNs
     * @return the Builder instance
     */
    public PowerQueryReference.Builder dsns(@Nullable List<OdbcDsn> dsns) {
      this.dsns = dsns;
      return this;
    }

    /**
     * Builds and returns a PowerQueryReference instance.
     *
     * @return the created PowerQueryReference instance
     */
    public PowerQueryReference build() {
      return new PowerQueryReference(this);
    }
  }
}

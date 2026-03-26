package zeenea.connector.common.powerquery;

import java.util.Arrays;

/** Enum representing possible ODBC engine providers. */
public enum OdbcEngine {
  ATHENA("athena"),
  BIGQUERY("bigquery"),
  DENODO("denodo"),
  ORACLE("oracle"),
  REDSHIFT("redshift"),
  SNOWFLAKE("snowflake"),
  SQLSERVER("sqlserver"),
  TERADATA("teradata"),
  UNKNOWN("unknown");

  private final String odbcEngine;

  OdbcEngine(String odbcEngine) {
    this.odbcEngine = odbcEngine;
  }

  public String getOdbcEngine() {
    return odbcEngine;
  }

  /**
   * Returns the OdbcEngine associated with the given name. Returns UNKNOWN if the name is null or
   * not found.
   *
   * @param name The name to look up
   * @return The matching OdbcEngine or UNKNOWN
   */
  public static OdbcEngine fromName(String name) {
    if (name == null) {
      return UNKNOWN;
    }

    return Arrays.stream(values())
        .filter(dialect -> dialect.odbcEngine.equalsIgnoreCase(name))
        .findFirst()
        .orElse(UNKNOWN);
  }
}

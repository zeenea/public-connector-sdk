package zeenea.connector.common;

import java.util.Arrays;

/** Enum representing all SQL dialects available. */
public enum SqlDialect {
  ANSI("ansi"),
  ATHENA("athena"),
  BIGQUERY("bigquery"),
  CLICKHOUSE("clickhouse"),
  DATABRICKS("databricks"),
  DB2("db2"),
  DUCKDB("duckdb"),
  EXASOL("exasol"),
  GREENPLUM("greenplum"),
  HIVE("hive"),
  IMPALA("impala"),
  MARIADB("mariadb"),
  MATERIALIZE("materialize"),
  MYSQL("mysql"),
  ORACLE("oracle"),
  POSTGRES("postgres"),
  REDSHIFT("redshift"),
  SNOWFLAKE("snowflake"),
  SOQL("soql"),
  SPARKSQL("sparksql"),
  SQLITE("sqlite"),
  STARROCKS("starrocks"),
  TERADATA("teradata"),
  TRINO("trino"),
  TSQL("tsql"),
  VERTICA("vertica");

  private final String dialectName;

  SqlDialect(String dialectName) {
    this.dialectName = dialectName;
  }

  public String getDialectName() {
    return dialectName;
  }

  /**
   * Returns the SqlDialect associated with the given name. Returns ANSI if the name is null or not
   * found.
   *
   * @param name The name to look up
   * @return The matching SqlDialect or ANSI
   */
  public static SqlDialect fromCustomName(String name) {
    if (name == null) {
      return ANSI;
    }

    return Arrays.stream(values())
        .filter(dialect -> dialect.dialectName.equalsIgnoreCase(name))
        .findFirst()
        .orElse(ANSI);
  }
}

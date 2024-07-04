package zeenea.connector.dataset;

/** Enum representing various Zeenea data types that can be used in a dataset fields. */
public enum DataType {
  String,
  Byte,
  Short,
  Integer,
  Long,
  Float,
  Double,
  Boolean,
  Date,
  Time,
  Timestamp,
  Binary,
  BigDecimal,
  GeoPoint,
  GeoShape,
  Struct,
  Map,
  Null,
  Unknown;

  /**
   * Checks if the data type is an integer type.
   *
   * @return true if the data type is Byte, Short, Integer, or Long, otherwise false
   */
  public boolean isInteger() {
    return this == Byte || this == Short || this == Integer || this == Long;
  }

  /**
   * Checks if the data type is a numeric type.
   *
   * @return true if the data type is an integer type, Float, Double, or BigDecimal, otherwise false
   */
  public boolean isNumeric() {
    return isInteger() || this == Float || this == Double || this == BigDecimal;
  }

  /**
   * Checks if the data type is a geographic type.
   *
   * @return true if the data type is GeoShape or GeoPoint, otherwise false
   */
  public boolean isGeo() {
    return this == GeoShape || this == GeoPoint;
  }
}

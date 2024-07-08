package zeenea.connector.dataset;

import java.util.List;
import java.util.stream.Collectors;

public enum SourceDataType {
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

  public boolean isInteger() {
    return this == Byte || this == Short || this == Integer || this == Long;
  }

  public boolean isNumeric() {
    return isInteger() || this == Float || this == Double || this == BigDecimal;
  }

  public boolean isGeo() {
    return this == GeoShape || this == GeoPoint;
  }

  public static SourceDataType moreGenericType(List<SourceDataType> types) {
    return moreGenericType(types, false);
  }

  public static SourceDataType moreGenericType(
      List<SourceDataType> types, boolean transparentUnknown) {

    // Remove Null, transparent Unknowns and duplicates
    List<SourceDataType> preProcessed =
        types.stream()
            .filter(t -> t != Null && (transparentUnknown || t != Unknown))
            .distinct()
            .collect(Collectors.toList());

    if (preProcessed.isEmpty()) {
      return types.contains(Null) ? Null : Unknown;
    }
    if (preProcessed.size() == 1) {
      return preProcessed.get(0);
    }
    if (preProcessed.stream().allMatch(SourceDataType::isInteger)) {
      return Long;
    }
    if (preProcessed.stream().allMatch(SourceDataType::isNumeric)) {
      return Double;
    }
    if (preProcessed.stream().allMatch(SourceDataType::isGeo)) {
      return GeoShape;
    }
    if (!preProcessed.contains(Unknown)) {
      return Binary;
    }
    return Unknown;
  }
}

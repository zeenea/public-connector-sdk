package zeenea.connector.datasampling;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.locationtech.jts.geom.*;

class SampleValueTest {

  @Test
  void ofString() throws JsonProcessingException {
    SampleValue testSample = SampleValue.of("test");
    assertThat(testSample.jsonify()).isEqualTo("\"test\"");
  }

  @Test
  void ofByte() throws JsonProcessingException {
    SampleValue testSample = SampleValue.of((byte) 123);
    assertThat(testSample.jsonify()).isEqualTo("123");
  }

  @Test
  void ofShort() throws JsonProcessingException {
    SampleValue testSample = SampleValue.of((short) 12345);
    assertThat(testSample.jsonify()).isEqualTo("12345");
  }

  @Test
  void ofInteger() throws JsonProcessingException {
    SampleValue testSample = SampleValue.of(12345678);
    assertThat(testSample.jsonify()).isEqualTo("12345678");
  }

  @Test
  void ofBoolean() throws JsonProcessingException {
    SampleValue testSample = SampleValue.of(false);
    assertThat(testSample.jsonify()).isEqualTo("false");
  }

  @Test
  void ofLong() throws JsonProcessingException {
    SampleValue testSample = SampleValue.of(1234567890L);
    assertThat(testSample.jsonify()).isEqualTo("1234567890");
  }

  @Test
  void ofMultiValue() throws JsonProcessingException {
    SampleValue testSample =
        SampleValue.of(
            SampleValue.of("Knatte"), SampleValue.of("Fnatte"), SampleValue.of("Tjatte"));
    assertThat(testSample.jsonify()).isEqualTo("[\"Knatte\",\"Fnatte\",\"Tjatte\"]");
  }

  @Test
  void ofBadMultiValue() throws JsonProcessingException {
    SampleValue testSample =
        SampleValue.of(SampleValue.of("value1"), SampleValue.of("true"), SampleValue.of("42L"));
    assertThat(testSample.jsonify()).isEqualTo("[\"value1\",\"true\",\"42L\"]");
  }

  @Test
  void ofStruct() throws JsonProcessingException {
    SampleValue testSample =
        SampleValue.of(
            SampleValue.of("name1", SampleValue.of("value1")),
            SampleValue.of("name2", SampleValue.of(true)),
            SampleValue.of("name3", SampleValue.of(42L)));
    assertThat(testSample.jsonify())
        .isEqualTo("{\"name1\":\"value1\",\"name2\":true,\"name3\":42}");
  }

  @Test
  void ofNestedStruct() throws JsonProcessingException {
    SampleValue testSample =
        SampleValue.of(
            SampleValue.of("name1", SampleValue.of("value1")),
            SampleValue.of("name2", SampleValue.of(true)),
            SampleValue.of("name3", SampleValue.of(42L)),
            SampleValue.of(
                "name4",
                SampleValue.of(
                    SampleValue.of("name1", SampleValue.of("value1")),
                    SampleValue.of("name2", SampleValue.of(true)),
                    SampleValue.of("name3", SampleValue.of(42L)))));
    assertThat(testSample.jsonify())
        .isEqualTo(
            "{\"name1\":\"value1\",\"name2\":true,\"name3\":42,\"name4\":{\"name1\":\"value1\",\"name2\":true,\"name3\":42}}");
  }

  @Test
  void ofGeoPoint() throws JsonProcessingException {
    GeometryFactory geometryFactory = new GeometryFactory();

    SampleValue testSample =
        SampleValue.of(geometryFactory.createPoint(new Coordinate(10.2, 30.8)));
    assertThat(testSample.jsonify()).isEqualTo("\"POINT (10.2 30.8)\"");
  }

  @Test
  void ofGeoLine() throws JsonProcessingException {
    GeometryFactory geometryFactory = new GeometryFactory();

    SampleValue testSample =
        SampleValue.of(
            geometryFactory.createLineString(
                new Coordinate[] {new Coordinate(10.2, 30.8), new Coordinate(56.1, 87.5)}));
    assertThat(testSample.jsonify()).isEqualTo("\"LINESTRING (10.2 30.8, 56.1 87.5)\"");
  }

  @Test
  void ofGeoPolygon() throws JsonProcessingException {
    GeometryFactory geometryFactory = new GeometryFactory();

    SampleValue testSample =
        SampleValue.of(
            geometryFactory.createPolygon(
                new Coordinate[] {
                  new Coordinate(10.2, 30.8),
                  new Coordinate(56.1, 87.5),
                  new Coordinate(75, 10),
                  new Coordinate(10.2, 30.8)
                }));
    assertThat(testSample.jsonify())
        .isEqualTo("\"POLYGON ((10.2 30.8, 56.1 87.5, 75 10, 10.2 30.8))\"");
  }

  @Test
  void ofGeoMultiPoint() throws JsonProcessingException {
    GeometryFactory geometryFactory = new GeometryFactory();

    SampleValue testSample =
        SampleValue.of(
            geometryFactory.createMultiPoint(
                new Point[] {
                  geometryFactory.createPoint(new Coordinate(10.2, 30.8)),
                  geometryFactory.createPoint(new Coordinate(1, 2))
                }));
    assertThat(testSample.jsonify()).isEqualTo("\"MULTIPOINT ((10.2 30.8), (1 2))\"");
  }

  @Test
  void ofGeoMultiLine() throws JsonProcessingException {
    GeometryFactory geometryFactory = new GeometryFactory();

    SampleValue testSample =
        SampleValue.of(
            geometryFactory.createMultiLineString(
                new LineString[] {
                  geometryFactory.createLineString(
                      new Coordinate[] {new Coordinate(10.2, 30.8), new Coordinate(56.1, 87.5)}),
                  geometryFactory.createLineString(
                      new Coordinate[] {new Coordinate(1, 2), new Coordinate(3, 4)})
                }));
    assertThat(testSample.jsonify())
        .isEqualTo("\"MULTILINESTRING ((10.2 30.8, 56.1 87.5), (1 2, 3 4))\"");
  }

  @Test
  void ofGeoMultiPolygon() throws JsonProcessingException {
    GeometryFactory geometryFactory = new GeometryFactory();

    SampleValue testSample =
        SampleValue.of(
            geometryFactory.createMultiPolygon(
                new Polygon[] {
                  geometryFactory.createPolygon(
                      new Coordinate[] {
                        new Coordinate(10.2, 30.8),
                        new Coordinate(56.1, 87.5),
                        new Coordinate(75, 10),
                        new Coordinate(10.2, 30.8)
                      }),
                  geometryFactory.createPolygon(
                      new Coordinate[] {
                        new Coordinate(1, 2),
                        new Coordinate(3, 4),
                        new Coordinate(5, 6),
                        new Coordinate(1, 2)
                      }),
                }));
    assertThat(testSample.jsonify())
        .isEqualTo(
            "\"MULTIPOLYGON (((10.2 30.8, 56.1 87.5, 75 10, 10.2 30.8)), ((1 2, 3 4, 5 6, 1 2)))\"");
  }

  @Test
  void ofNull() throws JsonProcessingException {
    SampleValue testSample = SampleValue.nullValue();
    assertThat(testSample.jsonify()).isEqualTo("\"NULL\"");
  }

  @Test
  void ofUnknown() throws JsonProcessingException {
    SampleValue testSample = SampleValue.unknowValue();
    assertThat(testSample.jsonify()).isEqualTo("\"Unknown\"");
  }

  /*
  Missing types :
  Float
  Double
  Date
  Time
  Timestamp
  Binary
  BigDecimal
  Map
  */
}

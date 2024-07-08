package zeenea.connector.common;

public class IdentificationKey {

  private final String key;
  private final String value;

  public IdentificationKey(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}

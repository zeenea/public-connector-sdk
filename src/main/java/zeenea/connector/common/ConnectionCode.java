package zeenea.connector.common;

public final class ConnectionCode implements ConnectionReference {
  private final String value;

  public ConnectionCode(String value) {
    this.value = value;
  }

  @Override
  public String getValue() {
    return this.value;
  }
}

package zeenea.connector.common;

public final class ConnectionAlias implements ConnectionReference {
  private final String value;

  public ConnectionAlias(String value) {
    this.value = value;
  }

  @Override
  public String getValue() {
    return this.value;
  }
}

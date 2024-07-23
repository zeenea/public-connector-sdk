package zeenea.connector.dataproduct;

import static zeenea.connector.exception.ExceptionUtils.throwIfNull;

import org.jetbrains.annotations.NotNull;

public class DataContract {

  public enum Type {
    DataContractDotCom
  }

  private final Type type;
  private final String source;

  private DataContract(Type type, String source) {
    this.type = type;
    this.source = source;
  }

  public Type getType() {
    return type;
  }

  public String getSource() {
    return source;
  }

  public static Builder builder(@NotNull Type type, @NotNull String source) {
    return new Builder(type, source);
  }

  public static class Builder {

    private final Type type;
    private final String source;

    private Builder(@NotNull Type type, @NotNull String source) {
      throwIfNull("type", type);
      throwIfNull("source", source);
      this.type = type;
      this.source = source;
    }

    public DataContract build() {
      return new DataContract(type, source);
    }
  }
}

package zeenea.connector.common;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class SourceItemReference {
  private final List<IdentificationKey> identificationProperties;
  private final String connectionAlias;

  public SourceItemReference(Builder builder) {
    this.identificationProperties = builder.identificationProperties;
    this.connectionAlias = builder.connectionAlias;
  }

  public List<IdentificationKey> getIdentificationProperties() {
    return identificationProperties;
  }

  public String getConnectionAlias() {
    return connectionAlias;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private List<IdentificationKey> identificationProperties;
    private String connectionAlias;

    private Builder() {}

    public Builder identificationProperties(List<IdentificationKey> identificationProperties) {
      this.identificationProperties = identificationProperties;
      return this;
    }

    public Builder connectionAlias(String connectionAlias) {
      this.connectionAlias = connectionAlias;
      return this;
    }

    protected SourceItemReference build() {
      if (this.identificationProperties == null || this.identificationProperties.isEmpty()) {
        throw new IllegalArgumentException("identificationProperties must not be null or empty");
      }
      return new SourceItemReference(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SourceItemReference that = (SourceItemReference) o;
    return Objects.equals(identificationProperties, that.identificationProperties)
        && Objects.equals(connectionAlias, that.connectionAlias);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identificationProperties, connectionAlias);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SourceItemReference.class.getSimpleName() + "[", "]")
        .add("connectionAlias='" + connectionAlias + "'")
        .add("identificationProperties=" + identificationProperties)
        .toString();
  }
}

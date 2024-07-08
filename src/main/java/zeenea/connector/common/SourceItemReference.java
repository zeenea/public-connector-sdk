package zeenea.connector.common;

import java.util.List;

public class SourceItemReference {
  private final String connectionAlias;
  private final List<IdentificationKey> identificationProperties;

  public SourceItemReference(Builder builder) {
    this.connectionAlias = builder.connectionAlias;
    this.identificationProperties = builder.identificationProperties;
  }

  public String getConnectionAlias() {
    return connectionAlias;
  }

  public List<IdentificationKey> getIdentificationProperties() {
    return identificationProperties;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String connectionAlias;
    private List<IdentificationKey> identificationProperties;

    private Builder() {}

    public Builder connectionAlias(String connectionAlias) {
      this.connectionAlias = connectionAlias;
      return this;
    }

    public Builder identificationProperties(List<IdentificationKey> identificationProperties) {
      this.identificationProperties = identificationProperties;
      return this;
    }

    protected SourceItemReference build() {
      if (this.identificationProperties == null || this.identificationProperties.isEmpty()) {
        throw new IllegalArgumentException("identificationProperties must not be null or empty");
      }
      return new SourceItemReference(this);
    }
  }
}

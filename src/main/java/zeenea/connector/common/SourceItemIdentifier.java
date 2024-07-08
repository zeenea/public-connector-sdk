package zeenea.connector.common;

import java.util.List;

public class SourceItemIdentifier {
  private final List<String> label;
  private final List<IdentificationKey> identificationProperties;

  public SourceItemIdentifier(Builder builder) {
    this.label = builder.label;
    this.identificationProperties = builder.identificationProperties;
  }

  public List<String> getLabel() {
    return label;
  }

  public List<IdentificationKey> getIdentificationProperties() {
    return identificationProperties;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private List<String> label;
    private List<IdentificationKey> identificationProperties;

    private Builder() {}

    public Builder label(List<String> label) {
      this.label = label;
      return this;
    }

    public Builder identificationProperties(List<IdentificationKey> identificationProperties) {
      this.identificationProperties = identificationProperties;
      return this;
    }

    protected SourceItemIdentifier build() {
      if (this.identificationProperties == null || this.identificationProperties.isEmpty()) {
        throw new IllegalArgumentException("identificationProperties must not be null or empty");
      }
      return new SourceItemIdentifier(this);
    }
  }
}

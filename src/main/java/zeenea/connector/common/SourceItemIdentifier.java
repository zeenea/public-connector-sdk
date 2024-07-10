package zeenea.connector.common;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class SourceItemIdentifier {
  private final List<IdentificationKey> identificationProperties;
  private final List<String> label;

  public SourceItemIdentifier(Builder builder) {
    this.identificationProperties = builder.identificationProperties;
    this.label = builder.label;
  }

  public List<IdentificationKey> getIdentificationProperties() {
    return identificationProperties;
  }

  public List<String> getLabel() {
    return label;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private List<IdentificationKey> identificationProperties;
    private List<String> label;

    private Builder() {}

    public Builder identificationProperties(List<IdentificationKey> identificationProperties) {
      this.identificationProperties = identificationProperties;
      return this;
    }

    public Builder label(List<String> label) {
      this.label = label;
      return this;
    }

    protected SourceItemIdentifier build() {
      if (this.identificationProperties == null || this.identificationProperties.isEmpty()) {
        throw new IllegalArgumentException("identificationProperties must not be null or empty");
      }
      return new SourceItemIdentifier(this);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SourceItemIdentifier that = (SourceItemIdentifier) o;
    return Objects.equals(identificationProperties, that.identificationProperties)
        && Objects.equals(label, that.label);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identificationProperties, label);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SourceItemIdentifier.class.getSimpleName() + "[", "]")
        .add("identificationProperties=" + identificationProperties)
        .add("label=" + label)
        .toString();
  }
}

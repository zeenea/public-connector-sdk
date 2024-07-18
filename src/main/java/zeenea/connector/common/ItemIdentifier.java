package zeenea.connector.common;

import java.util.List;

public class ItemIdentifier {

  private final List<IdentificationProperty> identificationProperties;

  ItemIdentifier(List<IdentificationProperty> identificationProperties) {
    this.identificationProperties = identificationProperties;
  }

  public List<IdentificationProperty> getIdentificationProperties() {
    return identificationProperties;
  }
}

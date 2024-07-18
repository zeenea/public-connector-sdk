package zeenea.connector.inventory;

import java.util.Optional;
import zeenea.connector.Connection;
import zeenea.connector.common.ItemIdentifier;

public interface DataSamplingConnection extends Connection {

  Optional<DataSample> collectDataSample(ItemIdentifier identificationPropertyList, int sampleSize);
}

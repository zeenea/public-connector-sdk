package zeenea.connector.sample;

import zeenea.connector.common.ItemIdentifier;

public interface SampleConnector {

    DataSample collectDataSample(ItemIdentifier itemIdentifier, int sampleSize);
}

package zeenea.connector.sample;

import zeenea.connector.common.ItemIdentifier;

public class SampleTest {

    public class SampleMockConnectorV2 implements SampleConnector {

        @Override
        public DataSample collectDataSample(ItemIdentifier itemIdentifier, int sampleSize) {
            return DataSample.builder()
                    .addFields(List<String> fields)
                    .addRow(List<SampleCellValue> rowData)
                    .addRow(List<SampleCellValue> rowData)
                    .addRow(List<SampleCellValue> rowData)
                    .addRow(List<SampleCellValue> rowData)
                    .addRow(List<SampleCellValue> rowData)
                    .addRow(List<SampleCellValue> rowData)
                    .build();
        };
    }

}

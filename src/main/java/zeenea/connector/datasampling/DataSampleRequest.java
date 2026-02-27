package zeenea.connector.datasampling;

import zeenea.connector.common.ItemReference;

public class DataSampleRequest {

    /**
     *  The item reference to sample data from
     */
    private final ItemReference itemToSample; // Ou type ItemToSample

    /**
     * The number of samples to collect
     */
    private final int sampleSize;

    public DataSampleRequest(ItemReference itemToSample, int sampleSize) {
        this.itemToSample = itemToSample;
        this.sampleSize = sampleSize;
    }

    public ItemReference getItemToSample() {
        return itemToSample;
    }

    public int getSampleSize() {
        return sampleSize;
    }
}

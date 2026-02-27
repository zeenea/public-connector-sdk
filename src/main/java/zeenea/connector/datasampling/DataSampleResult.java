package zeenea.connector.datasampling;

public class DataSampleResult {

    /**
     * The SampleData
     */
    private final DataSample sample;

    private DataSampleResult(DataSample sample) {
        this.sample = sample;
    }

    public DataSample getSample() {
        return sample;
    }

    public static DataSampleResult of(DataSample sample) {
        return new DataSampleResult(sample);
    }
}

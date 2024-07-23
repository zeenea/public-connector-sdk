package zeenea.connector.dataproduct;

import static zeenea.connector.exception.ExceptionUtils.throwIfNull;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import zeenea.connector.dataset.Dataset;
import zeenea.connector.property.PropertyValue;

public class OutputPort {

  private final DataContract dataContract;
  private final List<Dataset> datasets;
  private final Map<String, PropertyValue> properties;

  public OutputPort(
      DataContract dataContract,
      Collection<Dataset> datasets,
      Map<String, PropertyValue> properties) {
    this.dataContract = dataContract;
    this.datasets = List.copyOf(datasets);
    this.properties = Map.copyOf(properties);
  }

  public DataContract getDataContract() {
    return dataContract;
  }

  public List<Dataset> getDatasets() {
    return datasets;
  }

  public Map<String, PropertyValue> getProperties() {
    return properties;
  }

  public static Builder builder(@NotNull DataContract dataContract) {
    return new Builder(dataContract);
  }

  public static class Builder {

    private final DataContract dataContract;
    private final List<Dataset> datasets = new ArrayList<>();
    private final Map<String, PropertyValue> properties = new HashMap<>();

    public Builder(@NotNull DataContract dataContract) {
      throwIfNull("dataContract", dataContract);
      this.dataContract = dataContract;
    }

    public Builder dataset(Dataset dataset) {
      this.datasets.add(dataset);
      return this;
    }

    public Builder datasets(Collection<Dataset> datasets) {
      this.datasets.addAll(datasets);
      return this;
    }

    public Builder property(String code, PropertyValue value) {
      this.properties.put(code, value);
      return this;
    }

    public Builder properties(Map<String, PropertyValue> properties) {
      this.properties.putAll(properties);
      return this;
    }

    public OutputPort build() {
      return new OutputPort(dataContract, datasets, properties);
    }
  }
}

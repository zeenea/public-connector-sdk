package zeenea.connector.dataproduct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import zeenea.connector.common.ItemReference;

public class InputPort {

  private final List<ItemReference> inputs;

  private InputPort(Collection<ItemReference> inputs) {
    this.inputs = List.copyOf(inputs);
  }

  public List<ItemReference> getInputs() {
    return inputs;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private final List<ItemReference> inputs = new ArrayList<>();

    public Builder input(ItemReference input) {
      this.inputs.add(input);
      return this;
    }

    public Builder inputs(Collection<ItemReference> inputs) {
      this.inputs.addAll(inputs);
      return this;
    }

    public InputPort build() {
      return new InputPort(this.inputs);
    }
  }
}

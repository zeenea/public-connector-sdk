package zeenea.sdk;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

// not need to be thread safe because instantiated at each synchronization
public class CustomItemConnectorImpl implements CustomItemConnector {

    @Override
    public void close() throws Exception {
    }

    // TODO : créer un builder avec un constructeur (id, name, code)
    //  - permettre d'ajouter des metadata values au fur et à mesure
    //  - ajouter ou pas la description
    //  - autant de contactrelation que nécessaire

    // constructeur vide est un pré-requis pf4j, mais on pourrait se faire injecter
    // la conf quand même avec une technique spécifique pf4j
    public CustomItemConnectorImpl() {
    }

    @Override
    public ConfigurationValidationResult validateConfig(Map<String, String> config) {
        return null;
    }

    @Override
    public Set<Metadata> getTechnicalMetadata() {
        return null;
    }

    @Override
    public SynchronizationResult synchronize(Optional<Long> lastSuccessfulVersion) {
        return null;
    }


}
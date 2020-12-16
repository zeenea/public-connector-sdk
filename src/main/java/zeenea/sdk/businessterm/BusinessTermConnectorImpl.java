package zeenea.sdk.businessterm;

import zeenea.sdk.ConfigurationValidationResult;
import zeenea.sdk.SynchronizationResult;
import zeenea.sdk.property.Metadata;

import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.Set;

// not need to be thread safe because instantiated at each synchronization
public class BusinessTermConnectorImpl implements BusinessTermConnector {

    @Override
    public void close() throws Exception {
    }

    // TODO : créer un builder avec un constructeur (id, name, code)
    //  - permettre d'ajouter des metadata values au fur et à mesure
    //  - ajouter ou pas la description
    //  - autant de contactrelation que nécessaire

    // constructeur vide est un pré-requis pf4j, mais on pourrait se faire injecter
    // la conf quand même avec une technique spécifique pf4j
    public BusinessTermConnectorImpl() {
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
    public SynchronizationResult<SourceBusinessTerm> synchronize(Long lastSuccessfulVersion) {
        return null;
    }

}
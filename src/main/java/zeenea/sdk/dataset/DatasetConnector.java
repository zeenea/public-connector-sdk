package zeenea.sdk.dataset;

import zeenea.sdk.ConfigurationValidationResult;
import zeenea.sdk.SynchronizationResult;
import zeenea.sdk.dataprocess.DataProcess;
import zeenea.sdk.property.Metadata;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

// Il est CAPITAL que le format des externalIds soit documenté par le développeur de DatasetConnector
// Ce format est utilisé par DataProcessConnector et VisualisationConnector
// TODO Cette interface n'est pas terminée
public interface DatasetConnector extends AutoCloseable {

    ConfigurationValidationResult validateConfig(Map<String, String> config);

    Set<Metadata> getTechnicalMetadata();

}


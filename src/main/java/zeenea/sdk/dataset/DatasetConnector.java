package zeenea.sdk.dataset;

import zeenea.sdk.ItemConnector;
import zeenea.sdk.SourceItem;

// Il est CAPITAL que le format des externalIds soit documenté par le développeur de DatasetConnector
// Ce format est utilisé par DataProcessConnector et VisualisationConnector
// TODO Cette interface n'est pas terminée
public interface DatasetConnector extends ItemConnector<SourceItem> {
}


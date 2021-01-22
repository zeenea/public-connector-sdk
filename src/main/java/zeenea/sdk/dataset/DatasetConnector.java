package zeenea.sdk.dataset;

import zeenea.sdk.Connector;
import zeenea.sdk.SourceItem;
import zeenea.sdk.annotations.Beta;

// Il est CAPITAL que le format des externalIds soit documenté par le développeur de DatasetConnector
// Ce format est utilisé par DataProcessConnector et VisualisationConnector
@Beta
public interface DatasetConnector extends Connector<SourceItem> {
}


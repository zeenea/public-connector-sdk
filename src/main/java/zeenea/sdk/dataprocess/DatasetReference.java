package zeenea.sdk.dataprocess;

import zeenea.sdk.ConnectionCode;
import zeenea.sdk.annotations.Beta;

@Beta
class DatasetReference {
    // connection MUST exist
    // Mapping between ConnectionCode and connection id is to be performed by Scanner
    private final ConnectionCode connectionCode;

    // referenced dataset MIGHT exist
    // referenced dataset SHOULD exist in inventory, at least
    // max ??? chars
    //
    // Native path == machin avec des slashs
    // le mapping entre la représentation du dataset pour la techno intégrée et l'externalId doit être faite ici
    // ex: /my_schema/my_table
    // ex: projects/zeenea-connector-test-yellow/locations/europe-west1/entryGroups/@bigquery/entries/qkjfhkjsdhkjsdhghsdfmljgqmhdùlksjwfm
    private final String externalId;

    public DatasetReference(ConnectionCode connectionCode, String externalId) {
        this.connectionCode = connectionCode;
        this.externalId = externalId;
    }

    public ConnectionCode getConnectionCode() {
        return connectionCode;
    }

    public String getExternalId() {
        return externalId;
    }
}

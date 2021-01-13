package zeenea.sdk.customitem;

import zeenea.sdk.ConfigurationValidationResult;
import zeenea.sdk.SynchronizationResult;
import zeenea.sdk.annotations.Beta;
import zeenea.sdk.property.Metadata;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Beta
public interface CustomItemConnector extends AutoCloseable {

    // TODO la config est simplifiée mais du coup les valeurs secrètes ne sont plus identifiables
    // on pourrait injecter deux configs : secrètes et pas secrètes
    // appelé au démarrage pour se faire configuré
    ConfigurationValidationResult validateConfig(Map<String, String> config);

    // scanner appelle cette méthode avant un synchronize
    // si détecte un changement dans le template alors appelle synchronize(Optional.empty)
    // sinon synchronize incrémental
    // dictionnaire indexé par un code UNIQUE et une valeur typée
    //  exemple : String, BigDecimal, Instant, (URI + lien) et d'autres à venir...
    Set<Metadata> getTechnicalMetadata();

    // scanner appelle cette méthode
    // connector send all custom items, with a long for tagging last call to synchronize
    // scanner must consume all custom item
    SynchronizationResult<SourceCustomItem> synchronize(Optional<Long> lastSuccessfulVersion);

}


package zeenea.sdk;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CustomItem {

    // max 1024 exemple : Application
    // obligatoire
    String name;

    // obligatoire
    // max 1024
    // identifiant externe du custom item
    // nécessite une unicité sinon écrasement :fearful:
    String id;

    // ancien custom item type = code (example: APP for Application)
    // obligatoire
    String code;

    // max 32 * 1024
    Optional<String> description;

    // nécessaire pour exploiter dans le moteur de recherche
    Map<String, Metadata> metadata;

    // last update time
    Instant updateTime;

    List<ContactRelation> contactRelations;

    // no schemaVersion because no inventory

}


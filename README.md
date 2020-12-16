# public-connector-sdk

## Design review

- (Seb) scope de la design review
  - nous allons vous présenter les api des connecteurs
  - nous ne parlerons ni de l'implémentation côté plateforme ni côté connecteur
  - ces nouveaux connecteurs vont être directement codés par les clients
- (Arthur) schéma du cycle de vie du connecteur
- (Seb) types du contrat
  - la configration n'est pas remontée à la plateforme (pas de problème pour les passwords)
- (Arthur) builders
- (Seb) présenter les next steps :
  - modifier l'agent et la plateforme pour supporter le nouveau sdk
    - les deux versions du sdk doivent cohabiter en même temps sur un même scanner
  - voici le plan pour valider le design et l'implémentation :
    - porter les connecteurs mock custom item et mock business terms vers le sdk
    - tester le pf4j

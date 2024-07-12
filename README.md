# codelijst-materiaalklasse

## Samenvatting

De codelijst 'Materiaalklasses' bevat eenduidige benamingen van ...


## Projectinhoud
In dit project worden de bron bestanden en de verschillende verspreidingsvormen van de codelijst beheerd.
Het project bevat ook tools om deze lijst te beheren en om te zetten naar webformaten.

## Gebruik

### Versienummer in pom.xml bestand
Het version element in het pom.xml bestand bevat het 'SNAPSHOT'-versienummer van de nieuwe release waarop gewerkt wordt.
Voorbeeld: Indien '1.0.0' de huidige release is en '2.0.0' is het versienummer van de nieuwe release waarop gewerkt wordt, dan is '2.0.0-SNAPSHOT' het versienummer in het pom.xml bestand.
### Wijzigingen aan de codelijst doorvoeren
Pas het bron CSV bestand van de codelijst aan (src/source/codelijst_source.csv).

Voorbeeld: Voeg een definitie van een nieuwe code in de codelijst toe.

### Genereer de verspreidingsvormen van de codelijst

#### csv-source naar rdf, jsonld, csv, xsd, excel

```
cd $PROJECT_HOME/src/
node 01_codelijst_skos_from_csv.js
```

Dit script gaat het bron CSV bestand omzetten naar een SKOS lijst in verschillende RDF formaten, een CSV bestand, een excel en een xsd enum.
Het JSONLD context bestand van de codelijst (src/source/context.json) en de yaml (src/source/config.yml) bevatten de mapping van CSV naar RDF.

### Voorbereiding release
#### Valideer het versienummer
Het version element in het pom.xml bestand staat op het 'SNAPSHOT'-versienummer van de komende release.
Voorbeeld: Indien '2.0.0' het versienummer is van de nieuwe release die gemaakt wordt, dan is '2.0.0-SNAPSHOT' het versienummer in het pom.xml bestand.

### Commit/push de wijzigingen
Geef steeds een duidelijke commit boodschap mee (i.e vermelding van de gelogde issues die zijn opgelost).


### run een maven build ( testen worden uitgevoerd en metadata aangemaakt)
```
cd $PROJECT_HOME
mvn clean install
```

## Dependencies

**_nodejs_**

### Installeer nodejs en npm
```
apt-get install node
apt-get install npm
```

### Installeer libraries in package.json
```
cd $PROJECT_HOME
/usr/bin/node /usr/lib/node_modules/npm/bin/npm-cli.js install --scripts-prepend-node-path=auto
```

### Release
Release het overeenkomstige codelijsten project in Bamboo. Zie https://www.milieuinfo.be/bamboo/browse/CODELIJST

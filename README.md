# JokesDB

A minimal application to play with JPA and spring data topics.

## Selbsteinschätzung

Ich finde ich sollte ein 4.5 bekommen denn ich hab die minimalen Anforderungen gemacht zusätzlich habe ich noch einen Zusatz hinzugefügt.

Erfüllten Anforderungen:
- WebFlux-Client-Anbindung an https://jokeapi.dev (read-only, d.h. ohne `/submit`)
- ** min: hardcoded client für ein paar Usecases
- think-tank: generischer Jokes-Client, mehrere Parameter (zB `lang` werden supportet)
- Jokes werden local cached.
- min: Remote Jokes werden in der lokalen DB gespeichert und Duplikate werden verhindert.
- Die Datenbank wird durch sinnvolle Felder erweitert und Jokes können mit einem Sterne-Rating pro User versehen werden.
- ** min: Technische Datenbankfelder creation-timestamp, modified-timestamp (and friends) per Tabelle, joke-ratings
- ** think-tank: Techfelder in einer Basis-Klasse, weitere Columns wie "Category" entsprechend jokeapi.dev
- Verwendet sinnvolle https://projectlombok.org/[Lombok] Features- ** min: keine Getter/Setters in Code
- JUnit Testing mit `@SpringBootTest` und https://assertj.github.io/doc/[AssertJ]- ** min: `WebTestClient` Tests der eigenen Endpunkte
- ** min: README mit einer Selbsteinschätzung, Diskussion der verwendeten Features und wo der Fokus gesetzt wurde.



## Features
- Speichert Witze von der Api in die Datenbank 
- Hat ein Sterne Rating
- Stoppt Duplikate


## Fokus 
Ich setzte mein Fokus auf die Funktionalitäten darum habe ich kein Frontend.


## 🐳 Postgres with Docker

A simple solution expects a https://www.baeldung.com/linux/docker-run-without-sudo[running docker without sudo].
To get a Database connection (and associated JPA-autocomplete), run `./gradlew bootRun` (it will hang).

Alternatively launch a postgres docker container similar to the `dockerPostgres`-Task in `build.gradle` by hand.

## 🪣 IntelliJ Database View

View | Tool Windows | Database | + | Data Source from URL
```
jdbc:postgresql://localhost:5432/localdb
User: localuser, Password: localpass
```

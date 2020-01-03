# softeng2019b Example Project Repository

Σχολή Ηλεκτρολόγων Μηχανικών & Μηχανικών Η/Υ, ΕΜΠ

Παράδειγμα για την εργασία στο μάθημα [Τεχνολογία Λογισμικού](http://courses.softlab.ntua.gr/softeng/2019b/), Χειμερινό εξάμηνο 2019 (softeng19b).

## Δομή φακέλων σε όλα τα projects

Η δομή των φακέλων του παραδείγματος θα πρέπει να ακολουθηθεί και στη δική σας εργασία, ανεξάρτητα του αν θα χρησιμοποιήσετε τις τεχνολογίες του παραδείγματος ή όχι.

* Στον φάκελο `back-end` περιέχεται ο κώδικας του back-end.
* Στον φάκελο `cli-client` περιέχεται ο κώδικας του command line application.
* Στον φάκελο `documentation` περιέχεται η τεκμηρίωση της εργασίας (συνολικά).

## Κύριες τεχνολογίες του παραδείγματος

* [Java 11](https://docs.oracle.com/en/java/javase/11/)
* [Gradle build tool](https://gradle.org/)

### Back-end

* [Restlet framework](https://restlet.talend.com/documentation/tutorials/2.4/overview)
* [MySQL Connector/J](https://dev.mysql.com/doc/connector-j/8.0/en/)
* [Spring JDBC](https://spring.io/guides/gs/relational-data-access/)

### CLI client

* [Picocli](https://picocli.info)

## Γρήγορες οδηγίες

* Εγκατάσταση του Open JDK 11 από [εδώ](https://adoptopenjdk.net/). Για τους linux/macos χρήστες, προτείνετε η χρήση του [sdkman](https://sdkman.io/) για την εγκατάστασή του. Δεν απαιτείται η "χειροκίνητη" εγκατάσταση άλλου λογισμικού (π.χ. Gradle).

* Για την εκτέλεση του back-end:

```bash
cd back-end
./gradlew apprun
```

Το REST API base URL είναι το `https://localhost:8765/energy/api`, όπως απαιτείται από την εργασία. 

* Για την εκτέλεση του cli app:

```bash
cd cli-client
./gradlew installDist
./build/install/energy_TEAM/bin/energy_TEAM -h
./build/install/energy_TEAM/bin/energy_TEAM ActualTotalLoad -h
```

Κατά την ανάπτυξη του cli app, μπορείτε να χρησιμοποιείτε:

```bash
./gradlew run -Pargs="ActualTotalLoad -h"
```

Χρησιμοποιήστε τον editor / IDE της προτίμησής σας για να επεκτείνετε τον κώδικα του παραδείγματος (αν και όπως επιθυμείτε). 

Περισσότερα στο σχετικό φροντιστήριο...

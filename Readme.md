# Zoo Simulator

Diese Java-Anwendung simuliert einen Zoo, in dem Tiere verwaltet werden können. Außerdem können Informationen über die Tiere abgerufen werden.

## Setup Anweisung

- Java 21
- Spring Boot 3.2.5

## H2 Datenbank
Die Anwendung verwendet eine H2-Datenbank, um die Daten zur Laufzeit zu speichern. Die H2-Konsole ist unter http://localhost:8080/h2-console erreichbar. Verwende die folgenden Einstellungen, um mit der Datenbank zu verbinden:
* **JDBC URL**: jdbc:h2:file:/tmp/h2-data/zoo;AUTO_SERVER=TRUE
* **Username**: sa
* **Password**: password

## Endpunkte
Die Endpunkte können mit **Requests.http** getestet werden. Diese Datei enthält vordefinierte HTTP-Anfragen, die mit IntelliJ IDEA oder Visual Studio Code (mit dem REST Client Plugin) ausgeführt werden können.
Um die Anfragen auszuführen, öffne die Datei Requests.http in der IDE und klicke auf dem Play Button.




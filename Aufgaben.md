1 Wo für stet JDBC?

    Java Database Connectivity

2 Welche vier JDBC-Treiber gibt es ?

    - JDBC-ODBC
    - Native Partly
    - JDBC-Net
    - Pure-Java

3 Welche Vorteile/Nachteil bietet die JDBC-ODBC Bridge ?

	- Vorteile:
		- easy to use
		- Einfach mit verschiedenen Datenbanken zu benutzen
	- Nachteile:
		- Performance degraded because JDBC method call is converted into the ODBC function calls.
		- The ODBC driver need to be installed on the client machine

4 Welche Vorteile/Nachteile bietet der Native-Partly-Treiber ?

	- Vorteile:
		- performance upgraded than JDBC-ODBC bridge driver
	- Nachteile:
		- The Native driver needs to be installed on each client machine
		- The Vendor client libary needs to be installed on client machine

5 Welche Vorteile/Nachteile bietet der JDBC-Net-Treiber ?

	- Vorteile:
		- No client side library is required because of application server
		  that can- perform many tasks like auditing, load balancing, logging etc.
	- Nachteile:
		- Network support is required on client machine.
		- Requires database-specific coding to be done middle tier.
		- Maintenace of Network Protocol driver becomes costly because it
 		  requires database-specific coding to be done in the middle tier.

6 Welche Vorteile/Nachteile bietet der Pure-Java-Treiber (thin-driver) ?

	- Vorteile:
		- Completely implemented in Java to achieve platform independence.
		- These drivers don't translate the requests into an intermediary format (such as ODBC)
		- The client application connects directly to the database server. No translation or middleware layers are used, improving performance.
		- The JVM can manage all aspects of the application-to-database connection; this can facilitate debugging.
    - Nachteile:
	    - (Database depence) Drivers are database specific, as different database vendors use widely different (and usually proprietary) network protocols.

7 Welche JDBC-Version unterstützt Java 8?

     ojdbc8

8 Wie lautet der volle Name des JDBC-Treibers für MySQL?

    

9 Wie lautet die DB-URI für den Zugriff auf die DB „BEISPIEL“ auf dem lokalen Rechner?

    jdbc:mysql://localhost:3306/BEISPIEL

10 Welche Fehler können bei einem Verbindungsaufbau über JDBC geworfen werden?

    SQLException

11 Beim Verbindungsaufbau wird gemeldet, dass der Treiber nicht geladen werden kann.
Was könnte hierfür die Ursache sein?

    Das die JAR-Datei nicht im Build-in-Path geladen/ gefunden worden ist.

12 Mit der Klasse DatabaseMetaData können Metadaten der Verbindung ermittelt werden. Suchen Sie
in der API die wichtigsten Infromationen heraus, die Sie damit ermitteln können!

    - 

13 Wie lautet der Aufruf, um ein Query-Statement an die DB zu senden?

    statement.executeQuery(<SQL>);

14 Wie lautet der Aufruf, um ein Update-Statement an die DB zu senden?

    statement.executeUpdate(<SQL>);

15 Sie führen SQL-Statements aus, die Sie aus einer Datei lesen. Wie ermitteln Sie, ob Sie damit ein ResultSet erhalten?

    boolean hasResults = statement.execute(<SQL>)

16 Wie erhalten Sie zusätzliche Informationen über die Abfrage, beispielsweise die Spaltenanzahl?

    ResultSetMetaData meta = rs.getMetaData();
    int colmax = meta.getColumnCount();

17 Mit der Klasse ResultSetMetaData können Metadaten des ResultSets ermittelt werden.
Suchen Sie in der API die wichtigsten Informationen heraus, die Sie damit ermitteln können!
Schreiben Sie sodann ein kleines Programm, welches die wichtigsten Informationen ausgibt.

    

18 Überlegen Sie, warum Sie nicht die Anzahl an Zeilen (Datensätze) eines ResultSets mit einer Methode aus der Klasse ResultSetMetaData ermitteln können.

    

19 Erstellen Sie ein kleines Programm, welches die Struktur der Tabelle TMITGLIED, wie DESC TMITGLIED, auf der Konsole ausgibt.

    

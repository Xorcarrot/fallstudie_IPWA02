# fallstudie_IPWA02
Fallstudie für das Fach: Programmierung von industriellen Informationssysteme mit Java EE

Erste Version meines Projekts.

Die Webanwendung benötigt Maven und PostgreSQL.  

Man kann zwischen vier Rollen auswählen: 
      Sendung erstellen
      Sendung verfolgen
      Kurier
      Manager
      
1. Sendung erstellen:
    In dieser Rolle werden Sendung angelegt und der dazugehörige Empfänger erstellt. Die eingegeben Daten werden 
    auf korrektheit überprüft und dann der Datenbank hinzugefügt. Bei erfolgreicher Eingabe wird die Referenznummer, 
    die Gesamtkosten und die Anzahl der benötigten Briefmarken ausgegeben. 
2. Sendung verfolgen:
    Diese Rolle gibt die Möglichkeit, über die Referenznummer, den aktullen Status seiner Sendung zu kontrollieren. 
3. Kurier:
    Als Kurier bekommt man eine Tabelle, sortiert nach aktuellen Status der Sendungen. Auch gibt es die Möglichkeit den 
    Status einer Sendung in den folgenden Status weiter zu schalten, bis die Sendung als zugestellt markiert ist. 
4. Manager: 
    Der Manager bekommt eine gesamte Übersicht, auf die in der Datenbank vorhandenen Sendungen und deren Status. 
    
In PostgreSQL können die Tables altenativ auch von der Webanwendung generiert werden, es muss nur im xml Document 
hibernate.cfg.xml die property <property name="hbm2ddl.auto">update</property> auf <property name="hbm2ddl.auto">create</property>
gesetzt werden. Nach dem erstellen aber wieder auf update stellen, sonst funktionier die Anwendung nicht.

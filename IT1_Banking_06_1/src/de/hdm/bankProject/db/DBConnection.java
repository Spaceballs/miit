package de.hdm.bankProject.db;

import java.sql.*;

/**
 * Verwalten einer Verbindung zur Datenbank.<p>
 * <b>Vorteil:</b> Sehr einfacher Verbindungsaufbau zur Datenbank.<p>
 * <b>Nachteil:</b> Durch die Singleton-Eigenschaft der Klasse kann nur auf eine
 * fest vorgegebene Datenbank zugegriffen werden.<p>
 * In der Praxis kommen die meisten Anwendungen mit einer einzigen Datenbank 
 * aus. Eine flexiblere Variante fuer mehrere gleichzeitige Datenbank-Verbindungen
 * waere sicher leistungsfaehiger. Dies wuerde allerdings den Rahmen dieses
 * Projekts sprengen bzw. die Software unnoetig verkomplizieren, da dies fuer diesen
 * Anwendungsfall nicht erforderlich ist.
 * 
 * @author Thies
 */
public class DBConnection {

    /**
     * Die Klasse DBConnection wird nur einmal instantiiert. Man spricht hierbei
     * von einem sogenannten <b>Singleton</b>.<p>
     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal fuer
     * saemtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
     * einzige Instanz dieser Klasse.
     *
     * @see AccountMapper.accountMapper()
     * @see CustomerMapper.customerMapper()
     */
    private static Connection con = null;
    /**
     * Der Klassenname des Datenbanktreibers. Wir verwenden hier ein vollständig
     * in Java implementiertes, relationales Datenbanksystem namens Derby der Apache-
     * Organisation.
     */
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    /**
     * Der Name der Datenbank. Diese Datenbank wird in Derby mit Hilfe von Dateien
     * im Dateisystem realisiert. Der Name der Datenbank ist gleichzeit der Name
     * des Verzeichnisses, in dem sich die Datenbankdateien befinden.
     */
    private static String dbName = "banking";
    /**
     * Die URL, mit deren Hilfe die Datenbank angesprochen wird.
     */
    private static String connectionUrl = "jdbc:derby:" + dbName;

    /**
     * Diese statische Methode kann aufgrufen werden durch
     * <code>DBConnection.connection()</code>. Sie stellt die
     * Singleton-Eigenschaft sicher, indem Sie dafuer sorgt, dass nur eine einzige
     * Instanz von <code>DBConnection</code> existiert.<p>
     *
     * <b>Fazit:</b> DBConnection sollte nicht mittels <code>new</code>
     * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.<p>
     *
     * <b>Nachteil:</b> Bei Zusammenbruch der Verbindung zur Datenbank - dies kann
     * z.B. durch ein unbeabsichtigtes Herunterfahren der Datenbank ausgeloest
     * werden - wird keine neue Verbindung aufgebaut, so dass die in einem solchen
     * Fall die gesamte Software neu zu starten ist. In einer robusten Loesung
     * wuerde man hier die Klasse dahingehend modifizieren, dass bei einer nicht
     * mehr funktionsfuehigen Verbindung stets versucht wuerde eine neue Verbindung
     * aufzubauen. Dies wuerde allerdings ebenfalls den Rahmen dieses Projekts
     * sprengen.
     *
     * @return DAS <code>DBConncetion</code>-Objekt.
     * @see con
     */
    public static Connection connection() {
        // Wenn es bisher keine Conncetion zur DB gab, ...
        if (con == null) {
            try {
                // Ersteinmal muss der passende DB-Treiber geladen werden
                Class.forName(driver);

                /*
                 * Dann erst kann uns der DriverManager eine Verbindung mit den oben
                 * in der Variable url angegebenen Verbindungsinformationen aufbauen.
                 *
                 * Diese Verbindung wird dann in der statischen Variable con
                 * abgespeichert und fortan verwendet.
                 */
                con = DriverManager.getConnection(connectionUrl);
            } catch (SQLException e1) {
                con = null;
                e1.printStackTrace();
            } catch (ClassNotFoundException e) {
                con = null;
                e.printStackTrace();
            }
        }

        // Zurueckgegeben der Verbindung
        return con;
    }
}

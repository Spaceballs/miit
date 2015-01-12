package de.hdm.bankProject.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.bankProject.data.*;

/**
 * Mapper-Klasse, die <code>Customer</code>-Objekte auf eine relationale 
 * Datenbank abbildet.
 * Hierzu wird eine Reihe von Methoden zur Verfuegung gestellt, mit deren Hilfe
 * z.B. Objekte gesucht, erzeugt, modifiziert und geloescht werden koennen.
 * Das Mapping ist bidirektional. D.h., Objekte koennen in DB-Strukturen
 * und DB-Strukturen in Objekte umgewandelt werden.<p>
 * 
 * <b>Hinweis:</b> Diese Klasse ist analog zur Klasse <code>AccountMapper</code>
 * implementiert.
 * 
 * @see AccountMapper
 * @author Thies
 */
public class CustomerMapper extends DataMapper {

    /**
     * Die Klasse CustomerMapper wird nur einmal instantiiert. Man spricht hierbei
     * von einem sogenannten <b>Singleton</b>.<p>
     * Diese Variable ist durch den Bezeichner <code>static</code> nur einmal fuer
     * saemtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert die
     * einzige Instanz dieser Klasse.
     *
     * @see accountMapper()
     */
    private static CustomerMapper customerMapper = null;

    /**
     * Geschuetzter Konstruktor - verhindert die Moeglichkeit, mit new neue
     * Instanzen dieser Klasse zu erzeugen.
     *
     */
    protected CustomerMapper() {
    }

    /**
     * Diese statische Methode kann aufgrufen werden durch
     * <code>CustomerMapper.customerMapper()</code>. Sie stellt die
     * Singleton-Eigenschaft sicher, indem Sie dafuer sorgt, dass nur eine einzige
     * Instanz von <code>CustomerMapper</code> existiert.<p>
     *
     * <b>Fazit:</b> CustomerMapper sollte nicht mittels <code>new</code>
     * instantiiert werden, sondern stets durch Aufruf dieser statischen Methode.
     *
     * @return DAS <code>CustomerMapper</code>-Objekt.
     * @see customerMapper
     */
    public static CustomerMapper customerMapper() {
        if (customerMapper == null) {
            customerMapper = new CustomerMapper();
        }
        return customerMapper;
    }

    /**
     * Suchen eines Kunden mit vorgegebener Kundennummer. Da diese eindeutig ist,
     * wird genau ein Objekt zurueckgegeben.
     *
     * @param id Primaerschluesselattribut (->DB)
     * @return Kunden-Objekt, das dem uebergebenen Schluessel entspricht, null bei
     * nicht vorhandenem DB-Tupel.
     */
    public Customer findByKey(int id) {
        // auf existierendem Customer prüfen
        Customer c = (Customer) this.checkForObject(id);
        if (c != null) {
            return c;
        }
        // DB-Verbindung holen
        Connection con = DBConnection.connection();
        try {
            // Leeres SQL-Statement (JDBC) anlegen
            Statement stmt = con.createStatement();
            // Statement ausfuellen und als Query an die DB schicken
            ResultSet rs = stmt.executeQuery(
                    "SELECT id, firstName, lastName FROM customers " +
                    "WHERE id=" + id +
                    " ORDER BY lastName");
            /*
             * Da id Primaerschluessel ist, kann max. nur ein Tupel zurueckgegeben werden.
             * Pruefe, ob ein Ergebnis vorliegt.
             */
            if (rs.next()) {
                // Ergebnis-Tupel in Objekt umwandeln
                c = new Customer();
                c.setId(rs.getInt("id"));
                c.setFirstName(rs.getString("firstName"));
                c.setLastName(rs.getString("lastName"));
                // wir merken uns das Customer-Objekt im Cache
                this.rememberObject(id, c);
                return c;
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
            return null;
        }
        return null;
    }

    /**
     * Auslesen aller Kunden.
     *
     * @return Ein Vektor mit Customer-Objekten, die saemtliche Kunden
     * repraesentieren. Bei evtl. Exceptions wird ein partiell gefuellter oder ggf.
     * auch leerer Vetor zurueckgeliefert.
     */
    public Vector<Customer> findAll() {
        Connection con = DBConnection.connection();
        // Ergebnisvektor vorbereiten
        Vector<Customer> result = new Vector<Customer>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT id, firstName, lastName " +
                    "FROM customers " +
                    "ORDER BY lastName");
            // Fuer jeden Eintrag im Suchergebnis wird nun ein Customer-Objekt erstellt.
            while (rs.next()) {
                int id = rs.getInt("id");
                Customer c = (Customer) this.checkForObject(id);
                if (c == null) {
                    c = new Customer();
                    c.setId(id);
                    c.setFirstName(rs.getString("firstName"));
                    c.setLastName(rs.getString("lastName"));
                    // wer merken uns das Customer-Objekt im Cache
                    this.rememberObject(id, c);
                }
                // Hinzufuegen des neuen Objekts zum Ergebnisvektor
                result.addElement(c);
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        // Ergebnisvektor zurueckgeben
        return result;
    }

    /**
     * Auslesen aller Kunden-Objekte mit gegebenem Nachnamen
     *
     * @param name Nachname der Kunden, die ausgegeben werden sollen
     * @return Ein Vektor mit Customer-Objekten, die saemtliche Kunden mit dem
     * gesuchten Nachnamen repraesentieren. Bei evtl. Exceptions wird ein partiell
     * gefuellter oder ggf. auch leerer Vetor zurueckgeliefert.
     */
    public Vector<Customer> findByLastName(String name) {
        Connection con = DBConnection.connection();
        Vector<Customer> result = new Vector<Customer>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT id, firstName, lastName " +
                    "FROM customers " +
                    "WHERE lastName LIKE '" + name +
                    "' ORDER BY lastName");
            // Fuer jeden Eintrag im Suchergebnis wird nun ein Customer-Objekt erstellt.
            while (rs.next()) {
                int id = rs.getInt("id");
                Customer c = (Customer) this.checkForObject(id);
                if (c == null) {
                    c = new Customer();
                    c.setId(id);
                    c.setFirstName(rs.getString("firstName"));
                    c.setLastName(rs.getString("lastName"));
                    // wir merken uns das Customer-Objekt im Cache
                    this.rememberObject(id, c);
                }
                // Hinzufuegen des neuen Objekts zum Ergebnisvektor
                result.addElement(c);
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        // Ergebnisvektor zurueckgeben
        return result;
    }

    /**
     * Einfuegen eines <code>Customer</code>-Objekts in die Datenbank. Dabei wird
     * auch der Primaerschluessel des uebergebenen Objekts geprueft und ggf.
     * berichtigt.
     *
     * @param c das zu speichernde Objekt
     * @return das bereits uebergebene Objekt, jedoch mit ggf. korrigierter
     * <code>id</code>.
     */
    public Customer insert(Customer c) {
        Connection con = DBConnection.connection();
        try {
            Statement stmt = con.createStatement();
            /*
             * Zunaechst schauen wir nach, welches der momentan hoechste
             * Primaerschluesselwert ist.
             */
            ResultSet rs = stmt.executeQuery(
                    "SELECT MAX(id) AS maxid " +
                    "FROM customers ");
            // Wenn wir etwas zurueckerhalten, kann dies nur einzeilig sein
            if (rs.next()) {
                /*
                 * c erhaelt den bisher maximalen, nun um 1 inkrementierten
                 * Primaerschluessel.
                 */
                c.setId(rs.getInt("maxid") + 1);
                stmt = con.createStatement();
                // Jetzt erst erfolgt die tatsaechliche Einfuegeoperation
                stmt.executeUpdate("INSERT INTO customers (id, firstName, lastName) " +
                        "VALUES (" +
                        c.getId() + ",'" +
                        c.getFirstName() + "','" +
                        c.getLastName() + "')");
                // Anschließend merken wir uns das Customer-Objekt im Cache
                this.rememberObject(c.getId(), c);
            }
        } catch (SQLException e2) {
            e2.printStackTrace();
        }

        /*
         * Rueckgabe, des evtl. korrigierten Customers.
         *
         * HINWEIS: Da in Java nur Referenzen auf Objekte und keine physischen
         * Objekte uebergeben werden, waere die Anpassung des Customer-Objekts auch
         * ohne diese explizite Rueckgabe ausserhalb dieser Methode sichtbar.
         * Die explizite Rueckgabe von c ist eher ein Stilmittel, um zu signalisieren,
         * dass sich das Objekt evtl. im Laufe der Methode veraendert hat.
         */
        return c;
    }

    /**
     * Wiederholtes Schreiben eines Objekts in die Datenbank.
     *
     * @param c das Objekt, das in die DB geschrieben werden soll
     * @return das als Parameter uebergebene Objekt
     */
    public Customer update(Customer c) {
        Connection con = DBConnection.connection();
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE customers " +
                    "SET firstName='" + c.getFirstName() + "', " +
                    "lastName='" + c.getLastName() + "' " +
                    "WHERE id=" + c.getId());
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        // falls das Objekt noch nicht im Cache ist (sollte nicht passieren), wird
        // es dort vermerkt.
        if (c!=this.checkForObject(c.getId())) this.rememberObject(c.getId(), c);
        // Um Analogie zu insert(Customer c) zu wahren, geben wir c zurueck
        return c;
    }

    /**
     * Loeschen der Daten eines <code>Customer</code>-Objekts aus der Datenbank.
     *
     * @param c das aus der DB zu loeschende "Objekt"
     */
    public void delete(Customer c) {
        Connection con = DBConnection.connection();
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM customers " +
                    "WHERE id=" + c.getId());
            // Löschen aus dem Cache
            this.removeObject(c.getId());
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }

    /**
     * Auslesen der zugehoerigen <code>Account</code>-Objekte zu einem gegebenen
     * Kunden.
     *
     * @param c der Kunde, dessen Konten wir auslesen moechten
     * @return ein Vektor mit saemtlichen Konto-Objekten des Kunden
     */
    public Vector<Account> getAccountsOf(Customer c) {
        /*
         * Wir bedienen uns hier einfach des AccountMapper. Diesem geben wir
         * einfach den in dem Customer-Objekt enthaltenen Primaerschluessel.Der
         * CustomerMapper loest uns dann diese ID in eine Reihe von Konto-Objekten
         * auf.
         */
        return AccountMapper.accountMapper().findByOwner(c);
    }
}

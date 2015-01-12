package de.hdm.bankProject;

import java.util.Vector;

import de.hdm.bankProject.data.*;
import de.hdm.bankProject.db.*;

/**
 * <b>Frage:</b> Warum werden diese Methoden nicht als Teil der Klassen
 * <code>Bank</code> oder <code>Customer</code> implementiert?<br>
 * <b>Antwort:</b> Z.B. das Loeschen eines Kunden erfordert Kenntnisse ueber die
 * Verflechtung eines Kunden mit Konto-Objekten. Um die Klasse <code>Bank</code>
 * bzw. <code>Customer</code> nicht zu stark an andere Klassen zu koppeln, wird
 * das Wissen darueber, wie einzelne "Daten"-Objekte koexistieren, in der
 * vorliegenden Klasse gekapselt.
 * @author Thies
 */
public class BankAdministration {

    /**
     * Referenz auf das zugehoerige Bank-Objekt.
     */
    private Bank bank = null;
    /**
     * Referenz auf den DatenbankMapper, der Kundenobjekte mit der Datenbank
     * abgleicht.
     */
    private CustomerMapper cMapper = null;
    /**
     * Referenz auf den DatenbankMapper, der Kontoobjekte mit der Datenbank
     * abgleicht.
     */
    private AccountMapper aMapper = null;

    /**
     * Konstruktor. Empfaengt als Parameter die Bank, die zu verwalten ist sowie
     * ein <code>CustomerMapper</code>-Objekt und ein
     * <code>AccountMapper</code>-Objekt zwecks Datenbankanbindung.
     */
    public BankAdministration() {
        this.cMapper = CustomerMapper.customerMapper();
        this.aMapper = AccountMapper.accountMapper();
        this.bank = new Bank();
        bank.setId(76543210);
        bank.setName("SparNix Bank AG");
        bank.setCity("Stuttgart");
        bank.setStreet("Wolframstrasse");
        bank.setZip(70191);
        bank.setAccounts(this.getAllAccounts());
    }

    /**
     * Ein neues Konto fuer einen gegebenen Kunden eroeffnen.
     *
     * @param c der Kunde
     * @return fertiges Konto-Objekt
     * Das Anlegen eines neuen Kontos fuer den uebergebenen Kunden fuehrt implizit
     * zu einem Speichern des neuen, leeren Kontos in der Datenbank.<p>
     *
     * <b>HINWEIS:</b> Aenderungen an Account-Objekten muessen stets durch Aufruf von
     * <code>save(Account a)</code> in die Datenbank transferiert werden.
     *
     * @see save(Account a)
     * @see save(Customer c)
     */
    public Account createAccountFor(Customer c) {
        Account a = new Account();
        a.setOwner(c);
        /*
         * Setzen einer vorlaeufigen Kontonr. Der insert-Aufruf liefert dann ein
         * Objekt, dessen Nummer mit der Datenbank konsistent ist.
         */
        a.setId(1);
        // Objekt in der DB speichern.
        a = this.aMapper.insert(a);
        bank.setAccounts(getAllAccounts());
        return a;
    }

    /**
     * Einen Kunden anlegen.
     *
     * @param first Vorname
     * @param last Nachname
     * @return Ein fertiges Kunden-Objekt.
     *
     * Das Anlegen eines neuen Kunden fuehrt implizit
     * zu einem Speichern des neuen Kunden in der Datenbank.<p>
     *
     * <b>HINWEIS:</b> Aenderungen an Customer-Objekten muessen stets durch Aufruf von
     * <code>save(Customer c)</code> in die Datenbank transferiert werden.
     *
     * @see save(Customer c)
     * @see save(Account a)
     */
    public Customer createCustomer(String first, String last) {
        Customer c = new Customer();
        c.setFirstName(first);
        c.setLastName(last);
        /*
         * Setzen einer vorlaeufigen Kundennr. Der insert-Aufruf liefert dann ein
         * Objekt, dessen Nummer mit der Datenbank konsistent ist.
         */
        c.setId(1);

        // Objekt in der DB speichern.
        return this.cMapper.insert(c);
    }

    /**
     * Ein Konto loeschen.
     * @param a zu loeschendes Konto
     */
    public void deleteAccount(Account a) {
        // Account aus der DB entfernen
        this.aMapper.delete(a);
    }

    /**
     * Einen Kunden loeschen.
     * @param c zu loeschender Kunde
     */
    public void deleteCustomer(Customer c) {

        // Zunaechst saemtl. Konten des Kunden entfernen.
        Vector<Account> accounts = this.getAccountsOf(c);
        for (int i=0; i<accounts.size(); i++) {
            this.deleteAccount(accounts.elementAt(i));
        }
        // Anschliessend den Kunden entfernen
        this.cMapper.delete(c);
    }

    /**
     * Auslesen der zugeordneten Bank.
     * @return Bank-Objekt
     */
    public Bank getBank() {
        return this.bank;
    }

    /**
     * Konten eines Kunden auslesen.
     * @param Kundenobjekt
     * @return Vector-Objekt mit Account-Objekten bzgl. des Kunden.
     */
    public Vector<Account> getAccountsOf(Customer c) {

        return this.aMapper.findByOwner(c);
    }

    /**
     * Suchen von Customer-Objekten, von denen der Zuname bekannt ist.
     * @param lastName ist der Nachname.
     * @return Alle Customer-Objekte, die die Suchkriterien erfuellen.
     */
    public Vector<Customer> getCustomerByName(String lastName) {

        return this.cMapper.findByLastName(lastName);
    }

    /**
     * Suchen eines Customer-Objekts, dessen Kundennummer bekannt ist.
     * @param id ist die Kundennummer.
     * @return Das erste Customer-Objekt, dass den Suchkriterien entspricht.
     */
    public Customer getCustomerById(int id) {

        return this.cMapper.findByKey(id);
    }

    /**
     * Saemtliche Kunden der Bank auslesen.
     * @return Vector saemtlicher Kunden
     */
    public Vector<Customer> getAllCustomers() {
        return this.cMapper.findAll();
    }

    /**
     * Saemtliche Konten der Bank auslesen.
     * @return Vector saemtlicher Konten
     * @author Thies
     */
    public Vector<Account> getAllAccounts() {
        return this.aMapper.findAll();
    }

    /**
     * Alle Kunden mit allen Konten.
     * @return ein Vektor, dessen Elemente Vektoren sind. Jeder Teilvektor besteht aus
     * einem Kundenobjekt an Position 0 und den zugehoerenden Kontenobjekten ab Position 1.
     */
    public Vector getAllCustomersAndAccounts() {
        Vector customers = null, accounts = null, customersAndAccounts = new Vector();
        Customer c;
        // alle Kunden
        customers = getAllCustomers();
        for (int i = 0; i < customers.size(); i++) {
            c = (Customer) customers.elementAt(i);
            // alle Konten eines Kunden
            accounts = getAccountsOf(c);
            // Kundenobjekt wird als erstes Element hinzugefuegt
            accounts.add(0, c);
            customersAndAccounts.add(accounts);
        }
        return customersAndAccounts;
    }

    /**
     * Der Kontostand wird um den angegebenen Betrag veraendert, wenn
     * dadurch die Kreditlinie nicht unterschritten wird.
     * @param a
     * @param amount
     * @return der neue Betrag als float
     */
    public double modifyAccount(Account a, double amount) {
        if (amount < 0) {
            if (!a.isOverdrawAmount(-amount)) {
                a.makeWithdrawal(-amount);
            }
        } else {
            a.makeDeposit(amount);
        }
        save(a);
        return a.getBalance();
    }

    /**
     * Verwendung des Aufrufs von <code>book</code> für Account-Objekte
     * @param a
     * @param amount
     * @param text
     * @return
     */
    public double bookToAccount(Account a, double amount, String text) {
        if (!((amount < 0) && a.isOverdrawAmount(amount))) {
            a.book(amount, text);
        }
        return a.getCurrentBalance();
    }

    /**
     * Aktualisierung des Kontostands durch Verarbeitung der Transaktionen
     * @param a
     */
    public void updateAccountBalance(Account a) {
    	a.updateBalance();
    	save(a);
    }

    /**
     * Speichern eines Account-Objekts in der Datenbank.
     * @param a zu sicherndes Objekt.
     */
    public void save(Account a) {
        aMapper.update(a);
    }

    /**
     * Vor- und Nachname eines Kunden werden aktualisiert.
     * @param c
     * @param vorname
     * @param nachname
     * @return das modifizierte Kundenobjekt.
     */
    public Customer modifyCustomer(Customer c, String vorname, String nachname) {
        c.setFirstName(vorname);
        c.setLastName(nachname);
        save(c);
        return c;
    }

    /**
     * Speichern eines Customer-Objekts in der Datenbank.
     * @param c zu sicherndes Objekt.
     */
    public void save(Customer c) {
        cMapper.update(c);
    }

    /**
     * Verändern der Kreditlinie eines Kontos.
     * Falls der Kontostand unter die neue Kreditlinie fallen würde,
     * wird die Kreditlinie nicht verändert.
     */

    public void changeCreditLine(Account a, double creditLine){
        if (!(a.getBalance()<creditLine)) {
            a.setCreditLine(creditLine);
        }
    }
}

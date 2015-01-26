package de.hdm.bankProject.data;

import java.util.Vector;

/**
 * Implementierungsklasse des Interface <code>Bank</code>.
 * @see Bank
 * @author Thies
 */
public class Bank implements Cloneable {

    /**
     * Bankleitzahl
     */
    private int id = 0;
    /**
     * Name der Bank
     */
    private String name = "";
    /**
     * Strasse und Hausnummer der Bank.
     */
    private String street = "";
    /**
     * Postleitzahl der Bank.
     */
    private int zip = 0;
    /**
     * Ortsbezeichnung der Bank.
     */
    private String city = "";
    /**
     * Array für die Accounts der Bank
     */
    private Account[] accounts = null;

    public Bank() {
        super();
    }

    public Bank(String name) {
        this.name = name;
    }

    /**
     * Auslesen der Bankleitzahl.
     * @return Bankleitzahl, ganzzahlig
     */
    public int getId() {
        return id;
    }

    /**
     * Setzen der Bankleitzahl. <br>
     * <b>Hinweis:</b> Sollte nur von Verwaltungsklassen genutzt werden!
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Ort auslesen, an dem sich die Bank befindet.
     * @return Ort als Zeichenkette
     */
    public String getCity() {
        return city;
    }

    /**
     * Namen der Bank auslesen.
     * @return Name als Zeichenkette.
     */
    public String getName() {
        return name;
    }

    /**
     * Strasse inkl. Hausnummer auslesen, in der sich die Bank befindet.
     * @return Strasse und Hausnumer als Zeichenkette.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Postleitzahl des Bankorts.
     * @return Postleitzahl, ganzzahlig
     */
    public int getZip() {
        return zip;
    }

    /**
     * Ort der Bank setzen.
     * @param Ort als Zeichenkette
     */
    public void setCity(String string) {
        city = string;
    }

    /**
     * Namen der Bank setzen.
     * @param Name als Zeichenkette.
     */
    public void setName(String string) {
        name = string;
    }

    /**
     * Strasse und Hausnummer setzen, in der sich die Bank befindet.
     * @param Zeichenkette, die Strasse und Hausnummer enthaelt.
     */
    public void setStreet(String string) {
        street = string;
    }

    /**
     * Postleitzahl setzen des Bankorts setzen.
     * @param Postleitzahl, ganzzahlig
     */
    public void setZip(int i) {
        zip = i;
    }

    /**
     * Gibt das Acocunt Array zurueck
     * @return Account[]
     */
    public Account[] getAccounts() {
        return accounts;
    }

    /**
     * liefert das Konto mit vorgegebener Kontonummer
     * @param accountNumber
     * @return
     */
    public Account getAccount(int accountNumber) {
        for (int i = 0; i < accounts.length; i++) {
            if (accounts[i].getId() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }

    /**
     * Befuellt das Accounts Array moit den Accounts
     * @param accounts
     */
    public void setAccounts(Vector<Account> accounts) {
        if (this.accounts == null) {
            this.accounts = new Account[accounts.size()];
        }
        accounts.toArray(this.accounts);
    }

    /**
     * Hinzufügen eines Accounts. Die vorhandenen werden in ein neues Feld
     * uebertragen, dessen Elementanzahl um 1 erhoeht wird. Das neue Konto
     * wird zum Wert des letzten Elements.
     * @param a
     */
    public void addAccount(Account a) {
        Account[] tmpAccounts;
        if (accounts == null) {
            tmpAccounts = new Account[1];
        } else {
            tmpAccounts = new Account[accounts.length + 1];
            for (int i = 0; i < accounts.length; i++) {
                tmpAccounts[i] = accounts[i];
            }
        }
        tmpAccounts[(tmpAccounts.length) - 1] = a;
        accounts = tmpAccounts;
    }

    /**
     * Gibt Informationen über alle Konten auf dem Bildschirm aus
     */
    public void printAccounts() {
        System.out.println(this.name);
        for (int i = 0; i < accounts.length; i++) {
            System.out.println("Name : " + accounts[i].getOwner().getFirstName() + " " + accounts[i].getOwner().getLastName() + "     KTNO : " + accounts[i].getId() + "     Kontostand : " + accounts[i].getBalance());
        }
        System.out.println("");
    }

    /**
     * Sortiert das Account Array nach den Kontostaenden
     */
    public void sortAccounts() {
        for (int i = this.accounts.length; --i >= 0;) {
            for (int j = 0; j < i; j++) {
                if (this.accounts[j].getBalance() >
                        this.accounts[j + 1].getBalance()) {
                    Account temp = this.accounts[j];
                    this.accounts[j] = this.accounts[j + 1];
                    this.accounts[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

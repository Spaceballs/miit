package de.hdm.bankProject.data;

/**
 * Implementierungsklasse des Interface <code>Bank</code>.
 * @see Bank
 * @author Thies
 */
public class Bank {

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

    public Bank() {
        super();
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
}

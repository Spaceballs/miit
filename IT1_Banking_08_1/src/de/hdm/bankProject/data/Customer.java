package de.hdm.bankProject.data;

/**
 * Klasse der Bankkunden
 * @author Thies
 */
public class Customer {

    /**
     * Nachname des Kunden
     */
    private String lastName = "";
    /**
     * Vorname des Kunden
     */
    private String firstName = "";
    /**
     * Kundennummer des Kunden
     */
    private int id = 0;

    public Customer(){}

    public Customer(String firstname, String lastname) {
        this.firstName = firstname;
        this.lastName = lastname;
    }

    /**
     * Vornamen auslesen
     * @return Vorname
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Auslesen der Kundennummer.
     * @return Kundennummer
     */
    public int getId() {
        return id;
    }

    /**
     * Nachnamen auslesen.
     * @return Nachname
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Vornamen setzen.
     * @param first Vorname
     */
    public void setFirstName(String string) {
        firstName = string;
    }

    /**
     * Kundennummer setzen.
     * @param theId Kundennummer
     */
    public void setId(int i) {
        id = i;
    }

    /**
     * Nachnamen setzen.
     * @param first Nachname
     */
    public void setLastName(String string) {
        lastName = string;
    }

    public String toString(){
        return this.firstName + " " + this.lastName;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Customer) {
            Customer c = (Customer) o;
            if (c.getId() == this.id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.lastName != null ? this.lastName.hashCode() : 0);
        hash = 79 * hash + (this.firstName != null ? this.firstName.hashCode() : 0);
        hash = 79 * hash + this.id;
        return hash;
    }
}

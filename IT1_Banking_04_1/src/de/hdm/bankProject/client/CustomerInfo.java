/*
 * Created on 11.12.2004
 *
 */
package de.hdm.bankProject.client;

import de.hdm.bankProject.data.*;

/**
 * Objekte dieser Klasse werden in der Baumdarstellung von Kunden und Konten zur
 * Darstellung von Customers verwendet. Sie enthalten ausserdem das zugehoerige Customer-
 * Objekt.
 * @author Christian Rathke
 */
public class CustomerInfo {

    private String firstname;
    private String lastname;
    private Customer c;

    CustomerInfo(Customer c) {
        this.c = c;
        firstname = c.getFirstName();
        lastname = c.getLastName();
    }

    /**
     * Die String-Darstellung eines Kunden besteht aus Nachname und Vorname.
     */
    @Override
    public String toString() {
        return lastname + ", " + firstname;
    }

    public Customer getCustomer() {
        return c;
    }
}

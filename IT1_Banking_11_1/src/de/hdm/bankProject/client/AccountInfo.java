/*
 * Created on 11.12.2004
 *
 */
package de.hdm.bankProject.client;

import de.hdm.bankProject.data.*;

/**
 * Objekte dieser Klasse werden in der Baumdarstellung von Kunden und Konten zur
 * Darstellung von Accounts verwendet. Sie enthalten ausserdem das zugehoerige Account-
 * Objekt.
 * @author Christian Rathke
 */
public class AccountInfo {

    private int id;
    private Account a;

    AccountInfo(Account a) {
        this.a = a;
        id = a.getId();
    }

    /**
     * String-Darstellung eines Accounts besteht aus dem String "Konto " und
     * der Konto-Id.
     */
    @Override
    public String toString() {
        return "Konto " + id;
    }

    public Account getAccount() {
        return a;
    }
}

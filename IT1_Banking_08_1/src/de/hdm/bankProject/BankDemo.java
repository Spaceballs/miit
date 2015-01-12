package de.hdm.bankProject;

import de.hdm.bankProject.data.Account;
import de.hdm.bankProject.data.Bank;
import de.hdm.bankProject.data.Customer;

/**
 *
 * @author rathke
 */
public class BankDemo {

    public static void main(String[] args) {

      /**
         * Uebung 08 Aufgabe 1
         * Beheben Sie den Fehler im Code!
         */
        Account myFirstAccount;
        myFirstAccount.setOwner(new Customer("Sina", "Sappel"));
        System.out.println("Konteneigentuemer : " + myFirstAccount.getOwner());
        System.out.println("Kontonummer       : " + myFirstAccount.getId());
        System.out.println("Kreditrahmen      : " + myFirstAccount.getCreditLine());
        System.out.println("Zinssatz          : " + myFirstAccount.getInterest());
        System.out.println("Kontostand        : " + myFirstAccount.getCurrentBalance());

        /**
         * Uebung 08 Aufgabe 2
         * Welche Objekte koennen nach Ausfuehrung des folgenden Codes vom
         * Garbage Collector geloescht werden? Begruenden Sie!
         */
        Bank myFirstBank = new Bank("Spar Nix Bank AG");
        Account mySecondAccount = new Account(new Customer("Franz", "Freund"), 2345);
        myFirstBank.addAccount(mySecondAccount);
        mySecondAccount = null;

        /**
         * Uebung 08 Aufgabe 4
         * Welche Fehler enthaelt der folgende Code?
         */
        Account myThirdAccount = new Account(new Customer("Bodo", "Bank"), 2346);
        myThirdAccount.updateBalance = 5678.90;
        double myBalance = myThirdAccount.getBalance;

        /**
         * Uebung 08 Aufgabe 5
         * Welchen Wert ergibt der folgende Ausdruck:
         * myFourthAccount == myFifthAccount?
         * Begruenden Sie!
         */
        Customer paulAhner = new Customer("Paul", "Ahner");
        Account myFourthAccount = new Account(paulAhner, 2000);
        Account myFifthAccount  = new Account(paulAhner, 2000);
        System.out.println(myFourthAccount == myFifthAccount);

        /**
         * Uebung 08 Aufgabe 6
         * Fuegen Sie bitte hier Ihren eigenen Code ein:
         */
    }
}

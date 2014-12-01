package de.hdm.bankProject.data;

/**
 * Klasse der Bankkonten
 * @author Thies
 */
public class Account {

    /**
     * Kontonummer
     */
    private int id = 0;
    /**
     * Kontostand
     */
    private double balance = 0;
    /**
     * Zinssatz
     */
    private float interestRate = 5.0f;
    /**
     * Überziehungsbetrag
     */
    private double creditLine = -1600.0;
    /**
     * Kontoinhaber
     */
    private Customer owner = null;
    /**
     * Transaktionen-Speicher
     */
    private Transaction[] transactions = new Transaction[50];
    /**
     * Transactionpointer
     */
    private int transactionPointer = 0;

    /**
     * Auslesen des Kontostands.
     * @return Kontostand
     */
    public double getBalance() {
        return this.balance;
    }

    /**
     * Auslesen der Kontonummer.
     * @return Kontonummer, ganzzahlig
     */
    public int getId() {
        return this.id;
    }

    /**
     * Auslesen des Kontoinhabers.
     * @return Kontoinhaber-Instanz
     * @see Customer
     */
    public Customer getOwner() {
        return owner;
    }

    /**
     * Setzen des Kontostands
     */
    public void setBalance(double b) {
        this.balance = b;
    }

    /**
     * Setzen der Kontonummer. <br>
     * <b>Hinweis:</b> Sollte nur von Verwaltungsklassen genutzt werden!
     * @param i
     * @see setOwner(Customer customer)
     */
    public void setId(int i) {
        this.id = i;
    }

    /**
     * Setzen des Kontoinhabers. <br>
     * <b>Hinweis:</b> Sollte nur von Verwaltungsklassen genutzt werden!
     * @param c
     * @see setId(int i)
     */
    public void setOwner(Customer c) {
        this.owner = c;
    }

    /**
     * Eine Einzahlung bzw. Gutschrift fuer das Konto buchen.
     * @param amount
     * @see makeWithdrawal(int amount)
     */
    public void makeDeposit(double amount) {
        this.balance = this.balance + amount;
    }

    /**
     * Eine Auszahlung bzw. Belastung des Kontos buchen.
     * @param amount
     * @see makeDeposit(int amount)
     */
    public void makeWithdrawal(double amount) {
        this.balance = this.balance - amount;
    }

    /**
     * Berechnen des Zinsertrags.
     * @return Zinsertrag
     */
    public double getInterest() {
        return balance * (interestRate / 100);
    }

    /**
     * Rückgabe der Kreditlinie
     */
    public double getCreditLine() {
        return creditLine;
    }

    /**
     * Setzen der Kreditlinie.
     */
    public void setCreditLine(double creditLine) {
        this.creditLine = creditLine;
    }

    /**
     * Boolsche Methode zum Abprüfen eines "kritischen"
     * Kontostandes, d.h. der Kontostand ist weniger als 1%
     * über der Kreditlinie
     */
    public boolean isBalanceAlert() {
        return (balance <= (creditLine - (creditLine / 100)));
    }

    /**
     * Boolsche Methode zum Feststllen, ob das Abheben eins bestimmten
     * Betrags den Kontostand unterhalb der Kreditlinie führt.
     */
    public boolean isOverdrawAmount(double amount) {
        return ((balance - amount) <= creditLine);
    }

    /**
     * Gibt das Array mit den Transactions zurueck
     * @return transactions
     */
    public Transaction[] getTransactions() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Gibt den TransactionPointer zurueck
     * @return transactionPointer
     */
    public int getTransactionPointer() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Berechnet den aktuellen Kontostand
     * @return currentBalance
     */
    public double getCurrentBalance() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Fuegt eine Buchung hinzu
     * @param amount
     * @param text
     */
    public void book(double amount, String text) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Aktualisiert das Attribut balance mit Hilfe der transactions
     */
    public void updateBalance() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Gibt den Kontoauszug aus
     */
    public void printAccountStatement() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Feststellen der Gleichheit zweier Account-Objekte. Die Gleichheit wird in
     * diesem Beispiel auf eine identische Kontonummer beschraenkt.
     */
    @Override
    public boolean equals(Object o) {
        /*
         * Abfragen, ob ein Objekt ungl. NULL ist und ob ein Objekt gecastet werden
         * kann, sind immer wichtig!
         */
        if (o != null && o instanceof Account) {
            Account c = (Account) o;
            if (c.getId() == this.id) {
                return true;
            }
        }
        return false;
    }
}

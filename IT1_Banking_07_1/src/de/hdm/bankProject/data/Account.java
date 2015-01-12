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
        return this.getCurrentBalance() * (interestRate / 100);
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
        return (this.getCurrentBalance() <= (creditLine - (creditLine / 100)));
    }

    /**
     * Boolsche Methode zum Feststllen, ob das Abheben eins bestimmten
     * Betrags den Kontostand unterhalb der Kreditlinie führt.
     */
    public boolean isOverdrawAmount(double amount) {
        return ((this.getCurrentBalance() + amount) <= creditLine);
    }

    /**
     * Gibt das Array mit den Transactions zurueck
     * @return transactions
     */
    public Transaction[] getTransactions() {
        return this.transactions;
    }

    /**
     * Gibt den TransactionPointer zurueck
     * @return transactionPointer
     */
    public int getTransactionPointer() {
        return this.transactionPointer;
    }

    /**
     * Berechnet den aktuellen Kontostand
     * @return currentBalance
     */
    public double getCurrentBalance() {
        double currentBalance = this.getBalance();
        for (int i = 0; i < this.getTransactionPointer(); i++) {
			if(this.transactions[i] != null){
				currentBalance = this.transactions[i].getAmount() + currentBalance;
			}
		}
        return currentBalance;
    }

    /**
     * Fuegt eine Buchung hinzu
     * @param amount
     * @param text
     */
    public void book(double amount, String text) {
    	Transaction transaction = new Transaction();
    	transaction.setAmount(amount);
    	transaction.setText(text);
    	this.transactions[this.transactionPointer] = transaction;
    	this.transactionPointer++;
    }

    /**
     * Aktualisiert das Attribut balance mit Hilfe der transactions
     */
    public void updateBalance() {
        this.balance = this.getCurrentBalance();
        this.transactions = new Transaction[50];
        this.transactionPointer = 0;
    }

    /**
     * Gibt den Kontoauszug aus
     */
    public void printAccountStatement() {
        Transaction tempTransaction = null;
        double tempBalance = this.getBalance();
        
        System.out.println("Kontoauszug fuer : " + this.getOwner().getFirstName()
        		+ " " + this.getOwner().getLastName());
        System.out.println("Kontonummer     : " + this.getId());
        System.out.println();
        System.out.println("Kontostand zu Beginn: " + this.getBalance());
        
        for (int i = 0; i < this.getTransactionPointer(); i++) {
        	tempTransaction = this.getTransactions()[i];
        	tempBalance = tempBalance + tempTransaction.getAmount();
        	
        	System.out.println(tempTransaction.getDate() + " " +
        			tempTransaction.getText() + " " +
        			tempTransaction.getAmount() + " " +
        			tempBalance);
		}
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

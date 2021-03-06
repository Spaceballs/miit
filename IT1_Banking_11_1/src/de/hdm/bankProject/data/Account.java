package de.hdm.bankProject.data;

/**
 * Klasse der Bankkonten
 * @author Thies
 */
public class Account implements Cloneable{

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
     * Array fuer Transactions
     */
    private Transaction[] transactions = new Transaction[100];
    /**
     * Der Transactionpointer
     */
    private int transactionPointer = 0;
    /**
     * moegliche Währungen
     */
    public final static int EUR = 0;
    public final static int CHF = 1;
    public final static int USD = 2;
    public final static int YEN = 3;
    /**
     * Waehrung dieses Kontos
     */
    private int currency = 0;

    public Account() {
    }

    public Account(Customer owner) {
        this.owner = owner;
    }

    public Account(Customer owner, int currency) {
        this.owner = owner;
        this.currency = currency;
    }

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
     * Auslesen der Waehrung
     * @return ganze Zahl, die einer Waehrung entspricht
     */
    public int getCurrency() {
        return this.currency;
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
        for (int i = 0; i < this.transactionPointer; i++) {
            if (this.transactions[i] != null) {
                currentBalance = currentBalance +
                        this.transactions[i].getAmount();
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
        this.transactionPointer = this.transactionPointer + 1;
    }

    /**
     * Aktualisiert das Attribut balance mit Hilfe der transactions
     */
    public void updateBalance() {
        this.balance = this.getCurrentBalance();
        this.transactions = new Transaction[100];
        this.transactionPointer = 0;
    }

    /**
     * produziert den Kontoauszug als String
     */
    public String getAccountStatement() {
        StringBuffer result = new StringBuffer();
        Transaction tempTransaction = null;
        double tempBalance = this.getBalance();

        result.append("Kontoauszug fuer : " + this.getOwner() + "\n");
        result.append("Kontonummer : " + this.getId() + "\n");
        result.append("Waehrung : " + this.getCurrencyAsString());
        result.append("\n");
        result.append("Kontostand zu Beginn: " + this.normalizeDoubleValue(this.getBalance()) + "\n");

        for (int i = 0; i < this.getTransactionPointer(); i++) {
            tempTransaction = this.getTransactions()[i];
            tempBalance = tempBalance + tempTransaction.getAmount();

            result.append(tempTransaction.getTransactionId());
            result.append(": ");

            result.append(tempTransaction.getDate());
            result.append(" ");

            String tempText = tempTransaction.getText();
            if (tempText.length() <= 20) {
                while (tempText.length() < 20) {
                    tempText += " ";
                }
            } else {
                tempText = tempText.substring(0, 20);
            }
            result.append(tempText);

            result.append(" ");
            result.append(
                    this.normalizeDoubleValue(tempTransaction.getAmount()));
            result.append(" ");
            result.append(this.normalizeDoubleValue(tempBalance));
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Gibt einen String des Double Wertes zurueck mit genau zwei Kommastellen
     * @param number
     * @return String
     */
    public String normalizeDoubleValue(double number) {
        StringBuffer buffer = new StringBuffer(Double.toString(number));
        String substring = buffer.substring(buffer.lastIndexOf(".") + 1);

        if (substring.length() < 2) {
            return buffer + "0";
        } else {
            return buffer.substring(0, buffer.lastIndexOf(".") + 3);
        }
    }

    /**
     * Umwandlung einer ganzen Zahl zu einer Zeichenkette für Waehrungen
     * @param currency
     * @return Waehrungsbezeichnung
     */
    public static String currencyToString(int currency) {
        switch (currency) {
            case EUR:
                return "EUR";
            case CHF:
                return "CHF";
            case USD:
                return "USD";
            case YEN:
                return "Yen";
            default:
                return Integer.toString(currency);
        }
    }

    /**
     * Ermittlung der Waehrungsbezeichnung fuer dieses Konto
     * @return Waehrungsbezeichnung für dieses Konto
     */
    public String getCurrencyAsString() {
        return currencyToString(this.currency);
    }

    /**
     * Feststellen der Gleichheit zweier Account-Objekte. Die Gleichheit wird in
     * diesem Beispiel auf eine identische Kontonummer beschraenkt.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
        	return false;			
		}
        if (getClass() != obj.getClass()) {
        	return false;
        }
        final Account a = (Account) obj;
        if (this.id != a.id) {
			return false;
		}
        if (this.balance != a.balance){
        	return false;
        }
        if (!this.getOwner().equals(a.getOwner())) {
			return false;
		}
        if (this.currency != a.currency) {
			return false;
		}
        return true;
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


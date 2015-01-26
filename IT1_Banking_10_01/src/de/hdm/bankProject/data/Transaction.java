package de.hdm.bankProject.data;


import java.util.Date;

/**
 *
 * @author alex
 */
public class Transaction {
	
	private static long transactionIdCounter = 100000000L;
	private long transactionId = 0L;
    private double amount = 0;
    private String text = "";
    private Date date = new Date();
    
    public Transaction(){
    	this.transactionId = getTransactionIdCounter();
    	setTransactionIdCounter(getTransactionIdCounter() + 1);;
    }
    
    public double getAmount() {
        return this.amount;
    }

    public Date getDate() {
        return this.date;
    }

    public String getText() {
        return this.text;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setText(String text) {
        this.text = text;
    }

	/**
	 * @return the transactionId
	 */
	public long getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the transactionIdCounter
	 */
	public static long getTransactionIdCounter() {
		return transactionIdCounter;
	}

	/**
	 * @param transactionIdCounter the transactionIdCounter to set
	 */
	public static void setTransactionIdCounter(long transactionIdCounter) {
		Transaction.transactionIdCounter = transactionIdCounter;
	}
}

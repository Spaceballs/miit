package de.hdm.bankProject.data;


import java.util.Date;

/**
 *
 * @author alex
 */
public class Transaction {

    private double amount = 0;
    private String text = "";
    private Date date = new Date();
    private long transactionId = 0L;
    private static long transactionIdCounter = 100000000L;

    public Transaction(){
        transactionId=transactionIdCounter;
        transactionIdCounter++;
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

    public long getTransactionId() {
        return transactionId;
    }

    public long getTransactionIdCounter() {
        return transactionIdCounter;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setText(String text) {
        this.text = text;
    }
}

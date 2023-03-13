package model.transactions;

import model.Account;
import model.moneyoutprimitives.Date;
import org.json.JSONObject;
import persistence.Writable;

/**
 * Abstract Representation of a Transaction with cost (in CAD), and date (YYYYMMDD).
 * Extended by POSPurchase, Investment, and ETransfer.
 * All Transactions that are instantiated are automatically added to ListOfTransaction.transactionHistory.
 * With each newly instantiated transaction, Account.account balance is modified
 */

public abstract class Transaction implements Writable {

    private double cost; //The price of the Transaction
    private Date date;   //The date of the Transaction

    //REQUIRES: cost > 0
    //MODIFIES: this, ListOfTransaction.transactionHistory, Account.account
    //EFFECTS: Abstract constructor for Transaction (super for cost and date), extended in subclasses.
    //         Adds any new transaction to ListOfTransaction.transactionHistory
    public Transaction(double cost, Date date) {
        this.cost = cost;
        this.date = date;
        //TransactionList.accessTransactionHistory().add(this);
        //Account.accessAccount().setBalance(Account.accessAccount().getBalance() - cost);
    }

    //EFFECTS: Returns true if date attached to this is after the input date.
    //         False otherwise
    public boolean afterDate(Date date) {
        return this.date.getDate() >= date.getDate();
    }

    //Getters
    public double getCost() {
        return cost;
    }

    public int getDate() {
        return date.getDate();
    }

    //Setters (Test Coverage handled in POSPurchase)
    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    //JSON
    @Override
    public JSONObject toJson() {
        return null;
    }
}

package model.transactions;

import model.Account;
import model.Event;
import model.EventLog;
import model.Loading;
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
    //MODIFIES: this
    //EFFECTS: Abstract constructor for Transaction (super for cost and date), extended in subclasses.
    //Account Balance and TransactionList updates are handled within the ConsoleMoneyOutApp class.
    public Transaction(double cost, Date date) {
        this.cost = cost;
        this.date = date;
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
        if (!Loading.isLoading() == true) {
            EventLog.getInstance().logEvent(new Event("Changed Transaction Cost from " + this.getCost()
                    + " to " + cost + "."));
        }
        this.cost = cost;
    }

    public void setDate(Date date) {
        if (!Loading.isLoading() == true) {
            EventLog.getInstance().logEvent(new Event("Changed Transaction Date from "
                    + this.date.getDate() + " to " + date.getDate() + "."));
        }
        this.date = date;
    }

    //JSON
    //EFFECTS: Abstract method to convert an individual Transaction to JSON data. Overridden by Transaction subclasses.
    @Override
    public abstract JSONObject toJson();
}

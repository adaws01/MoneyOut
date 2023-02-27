package model.Transactions;

import model.MoneyOutPrimitives.Date;

import java.util.ArrayList;
import java.util.List;

// represents a transaction with cost (CAD), date (YYMMDD), type of good, and
// quantity (#, or false for intangible costs (ex: rent payments)
public abstract class Transaction {
    private double cost;
    private Date date;

    //EFFECTS: constructs the transaction (cost, date, good, quantity)
    public Transaction(double cost, Date date){
        this.cost = cost;
        this.date = date;
    }

    //TODO: Transaction List Implementation
    public List<Transaction> transactionHistory = new ArrayList<>();

    //Transaction Delete Method //should remove a transaction from the history list

    //View Transaction History Method //return the list

    //Getters
    public double getCost() {
        return cost;
    }
    public int getDate() {
        return date.getDate();
    }

    //Setters (Test Coverage handled in POSPurchase)
    public void setCost(double cost) {this.cost = cost;}
    public void setDate(Date date) {this.date = date;}
}

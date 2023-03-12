package model.Transactions;

import model.Account;
import model.MoneyOutPrimitives.Date;
import model.MoneyOutPrimitives.Location;
import ui.MoneyOutApp;

// represents a transaction with cost (CAD), date (YYMMDD), type of good, and
// quantity (#, or false for intangible costs (ex: rent payments)
public abstract class Transaction {

    private double cost;
    private Date date;

    //EFFECTS: constructs the transaction (cost, date, good, quantity)
    public Transaction(double cost, Date date){
        this.cost = cost;
        this.date = date;
        ListOfTransaction.transactionHistory.add(this);
        Account.account.setBalance(Account.account.getBalance() - cost);
    }

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
